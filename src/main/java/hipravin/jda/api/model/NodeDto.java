package hipravin.jda.api.model;

import java.util.List;

public class NodeDto {
    //location (center), expected value from -100 to 100
    private PositionDto position;

    private long value;
    private String header;

    private List<LinkDto> links;

    private boolean isNonProjectClass;

    public NodeDto(PositionDto position, long value, String header, List<LinkDto> links) {
        this.position = position;
        this.value = value;
        this.header = header;
        this.links = links;
    }

    public NodeDto() {
    }

    public PositionDto getPosition() {
        return position;
    }

    public void setPosition(PositionDto position) {
        this.position = position;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<LinkDto> getLinks() {
        return links;
    }

    public void setLinks(List<LinkDto> links) {
        this.links = links;
    }

    public boolean isNonProjectClass() {
        return isNonProjectClass;
    }

    public void setNonProjectClass(boolean nonProjectClass) {
        isNonProjectClass = nonProjectClass;
    }
}
