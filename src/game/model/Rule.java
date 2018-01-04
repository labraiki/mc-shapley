package game.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class Rule {

    protected final String pattern;
    protected final List<Player> posLiterals;
    protected final List<Player> negLiterals;
    protected final BigDecimal value;
    protected final BigDecimal posShapV;
    protected final BigDecimal negShapV;


    public static BigDecimal parseValue(String line){
        line = line.replaceAll("→|=>|->", "=").replace(" ", "");
        String value = line.substring(line.indexOf("=") + 1);
        return new BigDecimal(value);
    }

    private static long factorial(long number){

        if (number < 0)
            throw new RuntimeException("Can't calculate factorial of negative value.");

        long total = 1;
        for (long i = 2; i <= number; i++){
            total *= i;
        }

        return total;
    }

    public Rule(String pattern, List<Player> posLiterals, List<Player> negLiterals, BigDecimal value) {
        this.pattern = pattern;
        this.posLiterals = posLiterals;
        this.negLiterals = negLiterals;
        this.value = value;
        this.posShapV = calculateShapV(true);
        this.negShapV = calculateShapV(false);
    }

    private BigDecimal calculateShapV(boolean positive){
        long p = posLiterals.size();
        long n = negLiterals.size();

        if (positive && p == 0 || !positive && n == 0)
            return BigDecimal.ZERO;

        long facN = factorial(n);
        long facP = factorial(p);

        BigDecimal numerator = positive ? new BigDecimal(factorial(p - 1) * facN).multiply(value)
                                        : new BigDecimal(facP * factorial(n - 1)).multiply(value.negate());

        BigDecimal denominator = new BigDecimal(factorial(p + n));

        return numerator.divide(denominator);
    }

    public BigDecimal getShapleyVal(Player player) {
        BigDecimal total = BigDecimal.ZERO;

        if (posLiterals.contains(player)){
            total = total.add(posShapV);
        }

        if (negLiterals.contains(player)){
            total = total.add(negShapV);
        }

        return total;
    }

    @Override
    public String toString() {
        return String.format("Rule %s [p = %d, n = %d, value = %s, positive ShapVal  = %s, negative ShapVal = %s]",
                            pattern, posLiterals.size(), negLiterals.size(), value, posShapV, negShapV);
    }
}
