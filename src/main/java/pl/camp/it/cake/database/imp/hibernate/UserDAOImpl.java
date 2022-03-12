package pl.camp.it.cake.database.imp.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.cake.database.IUserDAO;
import pl.camp.it.cake.model.User;

import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
public class UserDAOImpl implements IUserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Optional<User> getUserByLogin(String login) {
        Session session=this.sessionFactory.openSession();
        Query<User> query=session.createQuery("FROM pl.camp.it.cake.model.User WHERE login= :login");
        query.setParameter("login",login);
        try {
            User user = query.getSingleResult();
            session.close();
            return Optional.of(user);
        }catch (NoResultException e){
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public void addUser(User user) {
        Session session=this.sessionFactory.openSession();
        Transaction tx=null;
        try{
            tx=session.beginTransaction();
            session.save(user);
            tx.commit();
        }catch(Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }finally {
            session.close();
        }
    }
}

