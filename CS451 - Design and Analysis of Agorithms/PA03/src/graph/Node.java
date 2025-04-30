package src.graph;

import java.util.*;

public class Node {
    private String name;
    private int attributes;
    private List<Node> neighbors;

    public Node(String name, int attributes) {
        this.name = name;
        this.attributes = attributes;
        this.neighbors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getAttributes() {
        return attributes;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }
}
