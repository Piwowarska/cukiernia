package pl.camp.it.cake.services;

import pl.camp.it.cake.model.view.RegisterUser;

public interface IAuthenticationService {
    void authenticate(String login, String password);
     void logout();
    boolean register(RegisterUser registerUser);
}
