package pl.camp.it.cake.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.camp.it.cake.model.Add;
import pl.camp.it.cake.model.Cart;
import pl.camp.it.cake.model.CartAdd;
import pl.camp.it.cake.model.User;


@Component
@SessionScope
public class SessionObject {
    private User user=null;

    private Cart cart=new Cart();
    private CartAdd cartAdd=new CartAdd();

    public boolean isLogged() {
        return this.user!=null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart getCart() {
        return cart;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public CartAdd getCartAdd() {
        return cartAdd;
    }
    public void setCartAdd(CartAdd cartAdd) {
        this.cartAdd = cartAdd;
    }


}

