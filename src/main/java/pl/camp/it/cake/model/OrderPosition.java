package pl.camp.it.cake.model;

import javax.persistence.*;
import java.util.List;

@Entity(name="torderposition")
public class OrderPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    private Cake cake;
   // private List<Add> addons;
    private int positionQuantity;


    public Cake getCake() {
        return cake;
    }
        public void setCake(Cake cake) {
        this.cake = cake;
    }
    public int getPossitionQuantity() {
        return positionQuantity;
    }

    public void setPositionQuantity(int positionQuantity) {
        this.positionQuantity = positionQuantity;
    }
    public void increaseQuantity(){
        this.positionQuantity++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
/*
    public List<Add> getAddons() {
        return addons;
    }

    public void setAddons(List<Add> addons) {
        this.addons = addons;
    }

 */


    public int getPositionQuantity() {
        return positionQuantity;
    }
}
