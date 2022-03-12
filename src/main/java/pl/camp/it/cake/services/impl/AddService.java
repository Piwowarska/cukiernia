package pl.camp.it.cake.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.cake.database.IAddDAO;
import pl.camp.it.cake.model.*;
import pl.camp.it.cake.services.IAddService;
import pl.camp.it.cake.session.SessionObject;

import javax.annotation.Resource;
import java.util.*;

@Service
public class AddService implements IAddService {

    @Autowired
    IAddDAO addDatabase;

    @Resource
    SessionObject sessionObject;

    @Override
    public List<Add> getAllAddes() {
        return this.addDatabase.getAddes();
    }


    @Override
    public void getAdd(Add add) {
        CartAdd cartadd = this.sessionObject.getCartAdd();
        List<OrderPositionAdd> orderPositionsAdd = cartadd.getPositionAdd();

        for (OrderPositionAdd orderPositionAdd : orderPositionsAdd) {
            Optional<Add> addTable = this.addDatabase.getAddByTitle(orderPositionAdd.getAdd().getAddTitle());
            if (!addTable.isPresent()) {
                return;
            }
        }
    }
}



