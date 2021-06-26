package com.philomath.unionfind;

public class UnionFind {
    // parent
    private int[] id;
    // size of the component
    private int[] sz;
    // number of components
    private int count;
    public UnionFind(int V){
        this.count = V;
        id = new int[V];
        sz = new int[V];
        for(int i = 0; i < id.length; i++){
            id[i] = i;
            sz[i] = 1;
        }
    }
    public int count(){
        return this.count;
    }
    // finds the absolute root of a given node
    public int find(int i){
        while(id[i] != i){
            // make every other node in the path point to its grand parent, thereby halving path length
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
    // if there is a path between two nodes p and q
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }
    // merge two nodes if they belong to different sets i.e. they have different absolute parent
    public void union(int p, int q){
        int i = find(p);
        int j = find(q);
        if(i == j) return;
        // make smaller root point to the larger one
        if(sz[i] < sz[j]){
            id[i] = j;
            sz[j] += sz[i];
        }
        else{
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(4);
        uf.union(0,1);
        uf.union(1,2);
        uf.union(2,3);
        System.out.println(uf.connected(0,3));
        System.out.println(uf.count);
    }
}
