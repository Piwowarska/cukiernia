package pl.camp.it.cake.database.imp.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.cake.database.ICakeDAO;
import pl.camp.it.cake.model.Add;
import pl.camp.it.cake.model.Cake;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;


@Repository
public class CakeDAOImpl implements ICakeDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Cake> getCakes() {
        Session session=this.sessionFactory.openSession();
        Query<Cake> query = session.createQuery("FROM pl.camp.it.cake.model.Cake");
        List<Cake> cakes = query.getResultList();
        session.close();
        return cakes;
    }

    @Override
    public Optional<Cake> getCakeByTitle(String title) {
        Session session=this.sessionFactory.openSession();
        Query<Cake> query=session.createQuery("FROM pl.camp.it.cake.model.Cake WHERE title= :title");
        query.setParameter("title",title);
        try {
            Cake cake = query.getSingleResult();
            session.close();
            return Optional.of(cake);
        }catch(NoResultException e){
            session.close();
            return Optional.empty();
        }
    }
     @Override
    public Optional<Cake> getCakeById(int id){
        Session session= this.sessionFactory.openSession();
        Query<Cake> query=session.createQuery("FROM pl.camp.it.cake.model.Cake WHERE id= :id ");
        query.setParameter("id",id);
        try {
            Cake cake = query.getSingleResult();
            session.close();
            return Optional.of(cake);
        }catch(NoResultException e){
            session.close();
            return Optional.empty();
        }
    }
//dodane
    @Override
    public void updateCake(Cake cake){
        Session session=this.sessionFactory.openSession();
        Transaction tx=null;
        try {
            tx = session.beginTransaction();
            session.update(cake);
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
