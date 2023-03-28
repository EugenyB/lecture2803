package main;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        double a = 0;
        double b = Math.PI;
        int n = 100_000_000;

//        long startTime = System.currentTimeMillis();
//        IntegralCalculator calc = new IntegralCalculator(a, b, n, Math::sin);
//        double v = calc.calculate();
//        long finishTime = System.currentTimeMillis();
//        System.out.println("v = " + v);
//        System.out.println(finishTime - startTime);
        int nThreads = 10;
        double delta = (b - a) / nThreads;
        totalResult = 0;
        finished = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < nThreads; i++) {
            RunnableIntegralCalculator calculator = new RunnableIntegralCalculator(a + i * delta, a + i * delta + delta, n / nThreads, Math::sin, this);
            new Thread(calculator).start();
        }
        try {
            synchronized (this) {
                while (finished < nThreads) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted");
        }
        long finishTime = System.currentTimeMillis();
        System.out.println("Result = " + totalResult);
        System.out.println(finishTime - startTime);
    }

    public synchronized void send(double v) {
        totalResult += v;
        finished++;
        notify();
    }

    private double totalResult;
    private int finished;
}
