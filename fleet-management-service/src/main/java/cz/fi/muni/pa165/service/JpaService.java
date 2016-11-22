package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.Dao;
import cz.fi.muni.pa165.exceptions.FleetManagementDAException;

import java.io.Serializable;
import java.util.List;

/**
 * @author Richard Trebichavský
 */
public abstract class JpaService<T, ID extends Serializable> {

    abstract protected Dao<T, ID> getDao();

    /**
     * Find entity by ID.
     * @param id ID of entity.
     * @return Entity.
     */
    public T findById(ID id) {
        try {
            return getDao().findById(id);
        } catch (RuntimeException ex) {
            throw new FleetManagementDAException("Find entity by id error.", ex);
        }
    }

    /**
     * Find all entities.
     * @return List of entities.
     */
    public List<T> findAll() {
        try {
            return getDao().findAll();
        } catch(RuntimeException ex) {
            throw new FleetManagementDAException("Find all entities error.", ex);
        }
    }

    /**
     * Save new entity.
     * @param entity Entity.
     */
    public void create(T entity) {
        if (entity == null) {
            throw new FleetManagementDAException("Entity cannot be null.");
        }
        try {
            getDao().persist(entity);
        } catch(RuntimeException ex) {
            throw new FleetManagementDAException("Entity create error.", ex);
        }
    }

    /**
     * Update saved entity.
     * @param entity Entity.
     */
    public void update(T entity) {
        if (entity == null) {
            throw new FleetManagementDAException("Entity cannot be null.");
        }
        try {
            getDao().merge(entity);
        } catch(RuntimeException ex) {
            throw new FleetManagementDAException("Entity update error.", ex);
        }
    }

    /**
     * Remove saved entity.
     * @param entity Entity.
     */
    public void remove(T entity) {
        if (entity == null) {
            throw new FleetManagementDAException("Entity cannot be null.");
        }
        try {
            getDao().remove(entity);
        } catch(RuntimeException ex) {
            throw new FleetManagementDAException("Entity delete error.", ex);
        }
    }
}
