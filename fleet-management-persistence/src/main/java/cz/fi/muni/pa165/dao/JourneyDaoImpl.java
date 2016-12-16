package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.dao.interfaces.JourneyDao;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.Journey;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Richard Trebichavský + Martin Schmidt + Michal Balicky
 */
@Repository
public class JourneyDaoImpl extends JpaDao<Journey, Long> implements JourneyDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Journey> findAllByVehicleId(long vehicleId) {

        Query query = this.em.createQuery(
                "SELECT j " +
                        "FROM Journey j " +
                        "INNER JOIN j.vehicle v " +
                        "WHERE v.id = :vehicleId",
                Journey.class);

        query.setParameter("vehicleId", vehicleId);

        List<Journey> result = query.getResultList();

        return result;
    }


    @Override
    public List<Journey> findByEmployee(Employee e) {
        TypedQuery<Journey> query = em.createQuery(
                "Select j from Journey j where j.employee = :employeeid",
                Journey.class);

        query.setParameter("employeeid", e);
        return query.getResultList();
    }

}
