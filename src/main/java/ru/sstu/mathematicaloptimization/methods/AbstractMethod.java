package ru.sstu.mathematicaloptimization.methods;

import java.util.function.UnaryOperator;

public abstract class AbstractMethod {
    private static final double DX = .0001;

    private UnaryOperator<Double> func;
    private int numberOfFunctionCalculations = 0;
    private Double result;
    protected Double a;
    protected Double b;
    protected Double epsilon;
    protected int count;
    protected Double left;
    protected Double funcLeft;
    protected Double right;
    protected Double funcRight;
    private boolean minimum;

    public AbstractMethod(UnaryOperator<Double> func, Double minX, Double maxX, Double epsilon, boolean minimum) {
        this.func = func;
        this.a = minX;
        this.b = maxX;
        this.epsilon = epsilon;
        this.minimum = minimum;
    }

    public Double getResult() {
        if (result == null) result = calculate();
        return result;
    }

    protected Double calculate() {
        if (b - a < epsilon) return getFinalResult();
        calculateInitialValues();
        while (b - a >= epsilon) {
            iteration();
            count++;
        }
        return getFinalResult();
    }

    protected abstract void calculateInitialValues();

    protected Double getFinalResult() {
        return (a + b) / 2;
    }

    protected abstract void iteration();

    protected Double getFunctionValue(Double x) {
        numberOfFunctionCalculations++;
        return minimum ? func.apply(x) : -func.apply(x);
    }

    protected Double getDerive(Double x, int n) {
        numberOfFunctionCalculations++;
        UnaryOperator<Double> derive = func;
        for (int i = 0; i < n; i++) {
            derive = derive(derive);
        }
        return derive.apply(x);
    }

    private UnaryOperator<Double> derive(UnaryOperator<Double> f) {
        return (x) -> (f.apply(x + DX) - f.apply(x)) / DX;
    }

    public int getNumberOfFunctionCalculations() {
        return numberOfFunctionCalculations;
    }

    public int getCount() {
        return count;
    }
}
