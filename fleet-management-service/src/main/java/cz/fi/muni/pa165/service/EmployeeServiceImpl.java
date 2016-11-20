package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.EmployeeDao;
import cz.fi.muni.pa165.entity.Employee;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by JozeFe on 11/20/2016.
 * @author Jozef Krcho
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private EmployeeDao employeeDao;

    @Override
    public Employee findById(Long id) {
        return employeeDao.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public void create(Employee employee) {
        employeeDao.persist(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeDao.merge(employee);

    }

    @Override
    public void remove(Employee employee) {
        employeeDao.remove(employee);
    }
}
