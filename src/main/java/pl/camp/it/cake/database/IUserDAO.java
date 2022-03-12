package pl.camp.it.cake.database;

import pl.camp.it.cake.model.User;

import java.util.Optional;

public interface IUserDAO {
   //List<User> getUsers();
    Optional<User> getUserByLogin(String login);
    void addUser(User user);
}
