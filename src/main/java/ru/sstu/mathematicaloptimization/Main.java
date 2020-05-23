package ru.sstu.mathematicaloptimization;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import ru.sstu.mathematicaloptimization.methods.DichotomyMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public class Main {

    public static final Map<String, String> OPERATIONS = new HashMap<String, String>();
    private static final String OPERATIONS_PREFIX = "T(java.lang.Math).";


    static {
        OPERATIONS.put("pow", "Возведение в степень. Например, pow(x, 2)");
        OPERATIONS.put("sqrt", "Возведение в степень. Например, pow(x, 2)");
        OPERATIONS.put("log", "Натуральный логарифм. Например, pow(x, 2)");
        OPERATIONS.put("atan", "Возведение в степень. Например, pow(x, 2)");
        OPERATIONS.put("abs", "Возведение в степень. Например, pow(x, 2)");
        OPERATIONS.put("sin", "Возведение в степень. Например, pow(x, 2)");
        OPERATIONS.put("cos", "Возведение в степень. Например, pow(x, 2)");
        OPERATIONS.put("exp", "Возведение в степень. Например, pow(x, 2)");
        OPERATIONS.put("log10", "Десятичный логарифм. Например, pow(x, 2)");
    }

    public static void main(String[] args) {
/*        ExpressionParser parser = new SpelExpressionParser();
                String s = "T(java.lang.Math).pow(x, 2)";
        Double value = parser.parseExpression(s).getValue(new Data(2.0), Double.class);
        System.out.println(value);*/

//       Double min = 0.0;
//       Double max = 3.0;
//        UnaryOperator<Double> func = x -> Math.pow(x - 2, 2);

        Double min = -6.0;
       Double max = 2.0;
        UnaryOperator<Double> func = x -> Math.pow(x + 5, 4);
        DichotomyMethod dichotomyMethod = new DichotomyMethod(func, min, max, 0.0000000001, true, 0.1);
        Double minimum = dichotomyMethod.getResult();
        System.out.println(minimum);
    }
}

class Data {
    Double x;

    public Data(Double x) {
        this.x = x;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }
}