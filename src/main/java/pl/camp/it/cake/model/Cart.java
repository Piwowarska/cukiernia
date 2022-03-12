package pl.camp.it.cake.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
private  List<OrderPosition> position =new ArrayList<>();
    private  List<OrderPositionAdd> positionAdd =new ArrayList<>();

    public List<OrderPosition> getPosition() {
        return position;
    }

    public List<OrderPositionAdd> getPositionAdd() {
        return positionAdd;
    }

    public void setPosition(List<OrderPosition> position) {
        this.position = position;
    }

    public void setPositionAdd(List<OrderPositionAdd> positionAdd) {
        this.positionAdd = positionAdd;
    }

    public double clculateSum(){
        double result=0.0;
        for(OrderPosition orderPosition:this.position){
            result+=orderPosition.getPossitionQuantity()*orderPosition.getCake().getPrice();
        }

        return Math.round(result*100)/100.0;
    }
}

