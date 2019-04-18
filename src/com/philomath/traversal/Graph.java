package com.philomath.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Graph {
	private int V;
	private List<List<Integer>> adjList;
	
	public Graph(int V) {
		this.V = V;
		this.adjList = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			this.adjList.add(new LinkedList<>());
		}
	}
	
	public void addEdge(int source, int destination) {
		this.adjList.get(source).add(destination);
	}
	
	public void bfs(int source) {
		boolean[] visited = new boolean[V];
		LinkedList<Integer> queue = new LinkedList<>();
		visited[source] = true;
		queue.add(source);
		while(queue.size() != 0) {
			source = queue.poll();
			System.out.println(source);
			for (Integer n : adjList.get(source)) {
				if(!visited[n]) {
					visited[n]=true;
					queue.add(n);
				}
			}
		}
	}
	
	public void dfs(int source) {
		boolean[] visited = new boolean[V];
		dfsr(source,visited);
	}
	
	private void dfsr(int source, boolean[] visited) {
		visited[source] = true;
		System.out.println(source);
		for (Integer node : adjList.get(source)) {
			if(!visited[node]) {
				dfsr(node,visited);
			}
		}
	}
	/**
	 * https://www.youtube.com/watch?v=TvTNFOn4KcE
	 */
	public void topologicalSort() {
		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[V];
		for (int i = 0; i < V; i++) {
			if(!visited[i]) {
				topologicalSortRec(	i,visited,stack);
			}
		}
		while(!stack.isEmpty()) {
			System.out.print(stack.pop()+" ");
		}
	}

	private void topologicalSortRec(int i, boolean[] visited, Stack<Integer> stack) {
		visited[i] = true;
		for (Integer node : adjList.get(i)) {
			if(!visited[node]) {
				topologicalSortRec(node,visited,stack);
			}
		}
		stack.push(i);
	}

	public static void main(String[] args) {
		Graph graph = new Graph(4);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(2, 0);
		graph.addEdge(2,3);
		graph.addEdge(3, 3);
		graph.bfs(2);
		graph.dfs(2);
		graph.topologicalSort();
	}
	
}
