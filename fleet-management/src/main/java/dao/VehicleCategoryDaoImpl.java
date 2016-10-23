package dao;

import entity.VehicleCategory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Martin on 23.10.2016.
 * @author Martin Schmidt
 */
@Repository
public class VehicleCategoryDaoImpl implements VehicleCategoryDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(VehicleCategory vc) {
        em.persist(vc);
    }

    @Override
    public VehicleCategory findById(Long id) {
        return em.find(VehicleCategory.class, id);
    }

    @Override
    public List<VehicleCategory> findAll() {
        return em.createQuery("SELECT v FROM VehicleCategory v", VehicleCategory.class)
                .getResultList();
    }

    @Override
    public VehicleCategory update(VehicleCategory vc) {
        return em.merge(vc);
    }

    @Override
    public VehicleCategory findByName(String name) {
        return em.createQuery("SELECT v FROM VehicleCategory v WHERE v.title LIKE :name", VehicleCategory.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
