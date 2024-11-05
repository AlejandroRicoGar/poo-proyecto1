package upm.model.category;

public class GeneratedMoney implements Category{

    private double value;

    private String name;

    public GeneratedMoney(String nombre, Double value) {
        this.name = name;
        this.value = value;
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
