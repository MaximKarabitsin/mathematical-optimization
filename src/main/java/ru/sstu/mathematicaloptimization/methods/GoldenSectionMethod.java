package ru.sstu.mathematicaloptimization.methods;

import java.util.function.UnaryOperator;

public class GoldenSectionMethod extends AbstractMethod {

    private final static Double T = (Math.sqrt(5) - 1) / 2;

    public GoldenSectionMethod(UnaryOperator<Double> func, Double minX, Double maxX, Double epsilon, boolean minimum) {
        super(func, minX, maxX, epsilon, minimum);
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
        right = a + T * (b - a);
    }

    @Override
    protected void calculateLeft() {
        left = a + (1.0 - T) * (b - a);
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
}
