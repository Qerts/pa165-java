package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.Dao;
import cz.fi.muni.pa165.dao.interfaces.EmployeeDao;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.service.interfaces.EmployeeService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Jozef Krcho
 */
@Service
public class EmployeeServiceImpl extends JpaService<Employee, Long> implements EmployeeService {

    @Inject
    private EmployeeDao employeeDao;

    @Override
    protected Dao<Employee, Long> getDao() {
        return employeeDao;
    }
}
