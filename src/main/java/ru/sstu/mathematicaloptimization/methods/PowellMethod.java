package ru.sstu.mathematicaloptimization.methods;

import java.util.function.UnaryOperator;

public class PowellMethod extends AbstractMethod {

    private Double h;
    private double[] x = new double[3];
    private double[] fx = new double[3];
    private double xMin;
    private double fxMin;
    private double xCurr;
    private double fxCurr;

    public PowellMethod(UnaryOperator<Double> func, Double minX, Double maxX, Double epsilon, boolean minimum, Double h) {
        super(func, minX, maxX, epsilon, minimum);
        this.h = h;
    }

    @Override
    protected void calculateInitialValues() {
        x[0] = (b + a) / 2;
        fx[0] = getFunctionValue(x[0]);
    }

    @Override
    protected void calculateRight() {
    }

    @Override
    protected void calculateLeft() {
    }

    @Override
    protected Double calculate() {
        calculateInitialValues();
        while (true) {
            count++;
            x[1] = x[0] + h;
            fx[1] = getFunctionValue(x[1]);
            if (fx[0] > fx[1]) {
                x[2] = x[0] + 2 * h;
            } else {
                x[2] = x[0] - h;
            }
            fx[2] = getFunctionValue(x[2]);
            findMin();
            sort();
            findCurr();
            if (Math.abs(xCurr - xMin) < epsilon) break;
            fxCurr = getFunctionValue(xCurr);
            if (fxCurr < fxMin) {
                if (xCurr < x[0]) {
                    x[0] = x[0] - h;
                    fx[0] = getFunctionValue(x[0]);
                } else if (xCurr > x[2]) {
                    x[0] = x[2] + h;
                    fx[0] = getFunctionValue(x[0]);
                } else {
                    x[0] = xCurr;
                    fx[0] = fxCurr;
                }
            } else {
                x[0] = xMin;
                fx[0] = fxMin;
            }
        }
        return getFinalResult();
    }

    private void sort() {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < 2; i++) {
                if (x[i] > x[i + 1]) {
                    flag = true;
                    double temp = x[i];
                    x[i] = x[i + 1];
                    x[i + 1] = temp;
                    temp = fx[i];
                    fx[i] = fx[i + 1];
                    fx[i + 1] = temp;
                }
            }
        }
    }

    private void findCurr() {
        xCurr = (x[1] + x[0]) / 2;
        double a1 = (fx[1] - fx[0]) / (x[1] - x[0]);
        double a2 = 1.0 / (x[2] - x[1]);
        a2 *= (fx[2] - fx[0]) / (x[2] - x[0]) - a1;
        xCurr -= a1 / (2 * a2);
    }

    @Override
    protected Double getFinalResult() {
        return xCurr;
    }

    @Override
    protected void iteration() {
    }

    private void findMin() {
        xMin = x[0];
        fxMin = fx[0];
        for (int i = 1; i < 3; i++) {
            if (fx[i] < fxMin) {
                fxMin = fx[i];
                xMin = x[i];
            }
        }
    }
}
