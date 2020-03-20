package app.dao.interfaces;

import app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public interface CommonDAO<T> {

    default void add(T item){
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    default T getById(Class<T> clazz, Long id){
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query= session.createQuery("from " + clazz.getName() +"  where id=:id");
        query.setParameter("id", id);
        T item = (T) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return item;
    }

    default List<T> getAll(Class<T> clazz){
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<T> list = session.createQuery("from " + clazz.getName()).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}
