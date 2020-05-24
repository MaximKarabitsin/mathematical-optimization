package ru.sstu.mathematicaloptimization.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class FibonacciMethod extends AbstractMethod {

    private final static List<Double> FIBONACCI = new ArrayList<>();
    private int n;

    static {
        FIBONACCI.add(1.0);
        FIBONACCI.add(1.0);
    }

    public FibonacciMethod(UnaryOperator<Double> func, Double minX, Double maxX, Double epsilon, boolean minimum) {
        super(func, minX, maxX, epsilon, minimum);
        double delta = b - a;
        while (epsilon < (delta) / getFibonacci(n)) {
            n++;
        }
    }

    @Override
    protected void calculateInitialValues() {
        calculateLeft();
        calculateRight();
        funcRight = getFunctionValue(right);
        funcLeft = getFunctionValue(left);
    }

    @Override
    protected void calculateRight() {
        right = a + getFibonacci(n - count) / getFibonacci(n - count + 1) * (b - a);
    }

    @Override
    protected void calculateLeft() {
        left = a +  getFibonacci(n - count-1) / getFibonacci(n - count + 1)* (b - a);
    }

    @Override
    protected void iteration() {
        if (funcLeft > funcRight) {
            a = left;
            left = right;
            funcLeft = funcRight;
            calculateRight();
            funcRight = getFunctionValue(right);
        } else {
            b = right;
            right = left;
            funcRight = funcLeft;
            calculateLeft();
            funcLeft = getFunctionValue(left);
        }
    }

    private Double getFibonacci(int n) {
        if (FIBONACCI.size() <= n) {
            FIBONACCI.add(n, getFibonacci(n - 1) + getFibonacci(n - 2));
        }
        return FIBONACCI.get(n);
    }
}
