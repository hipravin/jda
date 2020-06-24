package hipravin.jda.graph.model;

public abstract class Metadata {
    private final MetaValue metaValue;

    public Metadata(MetaValue metaValue) {
        this.metaValue = metaValue;
    }

    public MetaValue getMetaValue() {
        return metaValue;
    }
}
