package cz.fi.muni.pa165.dao.interfaces;

import java.util.List;

/**
 * Created by JozeFe on 10/20/2016.
 * @author Jozef Krcho
 */
public interface Dao<T,ID> {
    /**
     * Finds entity by ID.
     *
     * @param id Entity ID.
     * @return Entity
     */
    T findById(ID id);

    /**
     * Returns list of all entities.
     *
     * @return List of entities.
     */
    List<T> findAll();

    /**
     * Make an instance managed and persistent.
     *
     * The entity itself is saved after invocation of {@see EntityManage::flush()} method.
     * @todo Is this comment correct?
     *
     * @param entity Entity instance.
     */
    void persist(T entity);

    /**
     * Updates entity by merging the state of the given entity into the current persistence context.
     *
     * @param entity Entity instance.
     */
    void merge(T entity);

    /**
     * Remove the entity instance.
     *
     * @param entity Entity instance.
     */
    void remove(T entity);

    /**
     * Removed entity instance by ID.
     *
     * @param id Entity ID.
     */
    void removeById(ID id);
}
