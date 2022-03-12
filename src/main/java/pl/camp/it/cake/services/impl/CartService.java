package pl.camp.it.cake.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.cake.database.IAddDAO;
import pl.camp.it.cake.database.ICakeDAO;
import pl.camp.it.cake.model.Add;
import pl.camp.it.cake.model.OrderPosition;
import pl.camp.it.cake.model.Cake;
import pl.camp.it.cake.model.OrderPositionAdd;
import pl.camp.it.cake.services.ICartService;
import pl.camp.it.cake.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    ICakeDAO cakeDatabase;
    @Autowired
    IAddDAO addDatabase;

    @Resource
    SessionObject sessionObject;

    @Override
    public void addToCart(String title) {
        Optional<Cake> cakeBox = cakeDatabase.getCakeByTitle(title);
        if (!cakeBox.isPresent()) {
            return;
        }
        /*
        for (OrderPosition orderPosition : sessionObject.getCart().getPosition()) {
            if (orderPosition.getCake().getTitle().equals(title)) {
                orderPosition.increaseQuantity();
                return;
            }
        }

         */
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setCake(cakeBox.get());
        orderPosition.setPositionQuantity(1);
        this.sessionObject.getCart().getPosition().add(orderPosition);
    }
    //dodane
    @Override
    public void addToCart1(String title) {
        Optional<Add> addBox = addDatabase.getAddByTitle(title);
        if (!addBox.isPresent()) {
            return;
        }
/*
        for (OrderPositionAdd orderPositionAdd : sessionObject.getCartAdd().getPositionAdd()) {
            if (orderPositionAdd.getAdd().getAddTitle().equals(title)) {
                orderPositionAdd.increaseQuantityAdd();
                return;
            }
        }



 */
        OrderPositionAdd orderPositionAdd = new OrderPositionAdd();
        orderPositionAdd.setAdd(addBox.get());
        orderPositionAdd.setPositionQuantityAdd(1);
        this.sessionObject.getCartAdd().getPositionAdd().add(orderPositionAdd);
    }
}

