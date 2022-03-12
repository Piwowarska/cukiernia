package pl.camp.it.cake.database.imp.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.cake.database.IAddDAO;
import pl.camp.it.cake.model.Add;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class AddDAOImp implements IAddDAO {

          @Autowired
        SessionFactory sessionFactory;

        @Override
        public List<Add> getAddes() {
            Session session=this.sessionFactory.openSession();
            Query<Add> query = session.createQuery("FROM pl.camp.it.cake.model.Add");
            List<Add> Addes = query.getResultList();
            session.close();
            return Addes;
        }

    @Override
        public Optional<Add> getAddByTitle(String addTitle) {
            Session session=this.sessionFactory.openSession();
            Query<Add> query=session.createQuery("FROM pl.camp.it.cake.model.Add WHERE addTitle= :addTitle");
            query.setParameter("addTitle",addTitle);
            try {
                Add add = query.getSingleResult();
                session.close();
                return Optional.of(add);
            }catch(NoResultException e){
                session.close();
                return Optional.empty();
            }
        }

        @Override
        public Optional<Add> getAddByAddId(int id){
            Session session= this.sessionFactory.openSession();
            Query<Add> query=session.createQuery("FROM pl.camp.it.cake.model.Add WHERE id= :id ");
            query.setParameter("id",id);
            try {
                Add add = query.getSingleResult();
                session.close();
                return Optional.of(add);
            }catch(NoResultException e){
                session.close();
                return Optional.empty();
            }
        }

        @Override
        public void updateAdd(Add add){
            Session session=this.sessionFactory.openSession();
            Transaction tx=null;
            try {
                tx = session.beginTransaction();
                session.update(add);
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


