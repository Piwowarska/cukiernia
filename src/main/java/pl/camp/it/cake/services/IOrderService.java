package pl.camp.it.cake.services;

import jdk.jshell.spi.ExecutionControl;
import pl.camp.it.cake.model.Order;
import pl.camp.it.cake.model.Address;

import java.util.List;

public interface IOrderService {
    void confirmOrder(Address address);
    List<Order> getOrdersForCurrentUser();
}
