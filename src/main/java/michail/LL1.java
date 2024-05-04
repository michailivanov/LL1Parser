package michail;

import java.util.*;

import java.util.List;
import java.util.Map;

public class LL1 {
    private Nonterminal initial;
    private List<CFGRule> rules;
    private List<String> dontNeedSpaceBeforeString;
    private Map<Nonterminal, Map<Terminal, CFGRule>> table;

    public LL1(List<CFGRule> rules, List<String> dontNeedSpaceBeforeString) {
        this.rules = rules;
        this.table = new HashMap<>();
        this.dontNeedSpaceBeforeString = dontNeedSpaceBeforeString;

        // Iterate over the grammar rules
        for (CFGRule rule : rules) {
            Nonterminal nonTerminal = rule.getLeft();

            // Check if the left-hand side non-terminal is the initial symbol
            if (nonTerminal.isInitial()) {
                this.initial = nonTerminal;
            }

            // Calculate the SELECT set for the rule
            Set<Terminal> selectSet = rule.select();

            // Add entries to the parsing table for each terminal in the SELECT set
            for (Terminal terminal : selectSet) {
                if (!table.containsKey(nonTerminal)) {
                    table.put(nonTerminal, new HashMap<>());
                }

                // Check for conflicts in the parsing table
                if (table.get(nonTerminal).containsKey(terminal)) {
                    throw new InvalidLL1Grammar("Conflict in parsing table for non-terminal " +
                            nonTerminal.getName() + " and terminal " + terminal.getName());
                }
                table.get(nonTerminal).put(terminal, rule);
            }
        }
    }

    public Nonterminal getInitial() {
        return initial;
    }

    public void setInitial(Nonterminal initial) {
        this.initial = initial;
    }

    public List<CFGRule> getRules() {
        return rules;
    }

    public Map<Nonterminal, Map<Terminal, CFGRule>> getTable() {
        return table;
    }

    @Override
    public String toString() {
        return "LL1{" +
                "initial=" + initial +
                ", rules=" + rules +
                ", table=" + table +
                '}';
    }

    public void setRules(List<CFGRule> rules) {
        this.rules = rules;
    }

    public void setTable(Map<Nonterminal, Map<Terminal, CFGRule>> table) {
        this.table = table;
    }

    public Iterable<Expression> parse(List<Terminal> input) throws LL1ParseError {
        Stack<Term> stack = new Stack<>();
        stack.push(initial);
        int inputIndex = 0;

        List<Expression> expressions = new ArrayList<>();

        while (!stack.isEmpty()) {
            Term top = stack.peek();

            if (top instanceof Terminal) {
                if (inputIndex < input.size() && top.equals(input.get(inputIndex))) {
                    stack.pop();
                    inputIndex++;
                } else {
                    throw new LL1ParseError("Unexpected terminal: " + (inputIndex < input.size() ? input.get(inputIndex) : "EOF"));
                }
            } else if (top instanceof Nonterminal) {
                Nonterminal nonTerminal = (Nonterminal) top;
                Terminal currentInput = inputIndex < input.size() ? input.get(inputIndex) : Terminal.EOF;

                if (table.containsKey(nonTerminal) && table.get(nonTerminal).containsKey(currentInput)) {
                    CFGRule rule = table.get(nonTerminal).get(currentInput);
                    stack.pop();
                    List<Term> rhs = new ArrayList<>(rule.getRight().getTerms());
                    for (int i = rhs.size() - 1; i >= 0; i--) {
                        if (!rhs.get(i).equals(Terminal.EPSILON)) {
                            stack.push(rhs.get(i));
                        }
                    }
                } else {
                    throw new LL1ParseError("No rule found for non-terminal " + nonTerminal.getName() +
                            " and input " + currentInput.getName());
                }
            }

            expressions.add(new Expression(new ArrayList<>(stack)));
        }

        return expressions;
    }

    public boolean dontNeedSpaceBefore(String str)
    {
        for (String s : dontNeedSpaceBeforeString){
            if(str.equals(s)){
                return true;
            }
        }
        return false;
    }

    public void printParsingSteps(Iterable<Expression> expressions) {
        List<String> terminals = new ArrayList<>();
        Iterator<Expression> iterator = expressions.iterator();
        Expression expression = iterator.next();
        String output = "";
        String prevOutput = "";

        output += "<S> ->\n";

        while (iterator.hasNext()) {
            List<Term> terms = expression.getTerms();

            if(!terms.isEmpty())
            {
                output += "->";

                for (String terminal : terminals){
                    output += (dontNeedSpaceBefore(terminal) ? "" : " ") + terminal;
                }

                for (int i = terms.size() - 1; i >= 0; i--) {
                    Term term = terms.get(i);

                    output += dontNeedSpaceBefore(term.getName()) ? "" : " ";

                    if (term instanceof Nonterminal) {
                        output += "<" + term.getName() + ">";
                    } else {
                        terminals.add(term.getName());
                        output += term.getName();
                    }
                }

                expression = iterator.next();

                // Check if it isn't the last iteration
                if (iterator.hasNext()) {
                    output += " ->\n";
                }

                if (!output.equals(prevOutput))
                {
                    System.out.print(capitalizeLetters(output, 4));
                }
                prevOutput = output;
                output = "";
            }
        }
    }

    public static String capitalizeLetters(String str, int index) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, index).toUpperCase() + str.substring(index);
    }

    public static String capitalizeLetterWithI(String str) {
        if(str.charAt(0) != 'I'){
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        }
        return str;
    }

    public static List<Terminal> tokenizeInput(String input, List<Terminal> terminals) {
        List<Terminal> tokens = new ArrayList<>();
        String[] words = capitalizeLetterWithI(input).split("\\s+|(?=[.,!])|(?<=[.,!])");
        for (String word : words) {
            boolean found = false;
            for (Terminal terminal : terminals) {
                if (word.equals(terminal.getName())) {
                    tokens.add(terminal);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IllegalArgumentException("Unknown token: " + word);
            }
        }
        return tokens;
    }

}