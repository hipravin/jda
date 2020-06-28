package hipravin.jda.graph.model;

public class ClassUsageLinkMetadata extends Metadata {
    public ClassUsageLinkMetadata(MetaValue metaValue, JavaClassType javaClassType) {
        super(metaValue, javaClassType);
    }

    @Override
    public String getUniqueId() {
        return null;
    }
}
