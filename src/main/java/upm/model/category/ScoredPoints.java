package upm.model.category;

public class ScoredPoints implements Categoria {
    private double value;
    private String name;

    public ScoredPoints(double value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }
}
