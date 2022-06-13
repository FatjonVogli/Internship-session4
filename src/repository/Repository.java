package repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<ENTITY, ID> {

    List<ENTITY> findAll();

    ENTITY findById(ID id);

    Boolean exists(ID id);

    Boolean save(ENTITY entity) throws SQLException;

    Integer update(ENTITY entity);

}
