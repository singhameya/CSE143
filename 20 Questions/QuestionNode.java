/*
 * Author: Ameya Singh
 * CSE 143 AQ
 * TA: Soham P.
 * Homework 7: 20 Questions
 */

/**
 * QuestionNode is a class that supports in creating QuestionTree instances.
 * Each node holds a main data String and references to its left and right child
 * nodes.
 */
public class QuestionNode {
    /**
     * Holds data string in node.
     */
    public String data;
    /**
     * Reference to left node.
     */
    public QuestionNode left;
    /**
     * Reference to right node.
     */
    public QuestionNode right;

    /**
     * Constructor for nodes with no child nodes.
     * @param data Data string for node.
     */
    public QuestionNode(String data) {
        this(data, null, null);
    }

    /**
     * Constructs a new QuestionNode with specified data and child nodes.
     * @param data Data string for node.
     * @param left Reference to left node.
     * @param right Reference to right node.
     */
    public QuestionNode(String data, QuestionNode left, QuestionNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    /**
     * Returns string representation of contained data.
     * @return Returns data as a String.
     */
    @Override
    public String toString() {
        return data;
    }
}
