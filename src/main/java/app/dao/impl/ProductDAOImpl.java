package app.dao.impl;

import app.dao.interfaces.CrudDAO;
import app.model.entities.Product;
import app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAOImpl implements CrudDAO<Product> {

    public List<Product> getProductsByWarehouse(Long warehouse_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(
                " select p "
                        + " from Product p JOIN Warehouse wh"
                        + " ON p.warehouse.id=wh.id"
                        + " where wh.id=:warehouseId"
        );
        query.setParameter("warehouseId", warehouse_id);
        List<Product> products = query.list();
        session.getTransaction().commit();
        session.close();
        return products;
    }

    public List<Product> getProductsByParam(String param, String value) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        StringBuilder strQuery = new StringBuilder()
                .append("from Product ")
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
