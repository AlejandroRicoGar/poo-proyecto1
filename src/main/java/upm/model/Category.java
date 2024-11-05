package upm.model;

public class Category {
    private String nombre;
    private double value;

    public Category(String nombre, double value) {
        this.nombre = nombre;
        this.value = value;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
