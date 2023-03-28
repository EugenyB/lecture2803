package main;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.IntStream;

public class IntegralCalculator {
    private double start;
    private double end;
    private int nSteps;

    private double step;
    private DoubleUnaryOperator f;

    public IntegralCalculator(double start, double end, int nSteps, DoubleUnaryOperator f) {
        this.start = start;
        this.end = end;
        this.nSteps = nSteps;
        this.f = f;
        step = (end - start) / nSteps;
    }

    public double calculate() {
        return IntStream.range(0, nSteps).mapToDouble(i -> start + i * step).map(f).map(y -> y * step).sum();
    }
}
