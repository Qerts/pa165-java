package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.EmployeeDTO;

import java.util.Collection;

/**
 * @author Jozef Krcho
 */
public interface EmployeeFacade {

    /**
     * Find employee by id
     *
     * @param id id
     * @return employee by given id
     */
    EmployeeDTO findEmployeeById(Long id);

    /**
     * Find employee by email
     *
     * @param email registred email
     * @return employee with specific email
     */
    EmployeeDTO findEmployeeByEmail(String email);

    /**
     * Register employee with the unencrypted password
     *
     * @param employeeDTO         employee for registration
     * @param unencryptedPassword employee password
     */
    void registerEmployee(EmployeeDTO employeeDTO, String unencryptedPassword);

    /**
     * Return Collection of all employees
     *
     * @return employee collection
     */
    Collection<EmployeeDTO> findAllEmployee();

    /**
     * authenticate employee password
     *
     * @param employeeDTO registred employee
     * @param password    password for check
     * @return true if password is correct, false if it's ot equal with employee password
     */
    boolean authenticate(EmployeeDTO employeeDTO, String password);

    /**
     * Remove employee by id
     *
     * @param id employee id
     */
    void removeById(Long id);
}
