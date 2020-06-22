package hipravin.jda.graph.model;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final List<Link> links = new ArrayList<>();
    private final Metadata metadata;

    public Node(Metadata metadata) {
        this.metadata = metadata;
    }

    public void addLink(Link link) {
        links.add(link);
    }
}
