package cz.fi.muni.pa165.service.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * @author Richard Trebichavský
 */
public interface Service<T, ID extends Serializable> {

    /**
     * Find entity by ID.
     *
     * @param id ID of entity.
     * @return Entity.
     */
    T findById(ID id);

    /**
     * Find all entities.
     *
     * @return List of entities.
     */
    List<T> findAll();

    /**
     * Save new entity.
     *
     * @param entity Entity.
     */
    void create(T entity);

    /**
     * Update saved entity.
     *
     * @param entity Entity.
     */
    void update(T entity);

    /**
     * Remove saved entity.
     *
     * @param entity Entity.
     */
    void remove(T entity);
}
