import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class HuffmanTree {
    HuffmanNode root;

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
            HuffmanNode newNode = new HuffmanNode(null, node1.numOccurrences + node2.numOccurrences);
            newNode.left = node1;
            newNode.right = node2;
            priorityQueue.add(newNode);
        }

        root = priorityQueue.remove();
    }

    public HuffmanTree(Scanner input) {

    }

    public void write(PrintStream output) {
        write(output, root, "");
    }

    private void write(PrintStream output, HuffmanNode node, String currentPattern) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                output.println(node.character);
                output.println(currentPattern);
            }
            write(output, node.left, currentPattern + "0");
            write(output, node.right, currentPattern + "1");
        }
    }

    public void decode(BitInputStream input, PrintStream output, int eof) {

    }
}
