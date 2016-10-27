package cz.fi.muni.pa165.dao.interfaces;

import java.util.List;

/**
 * Created by JozeFe on 10/20/2016.
 * @author Jozef Krcho
 */
public interface Dao<T,ID> {
    T findById(ID id);
    List<T> findAll();
    void persist(T entity);
    void merge(T entity);
    void remove(T entity);
    void removeById(ID id);

}
