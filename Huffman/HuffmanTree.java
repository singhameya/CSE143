/*
 * Author: Ameya Singh
 * CSE 143 AQ
 * TA: Soham P.
 * Homework 8: Huffman Code
 */

import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * HuffmanTree provides a framework for compressing text files using the Huffman
 * coding scheme.
 */
public class HuffmanTree {
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
    public HuffmanTree(int[] count) {
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
     * Reconstructs a HuffmanTree from file.
     *
     * @param input Scanner containing tree in standard format.
     */
    public HuffmanTree(Scanner input) {
        while (input.hasNextLine()) {
            int character = Integer.parseInt(input.nextLine());
            String pathToNode = input.nextLine();
            root = read(root, character, pathToNode);
        }
    }

    /**
     * Private helper used to assist reading in HuffmanTrees.
     *
     * @param node       Current node.
     * @param character  Current character to represent in node.
     * @param pathToNode Current path to node.
     * @return Returns node conforming to passed parameters.
     */
    private HuffmanNode read(HuffmanNode node, int character,
                             String pathToNode) {
        if (pathToNode.isEmpty()) {
            return new HuffmanNode(character, null);
        } else {
            if (node == null) {
                node = new HuffmanNode(null, null);
            }

            if (pathToNode.charAt(0) == '0') {
                node.left = read(node.left, character, pathToNode.substring(1));
            } else {
                node.right = read(node.right, character,
                        pathToNode.substring(1));
            }
        }
        return node;
    }

    /**
     * Writes HuffmanTree in standard format to passed output stream.
     *
     * @param output PrintStream to which to write tree.
     */
    public void write(PrintStream output) {
        write(output, root, "");
    }

    /**
     * Private helper for writing tree to file.
     *
     * @param output     PrintStream to write to.
     * @param node       Current node.
     * @param pathToNode Current path taken.
     */
    private void write(PrintStream output, HuffmanNode node,
                       String pathToNode) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                output.println(node.character);
                output.println(pathToNode);
            }
            write(output, node.left, pathToNode + "0");
            write(output, node.right, pathToNode + "1");
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
}
