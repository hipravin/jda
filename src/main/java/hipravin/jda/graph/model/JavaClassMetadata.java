package hipravin.jda.graph.model;

public class JavaClassMetadata extends Metadata {
    private final ClassNameAndPackage nameAndPackage;
    private JavaClassType javaClassType;

    public JavaClassMetadata(ClassNameAndPackage nameAndPackage, MetaValue metaValue) {
        super(metaValue);
        this.nameAndPackage = nameAndPackage;
    }

    public ClassNameAndPackage getNameAndPackage() {
        return nameAndPackage;
    }

    @Override
    public String toString() {
        return nameAndPackage.toString();
    }

    @Override
    public String getUniqueId() {
        return nameAndPackage.getClassName();
    }
}
