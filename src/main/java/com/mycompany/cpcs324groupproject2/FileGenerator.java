
package com.mycompany.cpcs324groupproject2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FileGenerator {

    // Helper method to create a random string of A-Z characters
    public static String generateRandomText(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Approximate sizes in characters (1 char = 1 byte)
        int[] sizes = {51200, 204800, 1048576}; // 50KB, 200KB, 1MB
        String[] fileNames = {"file_50KB.txt", "file_200KB.txt", "file_1MB.txt"};

        System.out.println("Generating files...");

        for (int i = 0; i < sizes.length; i++) {
            String text = generateRandomText(sizes[i]);
            try {
                FileWriter writer = new FileWriter(fileNames[i]);
                writer.write(text);
                writer.close();
                System.out.println("Created: " + fileNames[i]);
            } catch (IOException e) {
                System.out.println("Error writing file: " + fileNames[i]);
            }
        }
        System.out.println("Done! Refresh your project folder to see the files.");
    }
}