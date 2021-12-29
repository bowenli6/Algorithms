package DataStructures.fundamentals.unionfind;

public class WeightedQuickFind extends UF {

    private final int[] id;       // access to component idq (site indexed)
    private int count;            // number of components

    public WeightedQuickFind(int n) {
        // initialize component id array.
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    void union(int p, int q) {

    }

    int find(int p) {
        return 0;
    }

    boolean connected(int p, int q) {
        return false;
    }

    int count() {
        return 0;
    }
}
