package DataStructures.fundamentals.unionfind;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    /**
     *The model.
     * We model a percolation system using an n-by-n grid of sites.
     * Each site is either open or blocked.
     * A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites.
     * We say the system percolates if there is a full site in the bottom row.
     * In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open site on the bottom row.
     * (For the insulating/metallic materials example, the open sites correspond to metallic materials,
     * so that a system that percolates has a metallic path from top to bottom, with full sites conducting.
     * For the porous substance example, the open sites correspond to empty space through which water might flow,
     * so that a system that percolates lets water fill open sites, flowing from top to bottom.)
     *<p>
     * The problem.
     * In a famous scientific problem, researchers are interested in the following question:
     * if sites are independently set to be open with probability p (and therefore blocked with probability 1 − p),
     * what is the probability that the system percolates? When p equals 0,
     * the system does not percolate; when p equals 1, the system percolates.
     * The plots below show the site vacancy probability p versus the percolation probability for 20-by-20 random grid (left) and 100-by-100 random grid (right).
     * <p>
     * When n is sufficiently large, there is a threshold value p* such that when p < p* a random n-by-n grid almost never percolates,
     * and when p > p*, a random n-by-n grid almost always percolates.
     * No mathematical solution for determining the percolation threshold p* has yet been derived.
     * The task is to write a computer program to estimate p*.
     */

    private boolean[][] grid;           // the n-by-n grid of sites
    private WeightedQuickUnionUF uf;    // the union find data structure
    private int virtualTop;             // a virtual top site
    private int virtualBottom;          // a virtual bottom site
    private int numberOfOpenSites;      // the number of the open sites
    private final int N;                // the size of the grid

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n cannot be smaller than 1");
        N = n;
        grid = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2);
        numberOfOpenSites = 0;
    }

//    private int to1D(int row, int col)


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row-1][col-1];
    }

//    // is the site (row, col) full?
//    public boolean isFull(int row, int col)
//
    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }
//
//    // does the system percolate?
//    public boolean percolates()

    // validate that row is col valid index
    private void validate(int row, int col) {
        if (row < 0 || row > N || col < 0  || col > N) {
            throw new IllegalArgumentException("The index is outside its prescribed range");
        }
    }
    // test client (optional)
    public static void main(String[] args) {
    }

}
