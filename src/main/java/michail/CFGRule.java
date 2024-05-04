package michail;

import java.util.HashSet;
import java.util.Set;

class CFGRule {
    private Nonterminal left;
    private Expression right;

    public CFGRule(Nonterminal left, Expression right) {
        this.left = left;
        this.right = right;
        this.left.getReachable().add(right);
        for (Term term : right.getTerms()) {
            if (term instanceof Nonterminal) {
                ((Nonterminal) term).getDestinations().add(this);
            }
        }
    }

    public Set<Terminal> select() {
        Set<Terminal> result = new HashSet<>();
        if (right.getTerms().contains(Terminal.EPSILON)) {
            result.addAll(left.follow(null));
        } else if (!right.epsilonable(new HashSet<>())) {
            result.addAll(right.first());
            result.remove(Terminal.EPSILON);
        } else {
            result.addAll(left.follow(null));
            result.addAll(right.first());
            result.remove(Terminal.EPSILON);
        }
        return result;
    }

    public Nonterminal getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "CFGRule{" +
                "right=" + right +
                ", left=" + left +
                '}';
    }
}