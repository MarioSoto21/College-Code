/*
    File: KosarajuSCC.java

    Description:
    This file extends the existing Java code to implement Kosaraju's algorithm for detecting strongly connected components (SCCs) in a directed graph.
    Kosaraju's algorithm consists of two depth-first searches (DFS) on the graph and its reverse, followed by post-processing to identify SCCs.

    Modifications:
    - Implemented Kosaraju's algorithm for detecting SCCs.
    - Added methods for performing DFS on the graph and its reverse.
    - Added method to reverse the graph.
    - Created a class-level stack for DFS traversal.
*/

package src.graph;

import java.util.*;

public class KosarajuSCC {

    private Stack<Node> stack;

    /**
     * Finds all strongly connected components (SCCs) in the graph using Kosaraju's algorithm.
     */
    public List<List<Node>> findSCC(Map<Node, List<Node>> graph) {
        stack = new Stack<>(); // Initialize stack for DFS
        Set<Node> visited = new HashSet<>(); // Set to keep track of visited nodes

        // First pass of DFS to fill the stack
        for (Node node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfsFirstPass(node, visited);
            }
        }

        // Reverse the graph
        Map<Node, List<Node>> reversedGraph = reverseGraph(graph);

        visited.clear();
        List<List<Node>> sccList = new ArrayList<>();

        // Second pass of DFS to find SCCs
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (!visited.contains(node)) {
                List<Node> scc = new ArrayList<>();
                dfsSecondPass(node, visited, scc, reversedGraph);
                sccList.add(scc);
            }
        }

        return sccList;
    }

    /**
     * Performs the first pass of DFS to fill the stack.
     */
    private void dfsFirstPass(Node node, Set<Node> visited) {
        visited.add(node);
        for (Node neighbor : node.getNeighbors()) {
            if (!visited.contains(neighbor)) {
                dfsFirstPass(neighbor, visited);
            }
        }
        stack.push(node);
    }

    /**
     * Performs the second pass of DFS to find SCCs.
     */
    private void dfsSecondPass(Node node, Set<Node> visited, List<Node> scc, Map<Node, List<Node>> reversedGraph) {
        visited.add(node);
        scc.add(node);
        for (Node neighbor : reversedGraph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfsSecondPass(neighbor, visited, scc, reversedGraph);
            }
        }
    }

    /**
     * Reverses the given directed graph.
     */
    private Map<Node, List<Node>> reverseGraph(Map<Node, List<Node>> graph) {
        Map<Node, List<Node>> reversedGraph = new HashMap<>();
        for (Node node : graph.keySet()) {
            for (Node neighbor : graph.get(node)) {
                reversedGraph.computeIfAbsent(neighbor, k -> new ArrayList<>()).add(node);
            }
        }
        return reversedGraph;
    }
}
