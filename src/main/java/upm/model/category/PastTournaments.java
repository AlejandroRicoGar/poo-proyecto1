package upm.model.category;

public class PastTournaments implements Category {
    private String name;
    private Double value;

    public PastTournaments(String name, Double value) {
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
