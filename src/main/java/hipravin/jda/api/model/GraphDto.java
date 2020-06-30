package hipravin.jda.api.model;

import java.util.List;

public class GraphDto {
    private List<NodeDto> nodes;
    private long complexityMin;
    private long complexityMax;
    private long outboundMin;
    private long outboundMax;

    public GraphDto(List<NodeDto> nodes) {
        this.nodes = nodes;
    }

    public GraphDto() {
    }

    public List<NodeDto> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeDto> nodes) {
        this.nodes = nodes;
    }

    public long getComplexityMin() {
        return complexityMin;
    }

    public void setComplexityMin(long complexityMin) {
        this.complexityMin = complexityMin;
    }

    public long getComplexityMax() {
        return complexityMax;
    }

    public void setComplexityMax(long complexityMax) {
        this.complexityMax = complexityMax;
    }

    public long getOutboundMin() {
        return outboundMin;
    }

    public void setOutboundMin(long outboundMin) {
        this.outboundMin = outboundMin;
    }

    public long getOutboundMax() {
        return outboundMax;
    }

    public void setOutboundMax(long outboundMax) {
        this.outboundMax = outboundMax;
    }
}
