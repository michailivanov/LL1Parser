package michail;

import java.util.Set;

public abstract class Term {
    protected String name;

    public Term(String name) {
        this.name = name;
    }

    public abstract Set<Terminal> first();

    public abstract boolean epsilonable(Set<Term> exclude);

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Term{" +
                "name='" + name + '\'' +
                '}';
    }
}

