package app.dao.impl;

import app.dao.interfaces.CommonDAO;
import app.model.entities.docs.Movement;
import app.util.HibernateUtil;
import org.hibernate.Session;

public class MovementDAOImpl implements CommonDAO<Movement> {

    @Override
    public void add(Movement movement) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
//        Query query= session.createQuery("from Product where id IN ?1");
//        query.setParameter(1, movement.getProducts());
//        List<Product> product = query.getResultList();
//        product.forEach(pr -> pr.setWarehouse(movement.getWarehouseTo()));
//        session.save(product);
        session.save(movement);
        session.getTransaction().commit();
        session.close();
    }

}
