package upm.model.category;


public class AsistsPoints implements Categoria {
    private double value;
    private String name;

    public AsistsPoints(Double value, String name) {
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