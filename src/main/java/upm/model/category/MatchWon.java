package upm.model.category;

public class MatchWon implements Category {
    private double value;

    private String name;

    public MatchWon(Double value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public Double getValue() {
        return 0.0;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public void setValue(Double value) {

    }
}
