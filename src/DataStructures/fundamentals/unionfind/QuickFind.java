package DataStructures.fundamentals.unionfind;

public class QuickFind extends UF {

    private final int[] id;       // access to component id (site indexed)
    private int count;            // number of components

    public QuickFind(int n) {
        // initialize component id array.
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) id[i] = i;
    }

    void union(int p, int q) {
        // put p and q into the same component.
        int pid = find(p);
        int qid = find(q);
        // nothing to do if p and q are already in the same component.
        if (pid == qid) return;
        // change values from id[p] to id[q]
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) id[i] = qid;
        }
        count--;
    }

    boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    int count() {
        return count;
    }

    int find(int p) {
        return id[p];
    }

}

