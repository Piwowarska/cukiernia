package pl.camp.it.cake.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.cake.database.IUserDAO;
import pl.camp.it.cake.model.User;
import pl.camp.it.cake.model.view.RegisterUser;
import pl.camp.it.cake.services.IAuthenticationService;
import pl.camp.it.cake.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    IUserDAO userDatabase;

    @Resource
    SessionObject sessionObject;

    @Override
    public void authenticate(String login, String password) {
        Optional<User> userBox = this.userDatabase.getUserByLogin(login);
        if (userBox.isPresent() && userBox.get().getPassword().equals(DigestUtils.md5Hex(password))) {
            this.sessionObject.setUser(userBox.get());

        }
    }
    @Override
    public void logout() {
        this.sessionObject.setUser(null);
    }

    @Override
    public boolean register(RegisterUser registerUser) {
        if (!this.userDatabase.getUserByLogin(registerUser.getLogin()).isPresent()) {
            registerUser.setPassword(DigestUtils.md5Hex(registerUser.getPassword()));
            User user=new User();
            user.setId(registerUser.getId());
            user.setMail(registerUser.getMail());
            user.setPassword(registerUser.getPassword());
            user.setLogin(registerUser.getLogin());
            user.setSurname(registerUser.getSurname());
            user.setName(registerUser.getName());
            this.userDatabase.addUser(user);
            return true;

         /*
        if(!this.userDatabase.getUserByLogin(registerUser.getLogin()).isPresent()) {
            registerUser.setPassword(DigestUtils.md5Hex(registerUser.getPassword()));
            this.userDatabase.addUser(registerUser);
            return true;

          */

        }
        return false;
    }
}

