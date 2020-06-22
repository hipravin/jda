package hipravin.jda.graph.model;

public class Link {
    private final Node to;
    private final MetaValue metaValue;

    public Link(Node to, MetaValue metaValue) {
        this.to = to;
        this.metaValue = metaValue;
    }

    public Node getTo() {
        return to;
    }

    public MetaValue getMetaValue() {
        return metaValue;
    }
}
