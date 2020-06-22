package hipravin.jda.graph.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClassNameAndPackage {
    private final String className;
    private final String classPackage;

    public ClassNameAndPackage(String className, String classPackage) {
        this.className = className;
        this.classPackage = classPackage;
    }

    public static ClassNameAndPackage fromSlashed(String slashed) {
        if (!slashed.contains("/")) {
            throw new IllegalArgumentException(slashed + " is not slashed class representation");
        }

        String[] vals = slashed.split("/");
        String classPackage = Arrays.stream(vals)
                .limit(vals.length - 1)
                .collect(Collectors.joining("."));
        String className = vals[vals.length - 1];

        return new ClassNameAndPackage(className, classPackage);

    }

    public String getClassName() {
        return className;
    }

    public String getClassPackage() {
        return classPackage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassNameAndPackage that = (ClassNameAndPackage) o;
        return Objects.equals(className, that.className) &&
                Objects.equals(classPackage, that.classPackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, classPackage);
    }

    @Override
    public String toString() {
        return classPackage + "." + className;
    }
}
