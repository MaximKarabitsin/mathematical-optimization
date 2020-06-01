package ru.sstu.mathematicaloptimization.methods;

import java.util.function.UnaryOperator;

public class DichotomyMethod extends AbstractMethod {
    private Double parameter;
    private Double delta;

    public DichotomyMethod(UnaryOperator<Double> func, Double minX, Double maxX, Double epsilon, boolean foundMinimum, Double parameter) {
        super(func, minX, maxX, epsilon, foundMinimum);
        this.parameter = parameter;
    }

    @Override
    protected void calculateInitialValues() {
        delta = (b - a) / 2;
        calculateLeft();
        calculateRight();
        funcRight = getFunctionValue(right);
        funcLeft = getFunctionValue(left);
    }

    protected void calculateRight() {
        right = a + delta * (1 + parameter);
    }

    protected void calculateLeft() {
        left = a + delta * (1 - parameter);
    }

    @Override
    protected void iteration() {
        if (funcLeft > funcRight) {
            a = left;
        } else {
            b = right;
        }
        calculateInitialValues();
    }
}
