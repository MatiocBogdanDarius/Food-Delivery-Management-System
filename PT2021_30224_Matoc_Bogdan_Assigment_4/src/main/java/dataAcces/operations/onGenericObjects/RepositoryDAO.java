package dataAcces.operations.onGenericObjects;

import java.io.Serializable;
import java.util.List;

/**
 *this class contains the header of the methods for interacting with the database
 */
public interface RepositoryDAO<T extends Serializable> {
    List<T> findAll();
    List<T> findByField(String fieldName, String fieldValue);
    void add(T object);
    void update(T object);
    void delete(T object);
}
