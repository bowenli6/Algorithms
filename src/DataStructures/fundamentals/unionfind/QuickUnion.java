package DataStructures.fundamentals.unionfind;

public class QuickUnion extends UF {

    private final int[] id;       // access to component id (site indexed)
    private int count;            // number of components

    public QuickUnion(int n) {
        // initialize component id array.
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    void union(int p, int q) {
        // give p and q the same root
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        id[i] = j;
        count--;
    }

    int find(int i) {
        // chase parent pointers until reach root
        while (i != id[i]) i = id[i];
        return i;
    }

    boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    int count() {
        return count;
    }
}
