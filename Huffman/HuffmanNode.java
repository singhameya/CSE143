public class HuffmanNode implements Comparable<HuffmanNode> {
    public Integer character;
    public int numOccurrences;
    public HuffmanNode left;
    public HuffmanNode right;

    public HuffmanNode(Integer character, int numOccurrences) {
        this(character, numOccurrences, null, null);
    }

    public HuffmanNode(Integer character, int numOccurrences, HuffmanNode left, HuffmanNode right) {
        this.character = character;
        this.numOccurrences = numOccurrences;
        this.left = left;
        this.right = right;
    }

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

