package dao;

import entity.Vehicle;
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
public class VehicleDaoImpl implements VehicleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Vehicle vehicle) {
        em.persist(vehicle);
    }

    @Override
    public Vehicle findById(Long id) {
        return em.find(Vehicle.class, id);
    }

    @Override
    public List<Vehicle> findAll() {
        return em.createQuery("SELECT v FROM Vehicle v", Vehicle.class)
                .getResultList();
    }

    @Override
    public Vehicle update(Vehicle vehicle) {
            return em.merge(vehicle);
    }

    @Override
    public void remove(Vehicle vehicle) throws IllegalArgumentException {
        em.remove(findById(vehicle.getId()));
    }

    @Override
    public List<Vehicle> findByVehicleCategory(VehicleCategory vehicleCategory) {
        return em.createQuery("SELECT v FROM Vehicle v WHERE v.vehicleCategory = :vehicleCategoryId", Vehicle.class)
                .setParameter("vehicleCategoryId", vehicleCategory)
                .getResultList();
    }
}
