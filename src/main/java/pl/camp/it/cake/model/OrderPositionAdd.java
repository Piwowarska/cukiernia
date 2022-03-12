package pl.camp.it.cake.model;

import javax.persistence.*;


@Entity(name="torderpositionadd")
public class OrderPositionAdd {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;


        @ManyToOne
        private Add add;
        private int positionQuantityAdd;


        public Add getAdd() {
            return add;
        }
        public void setAdd(Add add) {
            this.add = add;
        }
        public int getPositionQuantityAdd() {
            return positionQuantityAdd;
        }

        public void setPositionQuantityAdd(int positionQuantityAdd) {
            this.positionQuantityAdd = positionQuantityAdd;
        }
        public void increaseQuantityAdd(){
            this.positionQuantityAdd++;
        }


    }


