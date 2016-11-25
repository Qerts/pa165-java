package cz.fi.muni.pa165.dao.interfaces;

import cz.fi.muni.pa165.entity.Employee;

/**
 * @author Jozef Krcho
 */
public interface EmployeeDao extends Dao<Employee, Long>{

    /**
     * find Employee by given email
     * @param email Email.
     * @return Employee.
     */
    Employee findByEmail(String email);

}
