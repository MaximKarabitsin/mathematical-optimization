package ru.sstu.mathematicaloptimization.methods;

import java.util.function.UnaryOperator;

public class CubicApproximationMethod extends AbstractMethod {

    private Double deriveFx;
    private Double deriveFx1;
    private Double deriveFx2;
    private Double fx1;
    private Double fx2;
    private Double x;

    public CubicApproximationMethod(UnaryOperator<Double> func, Double minX, Double maxX, Double epsilon, boolean minimum) {
        super(func, minX, maxX, epsilon, minimum);
        fx1 = getFunctionValue(a);
        fx2 = getFunctionValue(b);
        deriveFx1 = getDerive(a, 1);
        deriveFx2 = getDerive(b, 1);
    }

    @Override
    protected void calculateInitialValues() {
        double z = deriveFx1 + deriveFx2 - 3 * (fx2 - fx1) / (b - a);
        double omega = Math.sqrt(z * z - deriveFx1 * deriveFx2);
        double nu = (omega + z - deriveFx1) / (2 * omega - deriveFx1 + deriveFx2);
        x = a + nu * (b - a);
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
        if (deriveFx * deriveFx1 < 0) {
            b = x;
            fx2 = getFunctionValue(b);
            deriveFx2 = deriveFx;
        } else {
            a = x;
            fx1 = getFunctionValue(a);
            deriveFx1 = deriveFx;
        }
        calculateInitialValues();
    }
}
