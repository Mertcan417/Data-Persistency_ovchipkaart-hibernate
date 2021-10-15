package nl.hu.dp;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOHibernate {

    private Session currentSession;

    public ProductDAOHibernate(Session currentSession) {
        this.currentSession = currentSession;
    }

    public boolean save(Product product) {
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.save(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean update(Product product) {
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.update(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public boolean delete(Product product) {
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.delete(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) {
        Transaction transaction = currentSession.beginTransaction();
        try {
            List<Product> alleproducten = currentSession.createQuery("FROM Product", Product.class).getResultList();
            List<Product> ovchipkaartProducten = new ArrayList<>();
            for (Product product : alleproducten) {
                if (product.getOvChipkaarten().contains(ovChipkaart)) {
                    ovchipkaartProducten.add(product);
                }
            }
            transaction.commit();
            return ovchipkaartProducten;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Product> findAll() {
        Transaction transaction = currentSession.beginTransaction();
        try {
            List<Product> productList = currentSession.createQuery("FROM Product", Product.class).getResultList();
            transaction.commit();
            return productList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
