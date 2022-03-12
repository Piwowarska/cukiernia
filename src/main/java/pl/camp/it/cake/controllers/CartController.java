package pl.camp.it.cake.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.cake.database.IAddDAO;
import pl.camp.it.cake.model.Add;
import pl.camp.it.cake.services.ICartService;
import pl.camp.it.cake.session.SessionObject;

import javax.annotation.Resource;

@Controller
@RequestMapping(value="/cart")
public class CartController {

    @Autowired
    ICartService cartService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value="/add/{title}",method = RequestMethod.GET)
    public String addToCatr(@PathVariable String title){
        if(!this.sessionObject.isLogged()){
            return "redirect:/main";
        }
        this.cartService.addToCart(title);
        return "redirect:/main";

    }
    @RequestMapping(value="/add1/{addTitle}",method = RequestMethod.GET)
    public String addToCatr1(@PathVariable String addTitle){
        if(!this.sessionObject.isLogged()){
            return "redirect:/add";
        }
        this.cartService.addToCart1(addTitle);
             return "redirect:/add";

    }

    //cake-title
    @RequestMapping(value="",method = RequestMethod.GET)
    public String cart(Model model){
        if(!this.sessionObject.isLogged()){
            return "redirect:/main";
        }
        model.addAttribute("logged",this.sessionObject.isLogged());
        model.addAttribute("cart",this.sessionObject.getCart());
        model.addAttribute("sum",this.sessionObject.getCart().clculateSum());
        model.addAttribute("cartAdd", this.sessionObject.getCartAdd());
       return "cart";
    }


}
