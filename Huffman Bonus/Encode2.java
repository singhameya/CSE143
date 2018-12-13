// Encode2 prompts the user for the name of a file to be encoded and the name
// to use for the binary (encoded) output file.  It examines the input file for
// the frequencies of characters and then uses a Huffman tree to generate codes
// to use for each character so as to compress the original file.

import java.io.*;
import java.util.*;

public class Encode2 {
    public static final int CHAR_MAX = 256;  // max char value to be encoded

    public static void main(String[] args) throws IOException {
        System.out.println("This program encodes a file with a Huffman code.");
        System.out.println();

        Scanner console = new Scanner(System.in);
        System.out.print("input file name? ");
        String inFile = console.nextLine();
        System.out.print("output file name? ");
        String outputFile = console.nextLine();
        
        // open input file and count character frequencies
        FileInputStream input = new FileInputStream(inFile);
        int[] count = new int[CHAR_MAX];
        int n = input.read();
        while (n != -1) {
            count[n]++;
            n = input.read();
        }

        // build tree, get codes
        HuffmanTree2 t = new HuffmanTree2(count);
        String[] codes = new String[CHAR_MAX + 1];
        t.assign(codes);

        // open output, write header
        BitOutputStream output = new BitOutputStream(outputFile);
        t.writeHeader(output);

        // reset input, encode file, close output
        input.close();
        input = new FileInputStream(inFile);
        n = input.read();
        while (n != -1) {
            writeString(codes[n], output);
            n = input.read();
        }
        writeString(codes[CHAR_MAX], output);
        output.close();
    }

    public static void writeString(String s, BitOutputStream output) {
        for (int i = 0; i < s.length(); i++) {
            output.writeBit(s.charAt(i) - '0');
        }
    }
}
