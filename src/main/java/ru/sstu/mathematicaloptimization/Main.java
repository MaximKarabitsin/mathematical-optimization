package ru.sstu.mathematicaloptimization;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import ru.sstu.mathematicaloptimization.methods.*;

import java.util.*;
import java.util.function.UnaryOperator;

public class Main {
    public static final Map<String, AbstractMethod> METHODS = new LinkedHashMap<>();
    public static final Map<String, String> OPERATIONS = new HashMap<String, String>();
    private static final String OPERATIONS_PREFIX = "T(java.lang.Math).";
    private static final Scanner SCANNER = new Scanner(System.in);

    private static String func = "pow(x-2, 2)";
    private static double epsilon = 0.0001;
    private static double min = 0.0;
    private static double max = 3.0;
    private static boolean minimum = true;

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
        UnaryOperator<Double> func1 = getFunc();
        System.out.println(func1.apply(3.0));
        while (true) {
            System.out.println("\n#########################################################");
            System.out.println("### Numerical methods of one-dimensional optimization ###");
            System.out.println("Accuracy: " + epsilon);
            System.out.println("xMin = " + min);
            System.out.println("xMax = " + max);
            System.out.println("Function: " + func);
            System.out.println("Find the" + (minimum ? "minimum" : "maximum"));
            System.out.println("** Actions: **");
            System.out.println("1 - change accuracy");
            System.out.println("2 - change xMin and xMax");
            System.out.println("3 - change function");
            System.out.println("4 - change to finding the " + (minimum ? "maximum" : "minimum"));
            System.out.println("5 - execute method");
            System.out.println("6 - compare methods");

            System.out.print("Enter action number -> ");
            if (SCANNER.hasNextInt()) {
                int action = SCANNER.nextInt();
                switch (action) {
                    case 1:
                        changeAccuracy();
                        break;
                    case 2:
                        changeX();
                        break;
                    case 3:
                        changeFunction();
                        break;
                    case 4:
                        minimum = !minimum;
                        break;
                    case 5:
                        executeMethod();
                        break;
                    case 6:
                        compareMethods();
                        break;
                }
            } else {
                System.out.println("Error! Unknown action.");
            }
        }
    }

    private static void changeAccuracy() {
        System.out.print("\nEnter accuracy -> ");
        if (SCANNER.hasNextDouble()) {
            epsilon = SCANNER.nextDouble();
        } else {
            System.out.println("Error!");
        }
    }

    private static void changeX() {
        System.out.print("\nEnter xMin -> ");
        if (SCANNER.hasNextDouble()) {
            min = SCANNER.nextDouble();
            System.out.print("Enter xMax -> ");
            if (SCANNER.hasNextDouble()) {
                max = SCANNER.nextDouble();
                if (min > max) System.out.println("Error! xMin > xMax");
            } else {
                System.out.println("Error!");
            }
        } else {
            System.out.println("Error!");
        }
    }

    private static void changeFunction() {
        System.out.println("\nAvailable operations:");
        OPERATIONS.forEach((op, desc) -> System.out.println(op + " - " + desc));
        System.out.print("Enter function -> ");
        if (SCANNER.hasNext()) {
            func = SCANNER.next();
        }
    }

    private static void executeMethod() {
        System.out.println("\nAvailable methods:");
        Iterator<Map.Entry<String, AbstractMethod>> iterator = METHODS.entrySet().iterator();
        int i = 1;
        while (iterator.hasNext()) {
            System.out.println(i + " - " + iterator.next().getKey());
            i++;
        }
        System.out.print("Enter method number -> ");
        if (SCANNER.hasNextInt()) {
            AbstractMethod method;
            int methodNumber = SCANNER.nextInt();
            switch (methodNumber) {
                case 1:
                    method = new DichotomyMethod(getFunc(), min, max, epsilon, minimum, 0.3);
                    break;
                case 2:
                    method = new BisectionMethod(getFunc(), min, max, epsilon, minimum);
                    break;
                case 3:
                    method = new GoldenSectionMethod(getFunc(), min, max, epsilon, minimum);
                    break;
                case 4:
                    method = new FibonacciMethod(getFunc(), min, max, epsilon, minimum);
                    break;
                case 5:
                    method = new PowellMethod(getFunc(), min, max, epsilon, minimum, 1.0);
                    break;
                case 6:
                    method = new BolzanoMethod(getFunc(), min, max, epsilon, minimum);
                    break;
                case 7:
                    method = new NewtonMethod(getFunc(), min, max, epsilon, minimum);
                    break;
                case 8:
                    method = new SecantMethod(getFunc(), min, max, epsilon, minimum);
                    break;
                case 9:
                    method = new CubicApproximationMethod(getFunc(), min, max, epsilon, minimum);
                    break;
                default:
                    throw new RuntimeException("Unknown method!");
            }
            System.out.println("Result: " + method.getResult());
            System.out.println("Number of iterations: " + method.getCount());
            System.out.println("Number of function and derivative calculations: " + method.getNumberOfFunctionCalculations());
        } else {
            System.out.println("Error! Unknown method.");
        }
    }

    private static void compareMethods() {
        initMethods();
        double average = .0;
        for (AbstractMethod method : METHODS.values()) {
            average += method.getResult();
        }
        average /= METHODS.size();
        System.out.println("Average = " + average);
        System.out.printf("| %30s | %8s | %8s | %4s | %4s |\n", "Methods", "Result", "Delta", "Number of iterations", "Number calculations");
        double finalAverage = average;
        METHODS.forEach((name, method) -> {
            System.out.printf("| %30s | %8.5f | %8.5f | %20d | %19d |\n", name, method.getResult(), Math.abs(method.getResult() - finalAverage), method.getCount(), method.getNumberOfFunctionCalculations());
        });
    }

    private static void initMethods() {
        METHODS.put("Dichotomy method", new DichotomyMethod(getFunc(), min, max, epsilon, minimum, 0.3));
        METHODS.put("Bisection method", new BisectionMethod(getFunc(), min, max, epsilon, minimum));
        METHODS.put("Golden section method", new GoldenSectionMethod(getFunc(), min, max, epsilon, minimum));
        METHODS.put("Fibonacci method", new FibonacciMethod(getFunc(), min, max, epsilon, minimum));
        METHODS.put("Powell method", new PowellMethod(getFunc(), min, max, epsilon, minimum, 1.0));
        METHODS.put("Bolzano method", new BolzanoMethod(getFunc(), min, max, epsilon, minimum));
        METHODS.put("Newton method", new NewtonMethod(getFunc(), min, max, epsilon, minimum));
        METHODS.put("Secant method", new SecantMethod(getFunc(), min, max, epsilon, minimum));
        METHODS.put("Cubic approximation method", new CubicApproximationMethod(getFunc(), min, max, epsilon, minimum));
    }

    private static UnaryOperator<Double> getFunc() {
        class Data {
            private Double x;

            private Data(Double x) {
                this.x = x;
            }

            public Double getX() {
                return x;
            }
        }
        String expressionString = func;
        for (String operation : OPERATIONS.keySet()) {
            expressionString = expressionString.replaceAll(operation, OPERATIONS_PREFIX + operation);
        }
        Expression expression = new SpelExpressionParser().parseExpression(expressionString);
        return x -> expression.getValue(new Data(x), Double.class);
    }
}