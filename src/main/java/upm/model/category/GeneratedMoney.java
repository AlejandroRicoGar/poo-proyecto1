package upm.model.category;

public class GeneratedMoney implements Categoria {

    private double value;

    private String name;

    public GeneratedMoney(String nombre, Double value) {
        this.name = nombre;
        this.value = value;
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
