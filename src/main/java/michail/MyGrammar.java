package michail;

import java.util.ArrayList;
import java.util.List;

public class MyGrammar {
    private Nonterminal S, conjunctionPhrase, subject, verb, object, conjunction, pronoun, noun, verbSg, verbPl, dot;
    private Terminal I, you, pythonCoder, errors, cookies, programmer, theory, cat, sleeping, loves, enjoys, likes, wonders, want, wants, love, enjoy, like, wonder, but, or, and, comma, dotSymbol, exclamation;
    private List<CFGRule> rules;
    private List<Terminal> terminals;

    public MyGrammar() {
        // Create instances of non-this.symbols
        this.S = new Nonterminal("S");
        this.conjunctionPhrase = new Nonterminal("Conjunction_phrase");
        this.subject = new Nonterminal("Subject");
        this.verb = new Nonterminal("Verb");
        this.object = new Nonterminal("Object");
        this.conjunction = new Nonterminal("Conjunction");
        this.pronoun = new Nonterminal("Pronoun");
        this.noun = new Nonterminal("Noun");
        this.verbSg = new Nonterminal("Verb_sg");
        this.verbPl = new Nonterminal("Verb_pl");
        this.dot = new Nonterminal("Dot");

        // Create instances of terminal symbols
        this.I = new Terminal("I");
        this.you = new Terminal("you");
        this.pythonCoder = new Terminal("Python coder");
        this.errors = new Terminal("errors");
        this.cookies = new Terminal("cookies");
        this.programmer = new Terminal("programmer");
        this.theory  = new Terminal("theory");
        this.cat = new Terminal("cat");
        this.sleeping = new Terminal("sleeping");
        this.loves = new Terminal("loves");
        this.enjoys = new Terminal("enjoys");
        this.likes = new Terminal("likes");
        this.wonders = new Terminal("wonders");
        this.want = new Terminal("want");
        this.wants = new Terminal("wants");
        this.love = new Terminal("love");
        this.enjoy = new Terminal("enjoy");
        this.like = new Terminal("like");
        this.wonder = new Terminal("wonder");
        this.but = new Terminal("but");
        this.or = new Terminal("or");
        this.and = new Terminal("and");
        this.comma = new Terminal(",");
        this.dotSymbol = new Terminal(".");
        this.exclamation = new Terminal("!");

        // Set the initial non-terminal symbol
        S.setInitial(true);

        // Create a list of all terminals
        this.terminals = List.of(
                I, you, pythonCoder, errors, cookies, programmer, theory, cat, sleeping,
                loves, enjoys, likes, wonders, want, wants, love, enjoy, like, wonder,
                but, or, and, comma, dotSymbol, exclamation
        );

        this.rules = new ArrayList<>();
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
        rules.add(new CFGRule(noun, new Expression(List.of(errors))));
        rules.add(new CFGRule(noun, new Expression(List.of(cookies))));
        rules.add(new CFGRule(noun, new Expression(List.of(programmer))));
        rules.add(new CFGRule(noun, new Expression(List.of(theory))));
        rules.add(new CFGRule(noun, new Expression(List.of(cat))));
        rules.add(new CFGRule(noun, new Expression(List.of(sleeping))));
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
    }

    public List<CFGRule> getRules(){
        return rules;
    }

    public List<Terminal> getTerminals() {
        return terminals;
    }
}
