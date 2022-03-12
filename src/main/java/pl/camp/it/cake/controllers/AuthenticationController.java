package pl.camp.it.cake.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.cake.exceptions.ValidationException;
import pl.camp.it.cake.model.Add;
import pl.camp.it.cake.model.view.RegisterUser;
import pl.camp.it.cake.services.IAuthenticationService;
import pl.camp.it.cake.session.SessionObject;
import pl.camp.it.cake.validators.Validator;

import javax.annotation.Resource;

@Controller
public class AuthenticationController {


    @Autowired
    IAuthenticationService authenticationService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password) {
         try {
            Validator.validateLogin(login);
            Validator.validatePassword(password);
        } catch (ValidationException e) {
            System.out.println("Walidacja nieudana");
            return "redirect:/login";
        }
        this.authenticationService.authenticate(login, password);
        if (this.sessionObject.isLogged()) {
            return "redirect:/main";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.authenticationService.logout();
        return "redirect:/main";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String zaloguj(Model model) {
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("registerUser", new RegisterUser());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute RegisterUser registerUser) {
        try {
            Validator.validateLogin(registerUser.getLogin());
            Validator.validatePassword(registerUser.getPassword());
            Validator.validatePasswordsEquality(registerUser.getPassword(), registerUser.getPassword2());
            Validator.validateName(registerUser.getName());
            Validator.validateSurname(registerUser.getSurname());
            Validator.validateMail(registerUser.getMail());
        } catch (ValidationException e) {
            return "redirect:/register";
        }

        if(this.authenticationService.register(registerUser)) {
            return "redirect:/login";
        } else {
            return "redirect:/register";
              }
    }

}

