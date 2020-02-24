package app.dao.impl;

import app.dao.interfaces.ProductDAO;
import app.model.entities.Product;
import app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public void addProduct(Product product) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateProduct(Product product) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(product);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Product getProductById(Long product_id) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query= session.createQuery("from Product where id=:id");
        query.setParameter("id", product_id);
        Product product = (Product) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Product> list = session.createQuery("from Product").list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public List<Product> getAllProductsCount() {
        return null;
    }

    @Override
    public void deleteProduct(Long product_id) {
        Product product = getProductById(product_id);
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(product);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Product> getProductsByWarehouse(Long warehouse_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(
                " select p "
                        + " from Product p INNER JOIN p.warehouse_id wh"
                        + " where wh.id = :warehouseId"
        );
        query.setParameter("warehouseId", warehouse_id);
        List<Product> products = query.list();
        session.getTransaction().commit();
        session.close();
        return products;
    }

    @Override
    public List<Product> getProductsByParam(String param, String value) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        StringBuilder strQuery = new StringBuilder()
                .append("from Product")
                .append("where ")
                .append(param).append("=:")
                .append(param);
        Query query= session.createQuery(strQuery.toString());
        query.setParameter(param, value);
        List<Product> products = query.list();
        session.getTransaction().commit();
        session.close();
        return products;
    }
}
