package app.dao.impl;

import app.dao.interfaces.CommonDAO;
import app.model.entities.Product;
import app.model.entities.docs.Receipt;
import app.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

public class ReceiptDAOImpl implements CommonDAO<Receipt> {

    @Override
    public void add(Receipt receipt) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        receipt.getProduct().forEach(pr -> pr.setId(null));
        List<Product> products =  receipt.getProduct().stream()
                .peek(pr-> {
                    Long id = (Long)session.save(pr);
                    pr.setId(id);
                })
                .collect(Collectors.toList());
        receipt.setProduct(products);
        session.save(receipt);
        session.getTransaction().commit();
        session.close();
    }

}
