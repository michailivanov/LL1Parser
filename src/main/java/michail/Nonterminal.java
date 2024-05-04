package michail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Nonterminal extends Term {
    private List<Expression> reachable;
    private List<CFGRule> destinations;
    private boolean initial;

    public Nonterminal(String name) {
        super(name);
        this.reachable = new ArrayList<>();
        this.destinations = new ArrayList<>();
        this.initial = false;
    }

    @Override
    public Set<Terminal> first() {
        Set<Terminal> firstSet = new HashSet<>();
        for (Expression expression : reachable) {
            firstSet.addAll(expression.first());
        }
        return firstSet;
    }

    @Override
    public boolean epsilonable(Set<Term> exclude) {
        if (exclude == null) {
            exclude = new HashSet<>();
        }
        if (exclude.contains(this)) {
            return false; // Break cycle to avoid infinite loop
        }
        exclude.add(this);
        for (Expression expression : reachable) {
            if (expression.epsilonable(exclude) || expression.isEpsilon()) {
                return true;
            }
        }
        return false;
    }


    public Set<Terminal> follow(Set<Nonterminal> exclude) {
        if (exclude == null) {
            exclude = new HashSet<>();
        }
        Set<Terminal> result = new HashSet<>();
        if (initial) {
            result.add(Terminal.EOF);
        }
        exclude.add(this);
        for (CFGRule rule : destinations) {
            Expression subexpr = rule.getRight().subexpr(this);
            Set<Terminal> subexprFirst = subexpr.first();
            subexprFirst.remove(Terminal.EPSILON);
            result.addAll(subexprFirst);
            if (!exclude.contains(rule.getLeft()) && subexpr.epsilonable(new HashSet<>())) {
                result.addAll(rule.getLeft().follow(exclude));
            }
        }
        return result;
    }

    public boolean isInitial() {
        return initial;
    }

    public void setInitial(boolean initial) {
        this.initial = initial;
    }

    public List<CFGRule> getDestinations() {
        return destinations;
    }

    public List<Expression> getReachable() {
        return reachable;
    }
}
