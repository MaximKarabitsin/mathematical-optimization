package ru.sstu.mathematicaloptimization.methods;

import java.util.function.UnaryOperator;

public class BolzanoMethod extends AbstractMethod {

    private Double deriveFa;
    private Double deriveFb;
    private Double deriveFx;
    private Double x;

    public BolzanoMethod(UnaryOperator<Double> func, Double minX, Double maxX, Double epsilon, boolean minimum) {
        super(func, minX, maxX, epsilon, minimum);
        deriveFa = getDerive(a, 1);
        deriveFb = getDerive(b, 1);
    }

    @Override
    protected void calculateInitialValues() {
        x = (a + b) / 2;
        deriveFx = getDerive(x, 1);
    }

    @Override
    protected void iteration() {
        if (deriveFx * deriveFa < 0) {
            b = x;
            deriveFb = deriveFx;
        } else {
            a = x;
            deriveFa = deriveFx;
        }
        calculateInitialValues();
    }
}
