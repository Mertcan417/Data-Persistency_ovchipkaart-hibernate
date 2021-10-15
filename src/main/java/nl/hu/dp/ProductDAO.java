package nl.hu.dp;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {

    boolean save(Product product) throws SQLException;

    boolean update(Product product) throws SQLException;

    boolean delete(Product product) throws SQLException;

    List<Product> findByOVChipkaart(OVChipkaart ovChipkaart);

    List<Product> findAll();


}
