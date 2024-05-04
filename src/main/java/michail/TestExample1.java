package michail;

import java.util.ArrayList;
import java.util.List;

public class TestExample1 {
    public static void main(String[] args) {
        // Create instances of non-terminal symbols
        Nonterminal S = new Nonterminal("S");
        Nonterminal conjunctionPhrase = new Nonterminal("Conjunction_phrase");
        Nonterminal subject = new Nonterminal("Subject");
        Nonterminal verb = new Nonterminal("Verb");
        Nonterminal object = new Nonterminal("Object");
        Nonterminal conjunction = new Nonterminal("Conjunction");
        Nonterminal pronoun = new Nonterminal("Pronoun");
        Nonterminal noun = new Nonterminal("Noun");
        Nonterminal verbSg = new Nonterminal("Verb_sg");
        Nonterminal verbPl = new Nonterminal("Verb_pl");
        Nonterminal dot = new Nonterminal("Dot");

        // Create instances of terminal symbols
        Terminal I = new Terminal("I");
        Terminal you = new Terminal("you");
        Terminal pythonCoder = new Terminal("Python coder");
        Terminal javaDev = new Terminal("Java dev");
        Terminal cppGuru = new Terminal("C++ guru");
        Terminal whitespace = new Terminal("whitespace");
        Terminal classes = new Terminal("classes");
        Terminal pointers = new Terminal("pointers");
        Terminal it = new Terminal("it");
        Terminal loves = new Terminal("loves");
        Terminal enjoys = new Terminal("enjoys");
        Terminal likes = new Terminal("likes");
        Terminal wonders = new Terminal("wonders");
        Terminal want = new Terminal("want");
        Terminal wants = new Terminal("wants");
        Terminal love = new Terminal("love");
        Terminal enjoy = new Terminal("enjoy");
        Terminal like = new Terminal("like");
        Terminal wonder = new Terminal("wonder");
        Terminal but = new Terminal("but");
        Terminal or = new Terminal("or");
        Terminal and = new Terminal("and");
        Terminal comma = new Terminal(",");
        Terminal dotSymbol = new Terminal(".");
        Terminal exclamation = new Terminal("!");

        // Create grammar rules
        List<CFGRule> rules = new ArrayList<>();
        rules.add(new CFGRule(S, new Expression(List.of(subject, verb, object, conjunctionPhrase, dot))));
        rules.add(new CFGRule(conjunctionPhrase, new Expression(List.of(comma, conjunction, subject, verb, object, conjunctionPhrase))));
        rules.add(new CFGRule(conjunctionPhrase, new Expression(List.of(Terminal.EPSILON))));
        rules.add(new CFGRule(subject, new Expression(List.of(pronoun))));
        rules.add(new CFGRule(subject, new Expression(List.of(noun))));
        rules.add(new CFGRule(verb, new Expression(List.of(verbSg))));
        rules.add(new CFGRule(verb, new Expression(List.of(verbPl))));
        rules.add(new CFGRule(object, new Expression(List.of(noun))));
        rules.add(new CFGRule(conjunction, new Expression(List.of(but))));
        rules.add(new CFGRule(conjunction, new Expression(List.of(or))));
        rules.add(new CFGRule(conjunction, new Expression(List.of(and))));
        rules.add(new CFGRule(pronoun, new Expression(List.of(I))));
        rules.add(new CFGRule(pronoun, new Expression(List.of(you))));
        rules.add(new CFGRule(noun, new Expression(List.of(pythonCoder))));
        rules.add(new CFGRule(noun, new Expression(List.of(javaDev))));
        rules.add(new CFGRule(noun, new Expression(List.of(cppGuru))));
        rules.add(new CFGRule(noun, new Expression(List.of(whitespace))));
        rules.add(new CFGRule(noun, new Expression(List.of(classes))));
        rules.add(new CFGRule(noun, new Expression(List.of(pointers))));
        rules.add(new CFGRule(noun, new Expression(List.of(it))));
        rules.add(new CFGRule(verbSg, new Expression(List.of(loves))));
        rules.add(new CFGRule(verbSg, new Expression(List.of(enjoys))));
        rules.add(new CFGRule(verbSg, new Expression(List.of(likes))));
        rules.add(new CFGRule(verbSg, new Expression(List.of(wonders))));
        rules.add(new CFGRule(verbSg, new Expression(List.of(wants))));
        rules.add(new CFGRule(verbPl, new Expression(List.of(love))));
        rules.add(new CFGRule(verbPl, new Expression(List.of(enjoy))));
        rules.add(new CFGRule(verbPl, new Expression(List.of(like))));
        rules.add(new CFGRule(verbPl, new Expression(List.of(wonder))));
        rules.add(new CFGRule(verbPl, new Expression(List.of(want))));
        rules.add(new CFGRule(dot, new Expression(List.of(dotSymbol))));
        rules.add(new CFGRule(dot, new Expression(List.of(exclamation))));

        // Set the initial non-terminal symbol
        S.setInitial(true);

        // Example 1: Calculating FIRST sets
        System.out.println("Example 1: Calculating FIRST sets");
        System.out.println("FIRST(S) = " + S.first());
        System.out.println("FIRST(Subject) = " + subject.first());
        System.out.println("FIRST(Verb) = " + verb.first());
        System.out.println("FIRST(Object) = " + object.first());
        System.out.println("FIRST(Conjunction_phrase) = " + conjunctionPhrase.first());
        System.out.println("FIRST(Dot) = " + dot.first());
        System.out.println("FIRST(conjunction) = " + conjunction.first());
        System.out.println("FIRST(pronoun) = " + pronoun.first());
        System.out.println("FIRST(noun) = " + noun.first());
        System.out.println("FIRST(verbSg) = " + verbSg.first());
        System.out.println("FIRST(verbPl) = " + verbPl.first());

        System.out.println();

        // Example 2: Calculating FOLLOW sets
        System.out.println("Example 2: Calculating FOLLOW sets");
        System.out.println("FOLLOW(S) = " + S.follow(null));
        System.out.println("FOLLOW(Subject) = " + subject.follow(null));
        System.out.println("FOLLOW(Verb) = " + verb.follow(null));
        System.out.println("FOLLOW(Object) = " + object.follow(null));
        System.out.println("FOLLOW(Conjunction_phrase) = " + conjunctionPhrase.follow(null));
        System.out.println("FOLLOW(Dot) = " + dot.follow(null));
        System.out.println("FOLLOW(conjunction) = " + conjunction.follow(null));
        System.out.println("FOLLOW(pronoun) = " + pronoun.follow(null));
        System.out.println("FOLLOW(noun) = " + noun.follow(null));
        System.out.println("FOLLOW(verbSg) = " + verbSg.follow(null));
        System.out.println("FOLLOW(verbPl) = " + verbPl.follow(null));
        System.out.println();

        // Example 3: Checking if symbols are epsilonable
        System.out.println("Example 3: Checking if symbols are epsilonable");
        System.out.println("Is S epsilonable? " + S.epsilonable(null));
        System.out.println("Is Subject epsilonable? " + subject.epsilonable(null));
        System.out.println("Is Verb epsilonable? " + verb.epsilonable(null));
        System.out.println("Is Object epsilonable? " + object.epsilonable(null));
        System.out.println("Is Conjunction_phrase epsilonable? " + conjunctionPhrase.epsilonable(null));
        System.out.println("Is Dot epsilonable? " + dot.epsilonable(null));
        System.out.println("Is conjunction epsilonable? " + conjunction.epsilonable(null));
        System.out.println("Is pronoun epsilonable? " + pronoun.epsilonable(null));
        System.out.println("Is noun) epsilonable? " + noun.epsilonable(null));
        System.out.println("Is verbSg epsilonable? " + verbSg.epsilonable(null));
        System.out.println("Is verbPl epsilonable? " + verbPl.epsilonable(null));
        System.out.println();

        // Example 4: Calculating SELECT sets for rules
        System.out.println("Example 4: Calculating SELECT sets for rules");
        for (CFGRule rule : rules) {
            System.out.println("SELECT(" + rule + ") =\n" + rule.select());
        }
        System.out.println();

        // Example 5: Checking if expressions are epsilonable
        System.out.println("Example 5: Checking if expressions are epsilonable");
        Expression expr1 = new Expression(List.of(subject, verb, object, conjunctionPhrase, dot));
        Expression expr2 = new Expression(List.of(and, conjunction, subject, verb, object, conjunctionPhrase));
        Expression expr3 = new Expression(List.of(Terminal.EPSILON));
        System.out.println("Is " + expr1 + " epsilonable? " + expr1.epsilonable(null));
        System.out.println("Is " + expr2 + " epsilonable? " + expr2.epsilonable(null));
        System.out.println("Is " + expr3 + " epsilonable? " + expr3.epsilonable(null));
        System.out.println();

        // Example 6: Replacing non-terminals in expressions
        System.out.println("Example 6: Replacing non-terminals in expressions");
        Expression expr4 = new Expression(List.of(subject, verb, object));
        System.out.println("Original expression: " + expr4);
        Expression replacedExpr = expr4.lreplace(subject, new Expression(List.of(I)));
        System.out.println("Replaced expression: " + replacedExpr);
        System.out.println();

        // Example 7: Checking if expressions are terminal
        System.out.println("Example 7: Checking if expressions are terminal");
        Expression expr5 = new Expression(List.of(I, love, pointers, dotSymbol));
        Expression expr6 = new Expression(List.of(subject, verb, object, dot));
        System.out.println("Is " + expr5 + " terminal? " + expr5.terminal());
        System.out.println("Is " + expr6 + " terminal? " + expr6.terminal());
        System.out.println();

        // Example 8: Finding the leftmost non-terminal in expressions
        System.out.println("Example 8: Finding the leftmost non-terminal in expressions");
        Expression expr7 = new Expression(List.of(I, love, object, dot));
        Expression expr8 = new Expression(List.of(I, love, pointers, dotSymbol));
        System.out.println("Leftmost non-terminal in " + expr7 + ": " + expr7.lfnon());
        try {
            System.out.println("Leftmost non-terminal in " + expr8 + ": " + expr8.lfnon());
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
    }
}
