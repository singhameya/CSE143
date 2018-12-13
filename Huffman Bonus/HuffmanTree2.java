/*
 * Author: Ameya Singh
 * CSE 143 AQ
 * TA: Soham P.
 * Homework 8b: Huffman Code Bonus
 */

import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Allows for compressing files using the Huffman Coding scheme.
 */
public class HuffmanTree2 {
    /**
     * Holds a reference to the root node of the HuffmanTree.
     */
    private HuffmanNode root;

    /**
     * Constructs a new HuffmanTree using the passed array of the frequency of
     * characters.
     *
     * @param count Integer array where count[i] is the number of occurrences of
     *              the character with integer value i.
     */
    public HuffmanTree2(int[] count) {
        Queue<HuffmanNode> priorityQueue = new PriorityQueue<HuffmanNode>();

        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                priorityQueue.add(new HuffmanNode(i, count[i]));
            }
        }
        priorityQueue.add(new HuffmanNode(count.length, 1));

        while (priorityQueue.size() > 1) {
            HuffmanNode node1 = priorityQueue.remove();
            HuffmanNode node2 = priorityQueue.remove();
            HuffmanNode newNode = new HuffmanNode(null,
                    node1.numOccurrences + node2.numOccurrences, node1, node2);
            priorityQueue.add(newNode);
        }

        root = priorityQueue.remove();
    }

    /**
     * Constructs a Huffman tree from the given input stream.
     *
     * @param input BitInputSteam containing standard bit representation of
     *              tree.
     */
    public HuffmanTree2(BitInputStream input) {
        root = read(input);
    }

    /**
     * Private helper to read tree from input stream.
     *
     * @param input input stream containing tree.
     * @return new root node of tree.
     */
    private HuffmanNode read(BitInputStream input) {
        HuffmanNode node;
        if (input.readBit() == 0) {
            node = new HuffmanNode(null, null);
            node.left = read(input);
            node.right = read(input);
        } else {
            node = new HuffmanNode(read9(input), null);
        }
        return node;
    }

    /**
     * Assigns codes for each character of the tree.
     *
     * @param codes Array to fill with String for each character in the tree
     *              indicating its code.
     */
    public void assign(String[] codes) {
        assign(codes, root, "");
    }

    /**
     * Private helper for assigning codes.
     *
     * @param codes      array to assign to.
     * @param node       current node.
     * @param pathToNode current path.
     */
    private void assign(String[] codes, HuffmanNode node, String pathToNode) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                codes[node.character] = pathToNode;
            }
            assign(codes, node.left, pathToNode + "0");
            assign(codes, node.right, pathToNode + "1");
        }
    }

    /**
     * Writes the current tree to the output stream using the standard bit
     * representation.
     *
     * @param output BitOutputStream to which to write tree.
     */
    public void writeHeader(BitOutputStream output) {
        write(output, root);
    }

    /**
     * Private helper to write bit representation of tree.
     *
     * @param output output stream to write to.
     * @param node   current node.
     */
    private void write(BitOutputStream output, HuffmanNode node) {
        if (node != null) {
            if (node.left != null && node.right != null) {
                output.writeBit(0);
            } else {
                output.writeBit(1);
                write9(output, node.character);
            }
            write(output, node.left);
            write(output, node.right);
        }
    }

    /**
     * Reads bits from passed input stream and writes corresponding characters
     * from the HuffmanTree to the given output stream. Will stop reading when
     * a character matching the passed end of file parameter is reached.
     *
     * @param input  BitInputStream containing encoded characters to decode.
     * @param output PrintStream to which decoded characters will be written.
     * @param eof    Character representing position at which to stop reading file.
     */
    public void decode(BitInputStream input, PrintStream output, int eof) {
        int currentBit = input.readBit();
        HuffmanNode currNode = root;
        boolean reachedEOF = false;

        while (!reachedEOF) {
            if (currNode.left == null && currNode.right == null) {
                if (currNode.character == eof) {
                    reachedEOF = true;
                } else {
                    output.write(currNode.character);
                    currNode = root;
                }
            }

            if (currentBit == 0) {
                currNode = currNode.left;
            } else {
                currNode = currNode.right;
            }

            currentBit = input.readBit();
        }
    }

    // pre : an integer n has been encoded using write9 or its equivalent
    // post: reads 9 bits to reconstruct the original integer
    private int read9(BitInputStream input) {
        int multiplier = 1;
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += multiplier * input.readBit();
            multiplier = multiplier * 2;
        }
        return sum;
    }

    // pre : 0 <= n < 512
    // post: writes a 9-bit representation of n to the given output stream
    private void write9(BitOutputStream output, int n) {
        for (int i = 0; i < 9; i++) {
            output.writeBit(n % 2);
            n = n / 2;
        }
    }
}
