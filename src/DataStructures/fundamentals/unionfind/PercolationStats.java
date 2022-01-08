package DataStructures.fundamentals.unionfind;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;
    private static final double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("N <= 0 or T <= 0");
        }
        double[] fraction = new double[trials];
        for (int i = 0; i < trials; i++) {
            int openSites = 0;
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int random1 = StdRandom.uniform(1, n + 1);
                int random2 = StdRandom.uniform(1, n + 1);
                if (!percolation.isOpen(random1, random2)) {
                    percolation.open(random1, random2);
                    openSites++;
                }
            }
            fraction[i] = (double) openSites / (n * n);
        }
        mean = StdStats.mean(fraction);
        stddev = StdStats.stddev(fraction);
        confidenceLo =  mean - ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
        confidenceHi =  mean + ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    // sample mean of percolation threshold
    public double mean() {
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.confidenceHi;
    }

    // test client (see below)
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(a, b);
        String confidence = "[" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]";
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }

}
