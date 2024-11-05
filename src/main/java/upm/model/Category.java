package upm.model;

public class Category {
    private Categorys type;
    private double value;

    @Override
    public String toString() {
        return "Category{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }

    public Category(Categorys nombre, double value) {
        this.type = nombre;
        this.value = value;
    }

    public Categorys getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
        System.out.println(this.value);
    }
}
