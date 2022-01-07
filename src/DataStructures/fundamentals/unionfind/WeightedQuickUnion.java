package DataStructures.fundamentals.unionfind;

public class WeightedQuickUnion extends UF {

    private final int[] id;       // access to component idq (site indexed)
    private final int[] sz;       // size of component for roots (site indexed)
    private int count;            // number of components

    public WeightedQuickUnion(int n) {
        // initialize component id array
        count = n;
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;

        // make smaller root point to larger one
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }

    int find(int p) {
        // follow links to find a root
        while (p != id[p]) p = id[p];
        return p;
    }

    boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    int count() {
        return count;
    }
}
