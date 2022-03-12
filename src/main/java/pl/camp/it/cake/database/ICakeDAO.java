package pl.camp.it.cake.database;

import pl.camp.it.cake.model.Cake;
import pl.camp.it.cake.model.Add;

import java.util.List;
import java.util.Optional;

public interface ICakeDAO {
    List<Cake> getCakes();
    Optional<Cake> getCakeByTitle(String title);
    Optional<Cake> getCakeById(int id);
    void updateCake(Cake cake);
}
