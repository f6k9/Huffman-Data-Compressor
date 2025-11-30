
package com.mycompany.cpcs324groupproject2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CPCS324GroupProject2 {

    public static void main(String[] args) {
        String[] files = {"file_50KB.txt", "file_200KB.txt", "file_1MB.txt"};
        
        System.out.println("=====================================================================================================");
        System.out.printf("%-15s %-10s %-15s %-15s %-10s %-15s %-15s%n", 
            "File", "Method", "Orig Size(B)", "Comp Size(B)", "Ratio", "Enc Time(ms)", "Dec Time(ms)");
        System.out.println("=====================================================================================================");

        for (String fileName : files) {
            String originalText = readFile(fileName);
            if(originalText.isEmpty()) continue; 

            long originalSize = originalText.length(); 

            // --- 1. TEST HUFFMAN ---
            HuffmanAlgo huffman = new HuffmanAlgo();
            
            long startH = System.nanoTime();
            huffman.buildTreeAndCodes(originalText); 
            String huffEncoded = huffman.encode(originalText);
            long endH = System.nanoTime();
            
            // Huffman Size = total bits / 8
            long huffSize = huffEncoded.length() / 8;
            double huffRatio = (double) huffSize / originalSize;
            double huffEncTime = (endH - startH) / 1_000_000.0;

            long startHD = System.nanoTime();
            huffman.decode(huffEncoded);
            long endHD = System.nanoTime();
            double huffDecTime = (endHD - startHD) / 1_000_000.0;

            System.out.printf("%-15s %-10s %-15d %-15d %-10.4f %-15.4f %-15.4f%n", 
                fileName, "Huffman", originalSize, huffSize, huffRatio, huffEncTime, huffDecTime);

            // Print Huffman Table only for the small file (to satisfy requirement without clutter)
            if (fileName.equals("file_50KB.txt")) {
               // huffman.printCodeTable(); // UNCOMMENT THIS LINE TO SEE THE CODE TABLE
            }

            // --- 2. TEST FIXED LENGTH ---
            FixedAlgo fixed = new FixedAlgo();
            
            long startF = System.nanoTime();
            String fixedEncoded = fixed.encode(originalText);
            long endF = System.nanoTime();
            
            long fixedSize = fixedEncoded.length() / 8;
            double fixedRatio = (double) fixedSize / originalSize;
            double fixedEncTime = (endF - startF) / 1_000_000.0;

            long startFD = System.nanoTime();
            fixed.decode(fixedEncoded);
            long endFD = System.nanoTime();
            double fixedDecTime = (endFD - startFD) / 1_000_000.0;

            System.out.printf("%-15s %-10s %-15d %-15d %-10.4f %-15.4f %-15.4f%n", 
                fileName, "Fixed", originalSize, fixedSize, fixedRatio, fixedEncTime, fixedDecTime);
            
            System.out.println("-----------------------------------------------------------------------------------------------------");
        }
    }
    
    public static String readFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
            scanner.close();
            return sb.toString();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return "";
        }
    }
    
}
