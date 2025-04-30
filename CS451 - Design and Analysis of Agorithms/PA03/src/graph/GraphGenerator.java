/*
    File: GraphGenerator.java

    Description:
    This file implements a class to generate random graphs with nodes, edges, and attributes.
    The generated graphs are used for testing the algorithm to detect strongly connected components (SCCs).

    Modifications:
    - Created a class to generate random graphs.
    - Added methods to generate random nodes, edges, and attributes.
*/

package src.graph;

import java.util.*;

public class GraphGenerator {

    private static final int NUM_NODES = 10;
    private static final int NUM_EDGES = 20;

    /**
     * Generates a random graph with nodes, edges, and attributes.
     */
    public Map<Node, List<Node>> generateRandomGraph() {
        Map<Node, List<Node>> graph = new HashMap<>();
        Random random = new Random();

        // Generate random nodes
        List<Node> nodes = generateRandomNodes();

        // Generate random edges
        for (int i = 0; i < NUM_EDGES; i++) {
            Node source = nodes.get(random.nextInt(NUM_NODES));
            Node target = nodes.get(random.nextInt(NUM_NODES));
            graph.computeIfAbsent(source, k -> new ArrayList<>()).add(target);
        }

        return graph;
    }

    /**
     * Generates random nodes with attributes.
     */
    private List<Node> generateRandomNodes() {
        List<Node> nodes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < NUM_NODES; i++) {
            String name = "Node" + i;
            int attributes = random.nextInt(100);
            Node node = new Node(name, attributes);
            nodes.add(node);
        }
        return nodes;
    }
}
