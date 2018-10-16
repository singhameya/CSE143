/**
 * LetterInventory represents the count of each letter of the alphabet
 * within a specified input string.
 *
 * @author Ameya Singh, CSE143 A, TA: Soham P.
 */
public class LetterInventory {
    public static final int LETTER_COUNTS_ARRAY_SIZE = 26;

    private int[] letterCounts;  // Holds counters for all alphabets
    private int size;            // Current size of inventory

    /**
     * Constructs a new letter inventory using the provided string
     *
     * @param data Input String whose characters will be inventoried
     */
    public LetterInventory(String data) {
        letterCounts = new int[LETTER_COUNTS_ARRAY_SIZE];
        size = 0;

        setLetterCounts(data);
    }

    /**
     * Helper method: Inventories the provided String
     *
     * @param data String to be inventoried
     */
    private void setLetterCounts(String data) {
        data = data.toLowerCase();
        char[] dataArr = data.toCharArray();
        for (char c : dataArr) {
            if (Character.isAlphabetic(c)) {
                int index = (int) c - 'a';
                letterCounts[index] = letterCounts[index] + 1;
                size++;
            }
        }
    }

    /**
     * Gets the current count of passed character in the inventory
     *
     * @param letter Alphabetic character whose count to return
     * @return Count of 'letter' in inventory
     * @throws IllegalArgumentException if non-alphabetic letter passed
     */
    public int get(char letter) {
        letter = Character.toLowerCase(letter);
        checkCharInput(letter);

        int index = (int) letter - 'a';
        return letterCounts[index];
    }


    /**
     * Sets the count of passed character in the inventory
     *
     * @param letter Alphabetic character whose count is to be set
     * @param value  Positive integer value to set count of 'letter' to
     * @throws IllegalArgumentException if non-alphabetic letter passed
     */
    public void set(char letter, int value) {
        letter = Character.toLowerCase(letter);
        checkCharInput(letter);
        if (value < 0)
            throw new IllegalArgumentException();

        int index = (int) letter - 'a';
        int before = letterCounts[index];
        letterCounts[index] = value;
        int delta = value - before;
        size = size + delta;
    }

    /**
     * Private Helper: Checks if passed char is valid
     *
     * @param c char to check
     * @throws IllegalArgumentException Thrown if char is not valid
     */
    private void checkCharInput(char c) {
        if (!Character.isAlphabetic(c)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Gets the current size of the inventory
     *
     * @return Size of the LetterInventory
     */
    public int size() {
        return size;
    }

    /**
     * Returns whether the inventory is currently empty
     *
     * @return Returns true if the LetterInventory is empty
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Creates a alphabetic list of the letters in the inventory
     * Repeats the letter for each occurrence in the inventory
     *
     * @return Square bracketed String of letters in inventory
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("[");
        for (int i = 0; i < LETTER_COUNTS_ARRAY_SIZE; i++) {
            for (int j = 0; j < letterCounts[i]; j++) {
                out.append((char) ('a' + i));
            }
        }
        out.append("]");
        return out.toString();
    }

    /**
     * Returns a LetterInventory with the sum of this inventory and the
     * passed in inventory
     *
     * @param other LetterInventory to be summed with current inventory
     * @return LetterInventory of the sum of this and other
     */
    public LetterInventory add(LetterInventory other) {
        LetterInventory newInv = new LetterInventory("");
        for (int i = 0; i < LETTER_COUNTS_ARRAY_SIZE; i++) {
            int sum = other.letterCounts[i] + this.letterCounts[i];
            newInv.letterCounts[i] = sum;
            newInv.size += sum;
        }
        return newInv;
    }

    /**
     * Returns a LetterInventory resultant of the subtraction of the passed
     * inventory with this inventory
     * Returns null if the subtraction cannot be completed
     *
     * @param other LetterInventory to be subtracted from current inventory
     * @return LetterInventory of result of subtraction, null if subtraction
     * fails
     */
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory newInv = new LetterInventory("");
        for (int i = 0; i < LETTER_COUNTS_ARRAY_SIZE; i++) {
            int difference = this.letterCounts[i] - other.letterCounts[i];
            if (difference < 0) {
                return null;
            }
            newInv.letterCounts[i] = difference;
            newInv.size += difference;
        }
        return newInv;
    }
}
