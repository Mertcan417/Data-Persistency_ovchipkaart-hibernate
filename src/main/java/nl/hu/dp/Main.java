package nl.hu.dp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 * <p>
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;

    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws SQLException {
//        testFetchAll();
        testDAOHibernate();
    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
    private static void testFetchAll() {
        Session session = getSession();
        try {
            Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                Query query = session.createQuery("from " + entityType.getName());

                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
                System.out.println();
            }
        } finally {
            session.close();
        }
    }

    private static void testDAOHibernate(){
        AdresDAOHibernate adresDAOHibernate = new AdresDAOHibernate(getSession());
        ReizigerDAOHibernate reizigerDAOHibernate = new ReizigerDAOHibernate(getSession());
        OVChipkaartDAOHibernate ovChipkaartDAOHibernate  = new OVChipkaartDAOHibernate(getSession());
        ProductDAOHibernate productDAOHibernate = new ProductDAOHibernate(getSession());

        Reiziger reiziger1 = new Reiziger(40,"H","van den","Verald", Date.valueOf("2007-05-08"));
        Reiziger reiziger2 = new Reiziger(126,"LM","","LEEME", Date.valueOf("2017-05-08"));
        Adres adres = new Adres(40,"0495LR","64","Visstraat","Amsterdam");
        OVChipkaart ovChipkaart1 = new OVChipkaart(4442,Date.valueOf("3001-04-08"),1,100);
        OVChipkaart ovChipkaart2 = new OVChipkaart(441,Date.valueOf("3400-02-04"),2,100000);
        Product product1 = new Product(420,"VIP CARD","gratis reizen voor 100 maanden",50.00);
        Product product2 = new Product(174,"SILVERE CARD","90% korting voor onbeperkt reizen ",10324.00);

        System.out.println("------------------Reiziger-------------------");
        System.out.println();
        System.out.println("[TEST] findAll()");
        reizigerDAOHibernate.findAll().forEach(value -> System.out.println(value + "\n"));

        System.out.println();

        reizigerDAOHibernate.save(reiziger1);
        System.out.println("[TEST] findByID & save");
        System.out.println(reizigerDAOHibernate.findById(40));

        System.out.println();

        System.out.println("[TEST] findByGb & update");
        reiziger1.setAchternaam("LOLOLOLOL");
        reizigerDAOHibernate.update(reiziger1);
        System.out.println(reizigerDAOHibernate.findByGbDatum(reiziger1.getGeboortedatum().toString()));

        System.out.println();

        System.out.println("----------------Adres-------------------");
        System.out.println();
        System.out.println("[TEST] findAll()");
        adresDAOHibernate.findAll().forEach(value -> System.out.println(value + "\n"));

        System.out.println();

        System.out.println("[TEST] findByReiziger & save");
        adres.setReiziger(reiziger1);
        adresDAOHibernate.save(adres);
        System.out.println(adresDAOHibernate.findByReiziger(reiziger1));

        System.out.println();

        System.out.println("[TEST] update");
        adres.setWoonplaats("TilburgTeest");
        adres.setHuisnummer("104020");
        adresDAOHibernate.update(adres);
        System.out.println(adresDAOHibernate.findByReiziger(reiziger1));

        System.out.println();

        System.out.println("----------------Product-------------------");
        System.out.println();
        System.out.println("[TEST] findAll()");
        productDAOHibernate.findAll().forEach(value -> System.out.println(value + "\n"));

        System.out.println();

        System.out.println("[TEST] findByOVChipkaart && save && bidirectionele persistentie OVChipkaart <- -> Product ");
        reizigerDAOHibernate.save(reiziger2);
        ovChipkaart2.setReiziger(reiziger2);
        ovChipkaartDAOHibernate.save(ovChipkaart2);
        product2.voegOVChipkaartToe(ovChipkaart2);
        productDAOHibernate.save(product2);
        System.out.println(productDAOHibernate.findByOVChipkaart(ovChipkaart2));

        System.out.println();

        System.out.println("[TEST] update");
        product2.setBeschrijving("Geen idee wat dit is TESTTT");
        product2.setPrijs(130.20);
        productDAOHibernate.update(product2);
        System.out.println(productDAOHibernate.findByOVChipkaart(ovChipkaart2));

        System.out.println();

        System.out.println("----------------OVChipkaart-------------------");
        System.out.println();
        System.out.println("[TEST] findAll()");
        ovChipkaartDAOHibernate.findAll().forEach(value -> System.out.println(value + "\n"));

        System.out.println();

        System.out.println("[TEST] findByReiziger && save");
        ovChipkaart1.setReiziger(reiziger1);
        ovChipkaartDAOHibernate.save(ovChipkaart1);
        System.out.println(ovChipkaartDAOHibernate.findByReiziger(reiziger1));
        System.out.println();

        System.out.println("[TEST] findById & update");
        ovChipkaart1.setSaldo(10000000);
        ovChipkaart1.setKlasse(2);
        ovChipkaartDAOHibernate.update(ovChipkaart1);
        System.out.println(ovChipkaartDAOHibernate.findById(ovChipkaart1.getKaartId()));
        System.out.println();

        System.out.println("[TEST] bidirectionele persistentie OVChipkaart <- -> Product");
        productDAOHibernate.save(product1);
        ovChipkaart1.voegProductToe(product1);
        ovChipkaartDAOHibernate.update(ovChipkaart1);
        System.out.println(ovChipkaartDAOHibernate.findById(ovChipkaart1.getKaartId()));

        System.out.println();

        System.out.println("[TEST] delete voor alle DAOHibernate klassen");

        System.out.println("LIJST VOORDAT ER WORDT VERWIJDERD:");
        ovChipkaartDAOHibernate.findAll().forEach(value -> System.out.println(value + "\n"));

        adresDAOHibernate.delete(adres);

        ovChipkaartDAOHibernate.delete(ovChipkaart1);
        ovChipkaartDAOHibernate.delete(ovChipkaart2);

        productDAOHibernate.delete(product1);
        productDAOHibernate.delete(product2);

        reizigerDAOHibernate.delete(reiziger1);
        reizigerDAOHibernate.delete(reiziger2);

        System.out.println("LIJST NADAT ER IS VERWIJDERD");
        ovChipkaartDAOHibernate.findAll().forEach(value -> System.out.println(value + "\n"));
    }
}
