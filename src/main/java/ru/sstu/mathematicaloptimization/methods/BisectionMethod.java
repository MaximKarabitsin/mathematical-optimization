package ru.sstu.mathematicaloptimization.methods;

import java.util.function.UnaryOperator;

public class BisectionMethod extends AbstractMethod {

    private Double delta;
    private Double middle;
    private Double funcMiddle;

    public BisectionMethod(UnaryOperator<Double> func, Double minX, Double maxX, Double epsilon, boolean minimum) {
        super(func, minX, maxX, epsilon, minimum);
        delta = (b - a) / 2;
        middle = (b + a) / 2;
        funcMiddle = getFunctionValue(middle);
    }

    @Override
    protected void calculateInitialValues() {
        delta /= 2;
        right = b - delta;
        left = a + delta;
    }

    @Override
    protected void iteration() {
        funcLeft = getFunctionValue(left);
        if (funcLeft < funcMiddle) {
            b = middle;
            middle = left;
            funcMiddle = funcLeft;
        } else {
            funcRight = getFunctionValue(right);
            if (funcRight < funcMiddle) {
                a = middle;
                middle = right;
                funcMiddle = funcRight;
            } else {
                a = left;
                b = right;
            }
        }
        calculateInitialValues();
    }
}
