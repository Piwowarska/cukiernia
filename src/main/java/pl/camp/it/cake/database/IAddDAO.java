package pl.camp.it.cake.database;


import pl.camp.it.cake.model.Add;
import java.util.List;
import java.util.Optional;

public interface IAddDAO {

        List<Add> getAddes();
        Optional<Add> getAddByTitle(String title);
        Optional<Add> getAddByAddId(int id);

        void updateAdd(Add add);
    }


