/*
 * Author: Ameya Singh
 * CSE 143 AQ
 * TA: Soham P.
 * Homework 5: GrammarSolver
 */

import java.util.*;

/**
 * GrammarSolver facilitates the manipulation of a grammar. Given rules in
 * Backus-Naur Form the class will allow the user to preform certain tasks,
 * notably generating elements of the grammar.
 */
public class GrammarSolver {
    /**
     * Represents all of the grammar rules.
     */
    private SortedMap<String, List<String[]>> grammar;

    /**
     * Constructs a new GrammarSolver given a list containing strings that
     * represent the grammar rules in the BNF format. Throws an exception if the
     * passed passed grammar is empty or if it contains more than one entry for
     * the same non-terminal.
     *
     * @param grammar List of strings containing grammar rules in BNF format.
     * @throws IllegalArgumentException Thrown if passed grammar is empty.
     * @throws IllegalArgumentException Thrown if the passed grammar contains
     *                                  more than one entry for the same non-
     *                                  terminal.
     */
    public GrammarSolver(List<String> grammar) {
        if (grammar.isEmpty()) {
            throw new IllegalArgumentException("Grammar is empty.");
        }

        this.grammar = new TreeMap<String, List<String[]>>();

        for (String s : grammar) {
            String[] symbol = s.split("::=");

            String nonTerminal = symbol[0];
            if (this.grammarContains(nonTerminal)) {
                throw new IllegalArgumentException("Grammar contains " +
                        "more than one entry for the same non-terminal.");
            }

            String[] rules = symbol[1].split("\\|");

            List<String[]> ruleList = new LinkedList<String[]>();
            for (String rule : rules) {
                ruleList.add(rule.trim().split("\\s+"));
            }
            this.grammar.put(nonTerminal, ruleList);
        }
    }

    /**
     * Returns whether or not the passed symbol is a non-terminal part of the
     * current grammar.
     *
     * @param symbol String to be checked as a valid non-terminal in the grammar.
     * @return Returns true if the symbol is a non-terminal of the grammar,
     * returns false otherwise.
     */
    public boolean grammarContains(String symbol) {
        return grammar.containsKey(symbol);
    }

    /**
     * Randomly generates the given number of occurrences of a given symbol.
     * For any given non-terminal symbol, each rule has an equal probability of
     * being applied. Throws an exception if the passed symbol is not part of
     * the grammar or if the passed number of times is less than 0.
     *
     * @param symbol Symbol whose rules will be used to generate the output
     *               strings.
     * @param times  Number of occurrences of the symbol to generate.
     * @return Returns an array of strings that conform to the rules within the
     * grammar for the passed symbol.
     * @throws IllegalArgumentException Thrown if the grammar does not contain
     *                                  the passed non-terminal symbol.
     * @throws IllegalArgumentException Thrown if passed number of times to
     *                                  generate symbol is less than 0.
     */
    public String[] generate(String symbol, int times) {
        if (!grammarContains(symbol)) {
            throw new IllegalArgumentException("Symbol not present in " +
                    "grammar.");
        }
        if (times < 0) {
            throw new IllegalArgumentException("Times cannot be less than 0.");
        }

        String[] out = new String[times];
        Random random = new Random();
        for (int i = 0; i < times; i++) {
            String terminal = getTerminal(symbol, random);
            out[i] = terminal.substring(0, terminal.length() - 1);
        }

        return out;
    }

    /**
     * Private helper that will generate a string given a non-terminal symbol.
     *
     * @param symbol Non-terminal to generate string for.
     * @param random Random object used to pick random rule.
     * @return Returns a terminated string from the non-terminal.
     */
    private String getTerminal(String symbol, Random random) {
        if (!grammarContains(symbol)) {
            return symbol + " ";
        } else {
            List<String[]> ruleList = grammar.get(symbol);
            String[] rules = ruleList.get(random.nextInt(ruleList.size()));

            String out = "";
            for (String rule : rules) {
                out += getTerminal(rule, random);
            }
            return out;
        }
    }

    /**
     * Returns a string representation of the non-terminal symbols contained in
     * the grammar.
     *
     * @return Returns all available non-terminal symbols as a sorted
     * comma-separated list enclosed in square brackets.
     */
    public String getSymbols() {
        return grammar.keySet().toString();
    }
}
