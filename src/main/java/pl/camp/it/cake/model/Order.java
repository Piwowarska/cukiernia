package pl.camp.it.cake.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name="torder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<OrderPosition> orderPositions;
    @OneToOne(fetch = FetchType.EAGER,cascade =CascadeType.ALL)
    private Address address;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Order(int id, Set<OrderPosition> orderPositions, Address address, User user, Status status) {
        this.id = id;
        this.orderPositions = orderPositions;
        this.address = address;
        this.user = user;
        this.status = status;
    }

    public Order() {
    }
    public double calculateSum(){
        double result=0.0;
        for(OrderPosition orderPosition:this.orderPositions){
            result +=orderPosition.getPossitionQuantity()*orderPosition.getCake().getPrice();
        }
        return Math.round(result*100)/100.0;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(Set<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public enum Status{
        NEW,
        REALIZATION,
        DELIVERY,
        DONE
    }
}

