package nl.hu.dp;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OVChipkaartDAOHibernate {

    private Session currentSession;

    public OVChipkaartDAOHibernate(Session currentSession) {
        this.currentSession = currentSession;
    }

    public boolean save(OVChipkaart ovChipkaart) {
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.save(ovChipkaart);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean update(OVChipkaart ovChipkaart) {
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.update(ovChipkaart);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean delete(OVChipkaart ovChipkaart) {
        Transaction transaction = currentSession.beginTransaction();
        try {
//            currentSession.createQuery(
            currentSession.delete(ovChipkaart);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        Transaction transaction = currentSession.beginTransaction();
        try {
            List<OVChipkaart> ovChipkaarten = currentSession.createQuery("FROM OVChipkaart WHERE reiziger_id = " + reiziger.getId(), OVChipkaart.class).getResultList();
            transaction.commit();
            return ovChipkaarten;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<OVChipkaart> findAll() {
        Transaction transaction = currentSession.beginTransaction();
        try {
            List<OVChipkaart> ovChipkaarten = currentSession.createQuery("FROM OVChipkaart", OVChipkaart.class).getResultList();
            transaction.commit();
            return ovChipkaarten;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public OVChipkaart findById(int id) {
        Transaction transaction = currentSession.beginTransaction();
        try {
            OVChipkaart ovChipkaart = currentSession.createQuery("FROM OVChipkaart WHERE id =" + id, OVChipkaart.class).getSingleResult();
            transaction.commit();
            return ovChipkaart;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
