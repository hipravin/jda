package hipravin.jda.graph.model;

public class Metadata {
    private final ClassNameAndPackage nameAndPackage;
    private final MetaValue metaValue;

    public Metadata(ClassNameAndPackage nameAndPackage, MetaValue metaValue) {
        this.nameAndPackage = nameAndPackage;
        this.metaValue = metaValue;
    }

    public ClassNameAndPackage getNameAndPackage() {
        return nameAndPackage;
    }

    public MetaValue getMetaValue() {
        return metaValue;
    }
}
