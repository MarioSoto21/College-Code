/*
    File: Runner.java

    Description:
    This file modifies the existing Java code to utilize Kosaraju's algorithm for detecting strongly connected components (SCCs) in a directed graph.
    Additionally, it extends the program to randomly generate graphs with nodes, edges, and attributes.
    The output includes information about the original graph (nodes, edges, and attributes) and the detected SCCs.

    Modifications:
    - Modified the main method to generate a random graph, detect SCCs, and output the results.
    - Added methods to output graph information and SCCs.
*/

package src.main.app;

import src.graph.*;

import java.util.*;

public class Runner {
    public static void main(String[] args) {
        // Generate a random graph
        GraphGenerator graphGenerator = new GraphGenerator();
        Map<Node, List<Node>> graph = graphGenerator.generateRandomGraph();

        // Output original graph information
        System.out.println("Original Graph Information:");
        outputGraphInformation(graph);

        // Detect strongly connected components (SCCs)
        KosarajuSCC kosarajuSCC = new KosarajuSCC();
        List<List<Node>> sccList = kosarajuSCC.findSCC(graph);

        // Output SCCs
        System.out.println("\nStrongly Connected Components (SCCs):");
        outputSCCs(sccList);
    }

    /**
     * Outputs information about the original graph (nodes, edges, and attributes).
     *
     * @param graph The original graph represented as a map of nodes to their adjacency lists.
     */
    private static void outputGraphInformation(Map<Node, List<Node>> graph) {
        for (Node node : graph.keySet()) {
            System.out.print("Node(" + node.getName() + ", Attributes: " + node.getAttributes() + ") -> ");
            List<Node> neighbors = graph.get(node);
            for (Node neighbor : neighbors) {
                System.out.print(neighbor.getName() + " ");
            }
            System.out.println();
        }
    }

    /**
     * Outputs the strongly connected components (SCCs) detected in the graph.
     */
    private static void outputSCCs(List<List<Node>> sccList) {
        for (List<Node> scc : sccList) {
            System.out.print("SCC: ");
            for (Node node : scc) {
                System.out.print(node.getName() + " ");
            }
            System.out.println();
        }
    }
}
