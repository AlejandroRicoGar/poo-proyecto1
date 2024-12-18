package upm.model;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "type")
    private Categories type;
    @Column(name = "value")
    private double value;
    @ManyToOne
    private Player p;

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
