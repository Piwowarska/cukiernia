package pl.camp.it.cake.model;

import java.util.ArrayList;
import java.util.List;

public class CartAdd {


        private  List<OrderPositionAdd> positionAdd =new ArrayList<>();

        public List<OrderPositionAdd> getPositionAdd() {
            return positionAdd;
        }

        public void setPositionAdd(List<OrderPositionAdd> positionAdd) {
            this.positionAdd = positionAdd;
        }

}



