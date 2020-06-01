package ru.sstu.mathematicaloptimization.methods;

import java.util.function.UnaryOperator;

public class SecantMethod extends AbstractMethod {

    private Double deriveFxPrev;
    private Double xPrev;
    private Double deriveFx;
    private Double x;

    public SecantMethod(UnaryOperator<Double> func, Double minX, Double maxX, Double epsilon, boolean minimum) {
        super(func, minX, maxX, epsilon, minimum);
        Double derive3Fa = getDerive(a, 3);
        Double deriveFa = getDerive(a, 1);
        Double deriveFb = getDerive(b, 1);
        if (derive3Fa * deriveFa > 0) {
            xPrev = a;
            deriveFxPrev = deriveFa;
        } else {
            xPrev = b;
            deriveFxPrev = deriveFb;
        }
        x = (a * deriveFb - b * deriveFa) / (deriveFb - deriveFa);
    }

    @Override
    protected void calculateInitialValues() {
        deriveFx = getDerive(x, 1);
    }

    @Override
    protected Double calculate() {
        calculateInitialValues();
        count++;
        while (Math.abs(deriveFx) > epsilon) {
            iteration();
            count++;
        }
        return x;
    }

    @Override
    protected void iteration() {
        x = x - deriveFx * (x - xPrev) / (deriveFx - deriveFxPrev);
        calculateInitialValues();
    }
}
