package nl.hu.dp;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;

public class ReizigerDAOHibernate {
    private Session currentSession;

    public ReizigerDAOHibernate(Session currentSession) {
        this.currentSession = currentSession;
    }

    public boolean save(Reiziger reiziger) {
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.save(reiziger);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean update(Reiziger reiziger) {
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.update(reiziger);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean delete(Reiziger reiziger) {
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.delete(reiziger);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public Reiziger findById(int id) {
        Transaction transaction = currentSession.beginTransaction();
        try {
            Reiziger reiziger = currentSession.createQuery("FROM Reiziger WHERE id = " + id, Reiziger.class).getSingleResult();
            transaction.commit();
            return reiziger;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Reiziger> findByGbDatum(String datum) {
        Transaction transaction = currentSession.beginTransaction();
        try {
            List<Reiziger> reizigers = currentSession.createQuery("FROM Reiziger WHERE geboortedatum = ' "+datum+"'", Reiziger.class).getResultList();
            transaction.commit();
            return reizigers;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Reiziger> findAll() {
            Transaction transaction = currentSession.beginTransaction();
        try {
            List<Reiziger> reizigers = currentSession.createQuery("FROM Reiziger", Reiziger.class).getResultList();
            transaction.commit();
            return reizigers;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
