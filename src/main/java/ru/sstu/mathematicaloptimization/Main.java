package ru.sstu.mathematicaloptimization;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import ru.sstu.mathematicaloptimization.methods.*;

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

       Double min = 0.0;
       Double max = 3.0;
        UnaryOperator<Double> func = x -> (x-2)*(x-2);
/*        Double min = -6.0;
        Double max = 2.0;
        UnaryOperator<Double> func = x -> Math.pow(x + 5, 4);*/
/*        AbstractMethod method = new DichotomyMethod(func, min, max, 0.0001, true,0.1);
        System.out.println(method.getResult());
        AbstractMethod method2 = new BisectionMethod(func, min, max, 0.0001, true);
        System.out.println(method2.getResult());*/
/*        AbstractMethod method3 = new GoldenSectionMethod(func, min, max, 0.0001, false);
        System.out.println(method3.getResult());
        System.out.println(method3.getCount());
        AbstractMethod method4 = new FibonacciMethod(func, min, max, 0.0001, false);
        System.out.println(method4.getResult());
        System.out.println(method4.getCount());*/
/*        AbstractMethod method5 = new PowellMethod(func, min, max, 0.0001, true, 1.0);
        System.out.println(method5.getResult());
        System.out.println(method5.getCount());*/
   /*     AbstractMethod method6 = new BolzanoMethod(func, min, max, 0.0001, true);
        System.out.println(method6.getResult());
        System.out.println(method6.getCount());*/
        AbstractMethod method7 = new NewtonMethod(func, min, max, 0.0001, true);
        System.out.println(method7.getResult());
        System.out.println(method7.getCount());
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