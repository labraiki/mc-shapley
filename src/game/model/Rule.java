package game.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;

public class Rule {

    protected final String pattern;
    protected final BigInteger playersCount;
    protected final BigDecimal value;

    public static BigDecimal parseValue(String line){
        line = line.replaceAll("→|=>|->", "=").replace(" ", "");
        String value = line.substring(line.indexOf("=") + 1);
        return new BigDecimal(value);
    }

    public Rule(String pattern, BigInteger playersCount, BigDecimal value) {
        this.pattern = pattern;
        this.playersCount = playersCount;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format(">> Rule: %s,%n>> n = %d,%n>> value = %s", pattern, playersCount, value);
    }
}
