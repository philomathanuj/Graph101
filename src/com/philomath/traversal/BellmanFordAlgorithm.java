package com.philomath.traversal;

/**
 * Dijkstra's algorithm won't work if there is a negative edge weight cycle. It will give output
 * which will be wrong. Bellman Ford algorithm will also not work if there is a negative edge weight
 * cycle but it can detect a negative edge weight cycle so we know output is wrong.
 * If an undirected graph has negative edge, it's equivalent to having a negative edge weight
 * cycle because 0 --(-2)-- 2 is like 0 -(-2)- 2 and 2 -(-2)- 0 .
 * Here in this program we are using directed graph
 * References -
 * https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/
 * https://www.youtube.com/watch?v=FrLWd1tJ_Wc&t=926s
 * https://www.youtube.com/watch?v=64KK9K4RpKE
 */
public class BellmanFordAlgorithm {
    int V;
    int E;
    Edge[] edges;
    class Edge{
        int src;
        int dest;
        int wt;
    }
    public BellmanFordAlgorithm(int V, int E){
        this.V = V;
        this.E = E;
        this.edges = new Edge[E];
        for(int i = 0; i < E; i++){
            edges[i] = new Edge();
        }
    }
    public void bellmanFord(int src, int target){
        int[] dist = new int[V];
        int[] parent = new int[V];
        for(int i = 0; i < V; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[src] = 0;
        parent[src] = -1;
        for(int i = 0; i < V-1; i++){
            for(int j = 0; j < E; j++){
                int u = edges[j].src;
                int v = edges[j].dest;
                int wt = edges[j].wt;
                if(dist[u] != Integer.MAX_VALUE && dist[u]+wt < dist[v]){
                    dist[v] = dist[u]+wt;
                    parent[v] = u;
                }
            }
        }
        // check for negative edge weight cycle
        for(int i = 0; i < V-1; i++){
            for(int j = 0; j < E; j++){
                int u = edges[j].src;
                int v = edges[j].dest;
                int wt = edges[j].wt;
                if(dist[u] != Integer.MAX_VALUE && dist[u]+wt < dist[v]){
                    System.out.println("Negative edge wt cycle is present");
                    break;
                }
            }
        }
        printShortestPathCost(dist,src);
        printShortestPath(parent,src,target);
    }

    private void printShortestPath(int[] parent, int src, int target) {
        while(src != target){
            System.out.print(target+" <-- ");
            target = parent[target];
        }
        System.out.print(src);
    }

    private void printShortestPathCost(int[] dist, int src) {
        for(int i = 0; i < dist.length; i++){
            System.out.println("Shortest Dist From Src : "+src+" dest: "+ i+" -> "+dist[i]);
        }
    }

    public static void main(String[] args) {
        BellmanFordAlgorithm b = new BellmanFordAlgorithm(5,8);
        // add edge 0-1 (or A-B in above figure)
        b.edges[0].src = 0;
        b.edges[0].dest = 1;
        b.edges[0].wt = -1;

        // add edge 0-2 (or A-C in above figure)
        b.edges[1].src = 0;
        b.edges[1].dest = 2;
        b.edges[1].wt = 4;

        // add edge 1-2 (or B-C in above figure)
        b.edges[2].src = 1;
        b.edges[2].dest = 2;
        b.edges[2].wt = 3;

        // add edge 1-3 (or B-D in above figure)
        b.edges[3].src = 1;
        b.edges[3].dest = 3;
        b.edges[3].wt = 2;

        // add edge 1-4 (or A-E in above figure)
        b.edges[4].src = 1;
        b.edges[4].dest = 4;
        b.edges[4].wt = 2;

        // add edge 3-2 (or D-C in above figure)
        b.edges[5].src = 3;
        b.edges[5].dest = 2;
        b.edges[5].wt = 5;

        // add edge 3-1 (or D-B in above figure)
        b.edges[6].src = 3;
        b.edges[6].dest = 1;
        b.edges[6].wt = 1;

        // add edge 4-3 (or E-D in above figure)
        b.edges[7].src = 4;
        b.edges[7].dest = 3;
        b.edges[7].wt = -3;

        b.bellmanFord(0,3);

        BellmanFordAlgorithm b2 = new BellmanFordAlgorithm(4,5);
        // add edge 0-1 (or A-B in above figure)
        b2.edges[0].src = 0;
        b2.edges[0].dest = 1;
        b2.edges[0].wt = 5;

        b2.edges[1].src = 1;
        b2.edges[1].dest = 2;
        b2.edges[1].wt = -2;

        b2.edges[2].src = 0;
        b2.edges[2].dest = 2;
        b2.edges[2].wt = -1;

        b2.edges[3].src = 3;
        b2.edges[3].dest = 0;
        b2.edges[3].wt = 2;

        b2.edges[4].src = 2;
        b2.edges[4].dest = 3;
        b2.edges[4].wt = 4;
        b2.bellmanFord(0,3);

    }
}
