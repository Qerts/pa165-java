package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.dao.interfaces.JourneyDao;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.Journey;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;


/**
 * @author Richard Trebichavský + Martin Schmidt
 */
@Repository
public class JourneyDaoImpl extends JpaDao<Journey, Long> implements JourneyDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Journey> findByEmployee(Employee e) {
        TypedQuery<Journey> query = em.createQuery(
                "Select j from Journey j where j.employee = :employeeid",
                Journey.class);

        query.setParameter("employeeid", e);
        return query.getResultList();
    }
}
