import java.util.*;

/**
 * AnagramSolver is a utility for computing anagrams.
 * AnagramSolver finds all combinations of words from a passed dictionary that
 * have the same letters as a given phrase.
 */
public class AnagramSolver {
    /**
     * Holds the computed LetterInventories of all words in the dictionary.
     */
    private Map<String, LetterInventory> inventoryMap;
    /**
     * Holds the full dictionary of available words.
     */
    private List<String> dict;

    /**
     * Constructs a new AnagramSolver which will use the passed dictionary of
     * words to solve anagrams.
     * @param dict Dictionary of words that will be used to solve the anagrams.
     */
    public AnagramSolver(List<String> dict) {
        inventoryMap = new HashMap<String, LetterInventory>();
        this.dict = dict;
        for (String s : dict) {
            inventoryMap.put(s, new LetterInventory(s));
        }
    }

    /**
     * Prints all combinations of words from the dictionary that are anagrams of
     * the passed string that include at most the passed maximum number words.
     * @param s String to find valid anagrams of using dictionary words.
     * @param max Maximum number of words to include in the anagram.
     * @throws IllegalArgumentException Thrown if passed max is less than zero.
     */
    public void print(String s, int max) {
        if (max < 0) {
            throw new IllegalArgumentException();
        }

        LetterInventory targetInventory = new LetterInventory(s);
        print(targetInventory, max, pruneDictionary(dict, targetInventory),
                0, new Stack<String>());
    }

    /**
     * Private recursive backtracking method for printing all anagrams. Utilizes
     * pruneDictionary on every recursive call to reduce number of words to be
     * tested.
     * @param current The current LetterInventory of remaining characters from
     *                the original string.
     * @param max The max number of words allowed by the user.
     * @param dict Current dictionary of words to be searched.
     * @param picked Current number of picked words.
     * @param words Stack of words that have been picked.
     */
    private void print(LetterInventory current, int max, List<String> dict,
                       int picked, Stack<String> words) {
        if (current.isEmpty()) {
            System.out.println(words);
        }
        if (picked < max || max == 0) {
            for (String s1 : dict) {
                words.push(s1);
                LetterInventory newInventory =
                        current.subtract(inventoryMap.get(s1));
                print(newInventory, max, pruneDictionary(dict, newInventory),
                        picked + 1, words);
                words.pop();
            }
        }
    }

    /**
     * Private helper to prune words that cannot be subtracted from the current
     * LetterInventory.
     * @param dict The current dictionary to be pruned.
     * @param current The current LetterInventory to check words against.
     * @return Returns a pruned dictionary containing only the words that can be
     *         subtracted from LetterInventory current.
     */
    private List<String> pruneDictionary(List<String> dict,
                                         LetterInventory current) {
        List<String> prunedDictionary = new LinkedList<String>(dict);
        for (String s : dict) {
            if (current.subtract(inventoryMap.get(s)) == null) {
                prunedDictionary.remove(s);
            }
        }
        return prunedDictionary;
    }
}
