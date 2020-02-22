package app.dao.impl;

import app.dao.interfaces.WarehouseDAO;
import app.entities.Product;
import app.entities.Warehouse;
import app.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WarehouseDAOImpl implements WarehouseDAO {
    @Override
    public void addWarehouse(Warehouse warehouse) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(warehouse);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.merge(warehouse);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Warehouse getWarehousetById(Long warehouse_id) {
        Session session = null;
        Warehouse warehouse = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            warehouse = (Warehouse) session.load(Warehouse.class, warehouse_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return warehouse;
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        Session session = null;
        List<Warehouse> warehouses = new ArrayList();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            warehouses = session.createCriteria(Product.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return warehouses;
    }

    @Override
    public void deleteWarehouse(Warehouse warehouse) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(warehouse);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Collection getWarehouseByProduct(Product product) {
        return null;
    }
}
