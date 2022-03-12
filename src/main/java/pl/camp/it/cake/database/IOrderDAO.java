package pl.camp.it.cake.database;

import pl.camp.it.cake.model.Order;

import java.util.List;

public interface IOrderDAO {
    //List<Order> getOrders();
    void addOrder(Order order);
    List<Order> getOrderByUserLogin(String login);
}
