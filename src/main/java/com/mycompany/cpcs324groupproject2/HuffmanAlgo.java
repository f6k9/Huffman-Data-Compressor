
package com.mycompany.cpcs324groupproject2;

import java.util.*;

public class HuffmanAlgo {
    private Map<Character, String> codeMap = new HashMap<>();
    private HuffmanNode root;

    // Build the tree and generate codes
    public void buildTreeAndCodes(String text) {
        // 1. Calculate frequency of each character
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (frequencyMap.containsKey(c)) {
                frequencyMap.put(c, frequencyMap.get(c) + 1);
            } else {
                frequencyMap.put(c, 1);
            }
        }

        // 2. Add all nodes to Priority Queue
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (Character c : frequencyMap.keySet()) {
            pq.add(new HuffmanNode(c, frequencyMap.get(c)));
        }

        // 3. Build the Tree (Greedy Strategy)
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();  // Smallest
            HuffmanNode right = pq.poll(); // Second smallest
            
            HuffmanNode parent = new HuffmanNode(left.frequency + right.frequency, left, right);
            pq.add(parent);
        }
        this.root = pq.poll(); // The final node is the root

        // 4. Generate codes
        generateCodesRecursive(root, "");
    }

    private void generateCodesRecursive(HuffmanNode node, String currentCode) {
        if (node == null) return;
        
        // If leaf node, store the code
        if (node.left == null && node.right == null) {
            codeMap.put(node.character, currentCode);
            return;
        }
        generateCodesRecursive(node.left, currentCode + "0");
        generateCodesRecursive(node.right, currentCode + "1");
    }

    public String encode(String text) {
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            encoded.append(codeMap.get(c));
        }
        return encoded.toString();
    }

    public String decode(String encodedText) {
        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;
        
        for (int i = 0; i < encodedText.length(); i++) {
            char bit = encodedText.charAt(i);
            if (bit == '0') current = current.left;
            else current = current.right;

            if (current.left == null && current.right == null) {
                decoded.append(current.character);
                current = root; 
            }
        }
        return decoded.toString();
    }

    // Displays the table of codes (Required by assignment)
    public void printCodeTable() {
        System.out.println("\n--- Huffman Codewords Table ---");
        System.out.println("Char\tCodeword");
        for (Map.Entry<Character, String> entry : codeMap.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
        System.out.println("-------------------------------");
    }
}
