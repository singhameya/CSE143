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
     * @param dict A
     */
    public AnagramSolver(List<String> dict) {
        inventoryMap = new HashMap<String, LetterInventory>();
        this.dict = dict;
        for (String s : dict) {
            inventoryMap.put(s, new LetterInventory(s));
        }
    }

    public void print(String s, int max) {
        if (max < 0) {
            throw new IllegalArgumentException();
        }

        LetterInventory targetInventory = new LetterInventory(s);
        print(targetInventory, max, pruneDictionary(dict, targetInventory),
                0, new Stack<String>());
    }

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
