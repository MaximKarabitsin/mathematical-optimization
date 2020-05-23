package ru.sstu.mathematicaloptimization.methods;

import java.util.function.UnaryOperator;

public abstract class AbstractMethod {

    private UnaryOperator<Double> func;
    private UnaryOperator<Double> funcMin;
    private int numberOfFunctionCalculations = 0;
    private Double result;
    protected Double a;
    protected Double b;
    private Double epsilon;
    private int count;
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

    private Double calculate() {
        if (b - a < epsilon) return getFinalResult();
        calculateInitialValues();
        while (b - a >= epsilon) {
            iteration();
            count++;
        }
        return getFinalResult();
    }

    protected abstract void calculateInitialValues();

    protected abstract void calculateRight();

    protected abstract void calculateLeft();

    private Double getFinalResult() {
        return (a + b) / 2;
    }

    protected abstract void iteration();

    protected Double getFunctionValue(Double x) {
        numberOfFunctionCalculations++;
        return minimum ? func.apply(x) : -func.apply(x);
    }
}
