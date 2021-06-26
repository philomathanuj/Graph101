package com.philomath.traversal;

import com.philomath.unionfind.UnionFind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class WeightedUndirectedGraph {
    // number of vertices
    private int V;

    private List<Edge> edges;

    public WeightedUndirectedGraph(int V){
        this.V = V;
        this.edges = new LinkedList<>();
    }

    public void addEdge(int src, int dest, int weight){
        edges.add(new Edge(src,dest,weight));
    }

    /**
     * Kruskal's algorithm
     * 1. Sort the edges in non-decreasing order of their weights
     * 2. Pick the edge with minimum weight and check if it forms a cycle with minimum spanning tree
     * built so far. If it doesn't then include it else discard it.
     * 3. Repeat step 2 until there are V-1 edges in the graph.
     * @return
     */
    public List<Edge> mst(){
        List<Edge> minimumSpanningTree = new ArrayList<>();
        Collections.sort(edges);
        UnionFind unionFind = new UnionFind(V);
        int edgeCount = 0;
        for(Edge edge: edges){
            if(unionFind.count() == V || unionFind.connected(edge.src,edge.dest)){
                minimumSpanningTree.add(edge);
                edgeCount++;
                if(edgeCount == edges.size()-1){// mst will have V-1 edges
                    break;
                }
            }
        }

        return minimumSpanningTree;
    }

    class Edge implements Comparable<Edge>{
        int src;
        int dest;
        int weight;
        public Edge(int src, int dest, int weight){
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
        public int compareTo(Edge e){
            if(weight < e.weight){
                return -1;
            }
            else if(weight > e.weight){
                return 1;
            }
            else{
                return 0;
            }
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "src=" + src +
                    ", dest=" + dest +
                    ", weight=" + weight +
                    '}';
        }
    }

    public static void main(String[] args) {
        WeightedUndirectedGraph g = new WeightedUndirectedGraph(4);
        g.addEdge(0,1,10);
        g.addEdge(0,2,6);
        g.addEdge(0,3,5);
        g.addEdge(1,3,15);
        g.addEdge(2,3,4);
        System.out.println(g.mst());
    }
}

