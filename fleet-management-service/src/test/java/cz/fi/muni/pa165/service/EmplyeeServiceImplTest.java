package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.config.ServiceConfiguration;
import cz.fi.muni.pa165.dao.interfaces.EmployeeDao;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.enums.Role;
import cz.fi.muni.pa165.service.interfaces.EmployeeService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.inject.Inject;

/**
 * @author Jozef Krcho
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class EmplyeeServiceImplTest extends AbstractTransactionalTestNGSpringContextTests {


    @Mock
    private EmployeeDao employeeDao;

    @Inject
    @InjectMocks
    private EmployeeService employeeService;

    @BeforeClass
    public void setUp() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAuthenticate() {
        Employee employee = new Employee("joe.foo@muni.cz","joe", "foo", "pass", Role.EMPLOYEE);
        String password = "password";
        String wrongPass = "wrongPassword";
        employeeService.registerEmployee(employee, password);

        Assert.assertTrue(employeeService.authenticate(employee, password));
        Assert.assertFalse(employeeService.authenticate(employee, wrongPass));
        Assert.assertFalse(employeeService.authenticate(employee, null));
        Assert.assertFalse(employeeService.authenticate(null, password));
        Assert.assertFalse(employeeService.authenticate(null, null));
    }
}
