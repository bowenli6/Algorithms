package DataStructures.fundamentals.unionfind;

abstract public class UF {

    abstract void union(int p, int q);

    abstract int find(int p);

    abstract boolean connected(int p, int q);

    abstract int count();
}
