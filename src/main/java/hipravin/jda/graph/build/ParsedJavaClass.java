package hipravin.jda.graph.build;

public class ParsedJavaClass {
    private final String className;
    private final String packageName;

    public ParsedJavaClass(String className, String packageName) {
        this.className = className;
        this.packageName = packageName;
    }
}
