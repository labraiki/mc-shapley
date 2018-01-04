package game.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Player {

    protected final String name;
    protected Set<Rule> rules;


    public Player(String name) {
        this.name = name;
        this.rules = new HashSet<>();
    }

    public Set<Rule> getRules() {
        return Collections.unmodifiableSet(rules);
    }

    public String getName() {
        return name;
    }

    public void addRule(Rule rule){
        this.rules.add(rule);
    }

    public BigDecimal calculateShapley() {
        BigDecimal total = BigDecimal.ZERO;
        for (Rule rule : rules) {
            total = total.add(rule.getShapleyVal(this));
        }
        return total;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equalsIgnoreCase(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
