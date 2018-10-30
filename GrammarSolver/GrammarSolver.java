import java.util.*;

public class GrammarSolver {
    private SortedMap<String, Set<String>> grammar;

    public GrammarSolver(List<String> grammar) {
        this.grammar = new TreeMap<String, Set<String>>();

        String nonterminal;
        for (String s : grammar) {
            String[] parts = s.split("::=");
            nonterminal = parts[0];

            parts = parts[1].split("\\|");
            for (String part : parts) {

            }
        }
    }

    public boolean grammarContains(String symbol) {


        return false;
    }

    public String[] generate(String symbol, int times) {


        return null;
    }

    public String getSymbols() {
        return grammar.keySet().toString();
    }
}
