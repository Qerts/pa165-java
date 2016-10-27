package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.dao.interfaces.EmployeeDao;
import cz.fi.muni.pa165.entity.Employee;
import org.springframework.stereotype.Repository;

/**
 * Created by JozeFe on 10/20/2016.
 * @author Jozef Krcho
 */
@Repository
public class EmployeeDaoImpl extends JpaDao<Employee, Long> implements EmployeeDao{

}
