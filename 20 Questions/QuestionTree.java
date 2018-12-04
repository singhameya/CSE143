/*
 * Author: Ameya Singh
 * CSE 143 AQ
 * TA: Soham P.
 * Homework 7: 20 Questions
 */

import java.io.*;
import java.util.*;

/**
 * QuestionTree is a class that allows for creating a 20 Questions style, yes/no
 * question guessing game.
 */
public class QuestionTree {
    /**
     * Holds a reference to Scanner on System.in.
     */
    private Scanner console;
    /**
     * References the root of the tree.
     */
    private QuestionNode root;

    /**
     * Constructs a new QuestionTree containing only the answer "computer."
     */
    public QuestionTree() {
        console = new Scanner(System.in);
        root = new QuestionNode("computer");
    }

    /**
     * Replaces the current QuestionTree with one read in from a file.
     *
     * @param input Scanner that is linked to the file that will be read in.
     */
    public void read(Scanner input) {
        root = readNode(input);
    }

    /**
     * Private helper to help facilitate reading a tree from a file.
     *
     * @param input Scanner from which to read lines.
     * @return Returns QuestionNode based on passed file.
     */
    private QuestionNode readNode(Scanner input) {
        String type = input.nextLine();
        String data = input.nextLine();
        QuestionNode node = new QuestionNode(data);
        if (type.equals("Q:")) {
            node.left = readNode(input);
            node.right = readNode(input);
        }
        return node;
    }

    /**
     * Writes the current QuestionTree to a file.
     *
     * @param output PrintStream to which to print the QuestionTree.
     */
    public void write(PrintStream output) {
        write(output, root);
    }

    /**
     * Private helper to help facilitate writing tree to file.
     *
     * @param output PrintStream to print to.
     * @param node   Current node to print.
     */
    private void write(PrintStream output, QuestionNode node) {
        if (node != null) {
            if (node.left != null && node.right != null) {
                output.println("Q:");
                output.println(node);
            } else {
                output.println("A:");
                output.println(node);
            }
            write(output, node.left);
            write(output, node.right);
        }
    }

    /**
     * Ask the user a series of yes or no questions from the current
     * QuestionTree until an answer is reached. If the reached answer is
     * incorrect, the user will be prompted to add a question that allows their
     * answer to be reached and will expand the QuestionTree using that
     * question.
     */
    public void askQuestions() {
        root = askQuestions(root);
    }

    /**
     * Private helper to facilitate asking the user questions from the tree.
     *
     * @param node Current node to ask questions from.
     */
    private QuestionNode askQuestions(QuestionNode node) {
        if (node.left != null && node.right != null) {
            if (yesTo(node.data)) {
                node.left = askQuestions(node.left);
            } else {
                node.right = askQuestions(node.right);
            }
        } else {
            if (!yesTo("Would your object happen to be " + node.data
                    + "?")) {
                System.out.print("What is the name of your object? ");
                String answer = console.nextLine();
                System.out.print("Please give me a yes/no question that\n"
                        + "distinguishes between your object\n"
                        + "and mine--> ");
                String question = console.nextLine();
                boolean ans = yesTo("And what is the answer for your "
                        + "object?");
                QuestionNode newNode;
                if (ans) {
                    newNode = new QuestionNode(question,
                            new QuestionNode(answer), node);
                } else {
                    newNode = new QuestionNode(question,
                            node, new QuestionNode(answer));
                }
                return newNode;
            } else {
                System.out.println("Great, I got it right!");
            }
        }
        return node;
    }

    /**
     * Asks a given question until 'y' or 'n' is received as a response.
     *
     * @param prompt Prompt to ask user.
     * @return Returns boolean true for 'y' and false for 'n'.
     */
    public boolean yesTo(String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }
}
