package cz.fi.muni.pa165.service.interfaces;

import cz.fi.muni.pa165.entity.Employee;

/**
 * @author Jozef Krcho
 */
public interface EmployeeService extends Service<Employee, Long> {

    Employee findByEmail(String email);

    void registerEmployee(Employee employee, String unencryptedPassword) throws IllegalArgumentException;

    boolean authenticate(Employee employee, String password);

}
