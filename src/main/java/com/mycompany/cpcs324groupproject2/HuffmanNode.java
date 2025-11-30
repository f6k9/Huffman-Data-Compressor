
package com.mycompany.cpcs324groupproject2;

// Represents a node in the Huffman Tree
public class HuffmanNode implements Comparable<HuffmanNode> {
    char character;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    // Constructor for leaf nodes (stores a character)
    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    // Constructor for internal nodes (stores sum of children)
    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    // Used by PriorityQueue to sort nodes (smallest frequency first)
    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}