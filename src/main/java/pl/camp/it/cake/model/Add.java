package pl.camp.it.cake.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="tadd")
public class Add {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String addTitle;
        private int addQuantity;
        private int addPrice;

        public Add(int id, String addTitle, int addQuantity, int addPrice) {
            this.id=id;
            this.addTitle = addTitle;
            this.addQuantity = addQuantity;
            this.addPrice = addPrice;
        }

    public Add() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddTitle() {
        return addTitle;
    }

    public void setAddTitle(String addTitle) {
        this.addTitle = addTitle;
    }

    public int getAddQuantity() {
        return addQuantity;
    }

    public void setAddQuantity(int addQuantity) {
        this.addQuantity = addQuantity;
    }

    public int getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(int addPrice) {
        this.addPrice = addPrice;
    }
}
