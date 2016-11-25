package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
import cz.fi.muni.pa165.entity.Vehicle;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Martin Schmidt + Michal Balicky
 */
@Repository
public class VehicleDaoImpl extends JpaDao<Vehicle, Long> implements VehicleDao {


    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Vehicle> findVehiclesAvailable(long employeeId) {
        Query query = this.em.createQuery(
                "SELECT v " +
                    "FROM Vehicle v " +
                    "INNER JOIN v.vehicleCategory vc " +
                    "INNER JOIN vc.employees emp " +
                    "WHERE emp.id = :employeeId",
                Vehicle.class);

        query.setParameter("employeeId", employeeId);

        List<Vehicle> result = query.getResultList();

        return result;
    }
}
