package hipravin.jda.graph.model;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {
    private final List<Link> links = new ArrayList<>();
    private final Metadata metadata;

    public GraphNode(Metadata metadata) {
        this.metadata = metadata;
    }

    public void addLink(Link link) {
        links.add(link);
    }

    public List<Link> getLinks() {
        return links;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return metadata.toString();
    }
}
