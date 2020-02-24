package app.dao.impl;

import app.dao.interfaces.WarehouseDAO;
import app.model.entities.Warehouse;
import app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class WarehouseDAOImpl implements WarehouseDAO {
    @Override
    public void addWarehouse(Warehouse warehouse) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(warehouse);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(warehouse);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Warehouse getWarehouseById(Long warehouse_id) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query= session.createQuery("from Warehouse where id=:id");
        query.setParameter("id", warehouse_id);
        Warehouse warehouse = (Warehouse) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return warehouse;
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Warehouse> list = session.createQuery("from Warehouse ").list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public void deleteWarehouse(Long warehouse_id) {
        Warehouse warehouse = getWarehouseById(warehouse_id);
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(warehouse);
        session.getTransaction().commit();
        session.close();
    }
}
