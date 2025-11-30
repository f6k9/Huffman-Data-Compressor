# 🗜️ Huffman Data Compressor (Greedy Algorithm Analysis) 

A Java benchmarking application that implements Huffman Coding from scratch to compress text data and measures its efficiency against a standard Fixed-Length baseline encoding scheme.

## 🌟 What It Does
* **Generates Test Inputs:** Uses an automated script (`FileGenerator.java`) to build random uppercase text datasets at three specific scales: 50KB, 200KB, and 1MB.
* **Builds Huffman Trees:** Calculates text frequencies dynamically, pushes items into a Min-Heap Priority Queue, and extracts nodes using a Greedy strategy to assign optimal binary prefix-free codes.
* **Measures Performance:** Utilizes nanosecond tracking blocks to record the exact compression speeds, decompression runtime intervals, and compression file-size reduction metrics.

## 📊 Benchmarking Results Summary
Our experimental runs yielded a consistent **40.4% savings in file storage sizes** across all test parameters when utilizing Huffman variable-length encoding:

| File Scale | Algorithm | Original Size | Compressed Size | Reduction Ratio | Encode Speed |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **50 KB** | Huffman | 51,200 B | 30,469 B | **0.5951** | 17.04 ms |
| | Fixed-Length | 51,200 B | 51,200 B | 1.0000 | 11.15 ms |
| **1 MB** | Huffman | 1,048,576 B | 624,931 B | **0.5960** | 28.88 ms |
| | Fixed-Length | 1,048,576 B | 1,048,576 B | 1.0000 | 37.92 ms |

## 📁 Key File Breakdown
* `CPCS324GroupProject2.java` — The central test controller that drives the data loops and prints the performance matrix.
* `HuffmanAlgo.java` — Implements frequency tracking, tree mapping, mapping generation, and bit packing systems.
* `HuffmanNode.java` — Builds individual node connections for data structures and supports sorting elements using a custom comparator.
* `FixedAlgo.java` — The control baseline that translates characters directly into standard 8-bit strings.
* `FileGenerator.java` — Generates arbitrary character data block strings.

## 🚀 How to Run It

1. Compile all project files from your terminal:
   ```bash
   javac com/mycompany/cpcs324groupproject2/*.java