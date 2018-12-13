/*
 * Author: Ameya Singh
 * CSE 143 AQ
 * TA: Soham P.
 * Homework 8: Huffman Code
 */

/**
 * HuffmanNode provides a node class used in creating instances of HuffmanTree.
 */
public class HuffmanNode implements Comparable<HuffmanNode> {
    /**
     * Nullable object holding integer representation of character.
     */
    public Integer character;
    /**
     * Nullable object holding integer number of occurrences of character.
     */
    public Integer numOccurrences;
    /**
     * Reference to left child node.
     */
    public HuffmanNode left;
    /**
     * Reference to right child node.
     */
    public HuffmanNode right;

    /**
     * Constructs new HuffmanNode with no children.
     *
     * @param character      Character node represents.
     * @param numOccurrences Occurrences of represented character.
     */
    public HuffmanNode(Integer character, Integer numOccurrences) {
        this(character, numOccurrences, null, null);
    }

    /**
     * Constructs a new HuffmanNode.
     *
     * @param character      Character node represents.
     * @param numOccurrences Occurrences of represented character.
     * @param left           Left child node.
     * @param right          Right child node.
     */
    public HuffmanNode(Integer character, Integer numOccurrences,
                       HuffmanNode left, HuffmanNode right) {
        this.character = character;
        this.numOccurrences = numOccurrences;
        this.left = left;
        this.right = right;
    }

    /**
     * Compares number of occurrences.
     *
     * @param o Other HuffmanNode.
     * @return Whether node has greater than less than or equal numOccurrences.
     */
    @Override
    public int compareTo(HuffmanNode o) {
        if (this.numOccurrences > o.numOccurrences) {
            return 1;
        } else if (this.numOccurrences < o.numOccurrences) {
            return -1;
        } else {
            return 0;
        }
    }
}
