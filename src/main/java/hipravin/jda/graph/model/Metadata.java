package hipravin.jda.graph.model;

public abstract class Metadata {
    private final MetaValue metaValue;
    private final JavaClassType javaClassType;

    public Metadata(MetaValue metaValue, JavaClassType javaClassType) {
        this.metaValue = metaValue;
        this.javaClassType = javaClassType;
    }

    public MetaValue getMetaValue() {
        return metaValue;
    }

    public abstract String getUniqueId();

    public JavaClassType getJavaClassType() {
        return javaClassType;
    }
}
