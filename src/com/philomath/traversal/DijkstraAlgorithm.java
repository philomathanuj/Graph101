package com.philomath.traversal;

public class DijkstraAlgorithm {

    int V;
    int[][] g;
    public DijkstraAlgorithm(int[][] g){
        this.V = g.length;
        this.g = g;
    }
    public void shortestPath(int src, int target){
        boolean sps[] = new boolean[V];
        int[] d = new int[V];
        int[] parent = new int[V];
        for(int i = 0; i < V; i++){
            d[i] = Integer.MAX_VALUE;
        }
        d[src] = 0;
        parent[src] = -1;
        for(int i = 0; i < V-1; i++){
            int u = minDist(sps,d);
            if(target != -1 && u ==target){
                break;
            }
            sps[u] = true;
            for(int v = 0; v < V; v++){
                if(g[u][v] != 0 && sps[v] == false && d[u] != Integer.MAX_VALUE && d[v] > d[u]+g[u][v]){
                    d[v] = d[u]+g[u][v];
                    parent[v] = u;
                }
            }
        }
        printShortestDist(d,src);
        printShortestPath(parent,src,target);
    }

    private void printShortestDist(int[] d,int src) {
        for(int i = 0; i < d.length; i++){
            System.out.println("From src: "+src+" dst: "+i+" distance: "+d[i]);
        }
    }

    private void printShortestPath(int[] parent, int src, int dest) {
        if(dest == -1 ) return;
       while(src != dest){
           System.out.print(dest+" <-- " );
           dest = parent[dest];
        }
        System.out.print(src);

    }

    private int minDist(boolean[] sps, int[] d) {
        int minIndex = 0;
        int min = Integer.MAX_VALUE;
        for(int v = 0; v < V; v++){
            if(sps[v] == false && d[v] <= min){
                min = d[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

        int[][] g2 = new int[][]{
                {0,10,0,20},
                {10,0,5,5},
                {0,5,0,15},
                {20,5,15,0}
        };
        DijkstraAlgorithm d = new DijkstraAlgorithm(g2);
        d.shortestPath(2,0);
    }
}
