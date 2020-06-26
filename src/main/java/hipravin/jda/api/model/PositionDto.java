package hipravin.jda.api.model;

public class PositionDto {
    private double x;
    private double y;

    public PositionDto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public PositionDto() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}
