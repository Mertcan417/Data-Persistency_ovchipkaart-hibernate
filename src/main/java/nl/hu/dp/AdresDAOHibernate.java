package nl.hu.dp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class AdresDAOHibernate implements AdresDAO {

    private Session currentSession;

    public AdresDAOHibernate(Session currentSession) {
        this.currentSession = currentSession;
    }

    @Override
    public boolean save(Adres adres) {
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.save(adres);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.update(adres);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    @Override
    public boolean delete(Adres adres) {
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.delete(adres);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        Transaction transaction = currentSession.beginTransaction();
        try {
            Adres adres = (Adres) currentSession.createQuery("FROM Adres WHERE reiziger_id = " + reiziger.getId(), Adres.class).getSingleResult();
            transaction.commit();
            return adres;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Adres> findAll() {
        Transaction transaction = currentSession.beginTransaction();
        try {
            List<Adres> adressen = currentSession.createQuery("FROM Adres", Adres.class).getResultList();
            List<Adres> adresList = new ArrayList<>(adressen);
            transaction.commit();
            return adresList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
