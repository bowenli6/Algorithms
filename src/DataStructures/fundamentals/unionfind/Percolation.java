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

    private final int N;                        // the size of the grid
    private int numberOfOpenSites;              // the number of the open sites
    private final boolean[][] sites;            // the n-by-n grid of sites
    private final int virtualTopSite;           // a virtual top site
    private final int virtualBottomSite;        // a virtual bottom site
    private final WeightedQuickUnionUF uf;      // the union find data structure


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n cannot be smaller than 1");
        N = n;
        sites = new boolean[n][n];
        virtualTopSite = n * n;
        virtualBottomSite = n * n + 1;
        uf = new WeightedQuickUnionUF(n * n + 2);
        numberOfOpenSites = 0;
    }

    private int index(int row, int col) {
        return (row - 1) * N + (col - 1);
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!sites[row-1][col-1]) {
            sites[row-1][col-1] = true;
            numberOfOpenSites++;
            if (row == 1) uf.union(virtualTopSite, index(row, col));    //top row
            if (row == N) uf.union(virtualBottomSite, index(row, col)); // bottom row
            connectNeighbors(row, col);
        }
    }

    // union possible neighbors
    private void connectNeighbors(int row, int col) {
        int it = index(row, col);
        if (row > 1 && isOpen(row - 1, col)) {
            // union with its upper site
            uf.union(it, index(row - 1, col));
        }
        if (row < N && isOpen(row + 1, col)) {
            // union with its lower site
            uf.union(it, index(row + 1, col));
        }
        if (col > 1 && isOpen(row, col - 1)) {
            // union with its left site
            uf.union(it, index(row, col - 1));
        }
        if (col < N && isOpen(row, col + 1)) {
            // union with its right site
            uf.union(it, index(row, col + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return sites[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int it = uf.find(index(row, col));
        return isOpen(row, col) && it == uf.find(virtualTopSite);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(virtualTopSite) == uf.find(virtualBottomSite);
    }

    // validate that row is col valid index
    private void validate(int row, int col) {
        if (row < 1 || row > N || col < 1  || col > N) {
            throw new IllegalArgumentException("The index is outside of its prescribed range");
        }
    }
    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(2, 1);
        p.open(1, 1);
        p.open(1, 3);
        p.open(2,2);
        System.out.println(p.isFull(1, 1));
        System.out.println(p.isFull(1, 2));
        System.out.println(p.isFull(1, 3));
        System.out.println(p.isOpen(2, 2));
        System.out.println(p.isFull(2, 2));
        p.open(3, 1);
        System.out.println(p.percolates());
    }

}
