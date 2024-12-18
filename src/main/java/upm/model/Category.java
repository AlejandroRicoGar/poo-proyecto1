package upm.model;

public class Category {
    private Categories type;
    private double value;

    @Override
    public String toString() {
        return "Category{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }

    public Category(Categories nombre, double value) {
        this.type = nombre;
        this.value = value;
    }

    public Categories getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
