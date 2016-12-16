package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.dao.interfaces.EmployeeDao;
import cz.fi.muni.pa165.entity.Employee;
import org.springframework.stereotype.Repository;

/**
 * @author Jozef Krcho
 */
@Repository
public class EmployeeDaoImpl extends JpaDao<Employee, Long> implements EmployeeDao {

    @Override
    public Employee findByEmail(String email) {
        if (email == null || email.isEmpty())
            throw new IllegalArgumentException("Cannot search for null e-mail");
        return getEntityManager().createQuery(
                "Select e from Employee e where e.email = :email",
                Employee.class).setParameter("email", email).getSingleResult();
    }
}
