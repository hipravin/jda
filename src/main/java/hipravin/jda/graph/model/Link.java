package hipravin.jda.graph.model;

public class Link {
    private final GraphNode to;
    private final Metadata metadata;

    public Link(GraphNode to, Metadata metadata) {
        this.to = to;
        this.metadata = metadata;
    }

    public GraphNode getTo() {
        return to;
    }

    public Metadata getMetadata() {
        return metadata;
    }
}
