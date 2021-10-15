package nl.hu.dp;

import java.util.List;

public interface AdresDAO {

    //dit is een soort contract met methodes wat ondertekent zal worden

    boolean save(Adres adres);
    boolean update(Adres adres);
    boolean delete(Adres adres);
    Adres findByReiziger(Reiziger reiziger);
    List<Adres> findAll();
}
