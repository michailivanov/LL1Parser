package michail;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class Term {
    protected String name;

    public Term(String name) {
        this.name = name;
    }

    public abstract Set<Terminal> first();

    public abstract boolean epsilonable(Set<Term> exclude);

    public static List<Term> generate(String... names) {
        List<Term> terms = new ArrayList<>();
        for (String name : names) {
            terms.add(new Terminal(name));
        }
        return terms;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Term{" +
                "name='" + name + '\'' +
                '}';
    }

    // Equals and hashCode methods based on name
}

