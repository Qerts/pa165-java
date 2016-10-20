package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by JozeFe on 10/20/2016.
 * @author Jozef Krcho
 */
public abstract class JpaDao<T, ID extends Serializable> implements Dao<T, ID>{
    private Class<T> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    public JpaDao() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T findById(ID id) {
        return entityManager.find(entityClass, id);
    }

    public List<T> findAll(){
        return entityManager.createQuery("from " + entityClass.getName()).getResultList();
    }

    public void persist(T entity) {
        entityManager.persist(entity);
    }

    public void merge(T entity) {
        entityManager.merge(entity);
    }

    public void remove(T entity) {
        entityManager.remove(entity);
    }

    public void removeById(ID id) {
        T entity = this.findById(id);
        this.remove(entity);
    }
}
