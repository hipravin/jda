package hipravin.jda.api.model;

public class LinkDto {
    private PositionDto positionTo;

    private long value;
    private String text;
    private String nodeTo;


    public LinkDto() {
    }

    public LinkDto(PositionDto positionTo, long value, String text, String nodeTo) {
        this.positionTo = positionTo;
        this.value = value;
        this.text = text;
        this.nodeTo = nodeTo;
    }

    public PositionDto getPositionTo() {
        return positionTo;
    }

    public void setPositionTo(PositionDto positionTo) {
        this.positionTo = positionTo;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNodeTo() {
        return nodeTo;
    }

    public void setNodeTo(String nodeTo) {
        this.nodeTo = nodeTo;
    }
}
