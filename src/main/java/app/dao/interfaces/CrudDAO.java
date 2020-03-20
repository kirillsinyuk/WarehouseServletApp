package app.dao.interfaces;

import app.util.HibernateUtil;
import org.hibernate.Session;

public interface CrudDAO<T> extends CommonDAO<T> {

    default void update(T item){
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(item);
        session.getTransaction().commit();
        session.close();
    }

    default void delete(Class<T> clazz, Long id){
        T item = getById(clazz, id);
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }

}
