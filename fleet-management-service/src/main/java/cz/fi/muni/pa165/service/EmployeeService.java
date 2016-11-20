package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JozeFe on 11/20/2016.
 * @author Jozef Krcho
 */
@Service
public interface EmployeeService {
    /**
     * find employee by id
     * @param id id of employee
     * @return  employee
     */
    public Employee findById(Long id);

    /**
     * find all employees
     * @return list of employees
     */
    public List<Employee> findAll();

    /**
     * save new employee
     * @param employee employee
     */
    public void create(Employee employee);

    /**
     * update saved employee
     * @param employee employee
     */
    public void update(Employee employee);

    /**
     * remove saved employee
     * @param employee employee
     */
    public void remove(Employee employee);
}
