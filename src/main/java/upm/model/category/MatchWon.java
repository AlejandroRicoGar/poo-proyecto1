package upm.model.category;

public class MatchWon implements Categoria {
    private double value;

    private String name;

    public MatchWon(Double value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }
}
