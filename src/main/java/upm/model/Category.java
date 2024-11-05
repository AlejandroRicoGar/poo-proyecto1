package upm.model;

public class Category {
    private Categorys type;
    private double value;

    public Category(Categorys nombre, double value) {
        this.type = nombre;
        this.value = value;
    }

    public String getType() {
        return type.toString();
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
