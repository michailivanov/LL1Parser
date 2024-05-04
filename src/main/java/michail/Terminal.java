package michail;

import java.util.HashSet;
import java.util.Set;

class Terminal extends Term {
    public Terminal(String name) {
        super(name);
    }

    @Override
    public Set<Terminal> first() {
        Set<Terminal> firstSet = new HashSet<>();
        firstSet.add(this);
        return firstSet;
    }

    @Override
    public boolean epsilonable(Set<Term> exclude) {
        return this.equals(Terminal.EPSILON);
    }

    public static final Terminal EPSILON = new Terminal("ε");
    public static final Terminal EOF = new Terminal("#");

    public boolean isEpsilon() {
        return this == EPSILON;
    }
}
