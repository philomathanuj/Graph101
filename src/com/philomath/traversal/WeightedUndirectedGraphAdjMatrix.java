package com.philomath.traversal;

/**
 * 1) Create a set mstSet that keeps track of vertices already included in MST.
 * 2) Assign a key value to all vertices in the input graph. Initialize all key values as INFINITE. Assign key value as 0 for the first vertex so that it is picked first.
 * 3) While mstSet doesn’t include all vertices
 * ….a) Pick a vertex u which is not there in mstSet and has minimum key value.
 * ….b) Include u to mstSet.
 * ….c) Update key value of all adjacent vertices of u. To update the key values, iterate through all adjacent vertices. For every adjacent vertex v, if weight of edge u-v is less than the previous key value of v, update the key value as weight of u-v
 * The idea of using key values is to pick the minimum weight edge from cut. The key values are used only for vertices which are not yet included in MST, the key value for these vertices indicate the minimum weight edges connecting them to the set of vertices included in MST.
 *https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/
 */
public class WeightedUndirectedGraphAdjMatrix {
    int[][] adjMatrix;
    int V;
    public WeightedUndirectedGraphAdjMatrix(int[][] g){
        this.V = g.length;
        adjMatrix = g;
    }
    public void addEdge(int src, int dest, int wt){
        adjMatrix[src][dest] = wt;
        adjMatrix[dest][src] = wt;
    }
    public void primMST(){
        boolean[] mstSet = new boolean[V];
        int[] key = new int[V];
        int[] parent = new int[V];
        for(int i = 0; i < V; i++){
            mstSet[i] = false;
            key[i] = Integer.MAX_VALUE;
        }
        key[0] = 0;
        parent[0] = -1; // no parent of first node 0 that we are starting with
        for(int i = 0; i < V-1; i++){
            int u = minKey(mstSet,key);
            mstSet[u] = true;
            // update the keys of adjacent vertices of u
            for(int v  = 0 ; v < V; v++){
                if(adjMatrix[u][v] != 0 && mstSet[v] == false && adjMatrix[u][v] < key[v]){
                    key[v] = adjMatrix[u][v];
                    parent[v] = u;
                }
            }
        }
        printMST(parent);
    }

    private void printMST(int[] parent) {
        for(int i = 1; i < parent.length; i++){
            System.out.println(parent[i]+"---"+i+" --> "+adjMatrix[parent[i]][i]);
        }
    }

    private int minKey(boolean[] mstSet, int[] key) {
        // find min value index
        int min = Integer.MAX_VALUE;
        int minIndex = 0;
        for(int i = 0; i < V; i++){
            if(mstSet[i] == false && key[i] < min){
                min = key[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        int graph[][] = new int[][] { { 0, 2, 0, 6, 0 },
                { 2, 0, 3, 8, 5 },
                { 0, 3, 0, 0, 7 },
                { 6, 8, 0, 0, 9 },
                { 0, 5, 7, 9, 0 } };
        WeightedUndirectedGraphAdjMatrix w = new WeightedUndirectedGraphAdjMatrix(graph);
        w.primMST();
    }
}
