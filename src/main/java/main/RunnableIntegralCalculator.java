package main;

import java.util.function.DoubleUnaryOperator;

public class RunnableIntegralCalculator implements Runnable {

    private Main main;

    private IntegralCalculator calculator;

    public RunnableIntegralCalculator(double start, double end, int nSteps, DoubleUnaryOperator f, Main main) {
        calculator = new IntegralCalculator(start, end, nSteps, f);
        this.main = main;
    }

    @Override
    public void run() {
        double v = calculator.calculate();
        main.send(v);
    }
}
