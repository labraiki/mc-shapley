package game.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Player {

    protected final String name;
    protected Set<Rule> rules;


    public static String[] parsePlayersNames(String line){
        if (line.contains("{") && line.contains("}")){
            line = line.substring(line.indexOf("{") + 1, line.indexOf("}"));
        }
        return line.replace(" ", "").split("∧|/\\\\|,");
    }

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
