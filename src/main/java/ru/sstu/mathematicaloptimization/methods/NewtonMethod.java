package ru.sstu.mathematicaloptimization.methods;

import java.util.function.UnaryOperator;

public class NewtonMethod extends AbstractMethod {

    private Double derive2Fx;
    private Double deriveFx;
    private Double x;

    public NewtonMethod(UnaryOperator<Double> func, Double minX, Double maxX, Double epsilon, boolean minimum) {
        super(func, minX, maxX, epsilon, minimum);
        Double derive3Fa = getDerive(a, 3);
        Double deriveFa = getDerive(a, 1);
        if (derive3Fa * deriveFa > 0) {
            x = a;
            deriveFx = deriveFa;
            derive2Fx = getDerive(x, 2);
        } else {
            x = b;
            calculateInitialValues();
        }
    }

    @Override
    protected void calculateInitialValues() {
        deriveFx = getDerive(x, 1);
        derive2Fx = getDerive(x, 2);
    }

    @Override
    protected void calculateRight() {

    }

    @Override
    protected void calculateLeft() {

    }

    @Override
    protected Double calculate() {
        while (Math.abs(deriveFx) > epsilon) {
            iteration();
            count++;
        }
        return x;
    }

    @Override
    protected void iteration() {
        x = x - deriveFx / derive2Fx;
        calculateInitialValues();
    }
}
