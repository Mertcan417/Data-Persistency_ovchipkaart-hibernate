package nl.hu.dp;

import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO {

    boolean save(Reiziger reiziger) throws SQLException;

    boolean update(Reiziger reiziger) throws SQLException;

    boolean delete(Reiziger reiziger);

    Reiziger findById(int id) throws SQLException;

    List<Reiziger> findByGbdatum(String datum);

    List<Reiziger> findAll();

}
