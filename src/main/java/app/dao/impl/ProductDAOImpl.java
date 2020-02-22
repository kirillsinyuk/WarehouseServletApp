package app.dao.impl;

import app.dao.interfaces.ProductDAO;
import app.entities.Product;
import app.entities.Warehouse;
import app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public void addProduct(Product product) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(product);
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
    public void updateProduct(Product product) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.merge(product);
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
    public Product getProductById(Long product_id) {
        Session session = null;
        Product product = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            product = (Product) session.load(Product.class, product_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        Session session = null;
        List<Product> products = new ArrayList();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            products = session.createQuery("from product").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return products;
    }

    @Override
    public void deleteProduct(Product product) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(product);
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
    public List<Product> getProductsByWarehouse(Warehouse warehouse) {
        Session session = null;
        List<Product> products = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Long warehouseId = warehouse.getId();
            Query query = session.createQuery(
                    " select p "
                            + " from product p INNER JOIN p.warehouse_id wh"
                            + " where wh.id = :warehouseId"
            )
                    .setLong("warehouseId", warehouseId);
            products = (List<Product>) query.list();
            session.getTransaction().commit();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return products;
    }
}
