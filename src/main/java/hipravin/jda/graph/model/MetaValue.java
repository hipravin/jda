package hipravin.jda.graph.model;

public class MetaValue {
    public static final long  DEFAULT_VALUE = 1000;

    private final long longValue;

    public MetaValue(long longValue) {
        this.longValue = longValue;
    }

    public static MetaValue defaultMetaValue() {
        return new MetaValue(DEFAULT_VALUE);
    }

    public long getLongValue() {
        return longValue;
    }
}
