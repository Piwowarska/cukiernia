package pl.camp.it.cake.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.cake.database.IAddDAO;
import pl.camp.it.cake.database.IOrderDAO;
import pl.camp.it.cake.database.ICakeDAO;
import pl.camp.it.cake.model.*;
import pl.camp.it.cake.model.Address;
import pl.camp.it.cake.model.Cart;
import pl.camp.it.cake.model.Cake;
import pl.camp.it.cake.services.IOrderService;
import pl.camp.it.cake.session.SessionObject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    ICakeDAO cakeDatabase;

    @Autowired
    IAddDAO addDatabase;

    @Autowired
    IOrderDAO orderDatabase;

    @Override
    public void confirmOrder(Address address){
        Cart cart=this.sessionObject.getCart();
        List<OrderPosition> orderPositions=cart.getPosition();

        for( OrderPosition orderPosition:orderPositions ) {
            Optional<Cake> cakeTable = this.cakeDatabase.getCakeByTitle(orderPosition.getCake().getTitle());
            if (!cakeTable.isPresent()) {
                return;
            }
            if (orderPosition.getPossitionQuantity() > cakeTable.get().getQuantity()) {
                return;
            }
            //cakeTable cakeBox
        }
            Order order=new Order();
            order.setOrderPositions(new HashSet<>(orderPositions));
            order.setAddress(address);
            order.setUser(this.sessionObject.getUser());
            order.setStatus(Order.Status.NEW);
             this.orderDatabase.addOrder(order);
        for(OrderPosition orderPosition:orderPositions){
           Optional<Cake> cakeBox = this.cakeDatabase.getCakeByTitle(orderPosition.getCake().getTitle());
           Cake cake=cakeBox.get();
           cake.setQuantity(cake.getQuantity()-orderPosition.getPossitionQuantity());
           this.cakeDatabase.updateCake(cake);
               }
        cart.setPosition(new ArrayList<>());
        }

        @Override
        public List<Order> getOrdersForCurrentUser(){
        return this.orderDatabase.getOrderByUserLogin(this.sessionObject.getUser().getLogin());

        }
    }

