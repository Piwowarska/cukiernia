package pl.camp.it.cake.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.cake.database.ICakeDAO;
import pl.camp.it.cake.model.Cake;
import pl.camp.it.cake.services.ICakeService;

import java.util.List;

@Service
public class CakeService implements ICakeService {

    @Autowired
    ICakeDAO cakeDatabase;

    @Override
    public List<Cake> getAllCakes(){
        return this.cakeDatabase.getCakes();
    }
}
