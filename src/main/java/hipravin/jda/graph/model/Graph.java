package hipravin.jda.graph.model;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final List<Node> nodes = new ArrayList<>();

    public void addNode(Node node) {
        nodes.add(node);
    }
}
