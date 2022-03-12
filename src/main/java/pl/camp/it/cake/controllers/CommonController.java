package pl.camp.it.cake.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.cake.exceptions.ValidationException;
import pl.camp.it.cake.model.*;
import pl.camp.it.cake.services.ICakeService;
import pl.camp.it.cake.services.IAddService;
import pl.camp.it.cake.session.SessionObject;
import pl.camp.it.cake.validators.AddressValidator;
import pl.camp.it.cake.validators.Validator;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Controller
public class CommonController {

    @Autowired
    ICakeService cakeService;

    @Autowired
    IAddService addService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/",method =RequestMethod.GET)
    public String main(){
        return "redirect:/main";
    }

    @RequestMapping(value="/main",method = RequestMethod.GET)
    public String main(Model model){
        model.addAttribute("cakes",this.cakeService.getAllCakes());
        model.addAttribute("logged",this.sessionObject.isLogged());
        return"main";
    }
//dodane
    @RequestMapping(value="/add",method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("addes",this.addService.getAllAddes());
        model.addAttribute("logged",this.sessionObject.isLogged());
        return"add";
    }
///////////
    @RequestMapping(value="/addpost",method = RequestMethod.POST)
    public String getAdd(@ModelAttribute Add add){
        add.getAddTitle();
        this.addService.getAdd(add);
        return"add";
    }

///////////////

    @RequestMapping(value = "/contact",method =RequestMethod.GET)
    public String contact(Model model)
    { model.addAttribute("logged",this.sessionObject.isLogged());
        return"contact";
    }
}
