package upm.model.category;

public class ScoredPoints implements Category{
    private double value;
    private String name;

    public ScoredPoints(double value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public Double getValue() {
        return 0.0;
    }

    @Override
    public void setValue(Double value) {

    }
}
