package michail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Expression {
    private List<Term> terms;

    public Expression(List<Term> terms) {
        this.terms = terms;
    }

    public Set<Terminal> first() {
        Set<Terminal> result = new HashSet<>();
        for (Term term : terms) {
            Set<Terminal> termFirst = term.first();
            result.addAll(termFirst);
            if (!termFirst.contains(Terminal.EPSILON)) {
                return result;
            }
        }
        return result;
    }

    public Expression subexpr(Nonterminal nonterm) {
        int index = terms.indexOf(nonterm);
        if (index != -1) {
            return new Expression(terms.subList(index + 1, terms.size()));
        }
        // Return an empty expression or handle the case gracefully
        return new Expression(new ArrayList<>());
    }

    public boolean epsilonable(Set<Term> exclude) {
        if (exclude == null) {
            exclude = new HashSet<>();
        }
        for (Term term : terms) {
            if (!exclude.contains(term)) {
                if (!term.epsilonable(exclude)) {
                    return false;
                }
                exclude.add(term);
            }
        }
        return true;
    }

    public Expression lreplace(Nonterminal target, Expression expression) {
        int index = terms.indexOf(target);
        if (index != -1) {
            List<Term> newTerms = new ArrayList<>(terms);
            newTerms.remove(index);
            newTerms.addAll(index, expression.getTerms());
            return new Expression(newTerms);
        }
        return this;
    }

    public boolean terminal() {
        return terms.stream().allMatch(term -> term instanceof Terminal);
    }

    public Nonterminal lfnon() {
        for (Term term : terms) {
            if (term instanceof Nonterminal) {
                return (Nonterminal) term;
            }
        }
        throw new RuntimeException("Expression is all terminal.");
    }

    public List<Term> getTerms() {
        return terms;
    }

    public boolean isEpsilon() {
        for (Term term : terms) {
            if (!(term instanceof Terminal) || !((Terminal) term).isEpsilon()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "terms=" + terms +
                '}';
    }
}
