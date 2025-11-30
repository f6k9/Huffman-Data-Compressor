
package com.mycompany.cpcs324groupproject2;

public class FixedAlgo {
    
    public String encode(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            String binary = Integer.toBinaryString(c);
            
            // Pad with leading zeros to make it 8 bits
            while (binary.length() < 8) {
                binary = "0" + binary;
            }
            sb.append(binary);
        }
        return sb.toString();
    }

    public String decode(String encodedText) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encodedText.length(); i += 8) {
            if (i + 8 <= encodedText.length()) {
                String byteChunk = encodedText.substring(i, i + 8);
                int charCode = Integer.parseInt(byteChunk, 2);
                sb.append((char) charCode);
            }
        }
        return sb.toString();
    }
}