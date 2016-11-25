package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.config.ServiceConfiguration;
import cz.fi.muni.pa165.dto.EmployeeDTO;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.enums.Role;
import cz.fi.muni.pa165.service.interfaces.BeanMappingService;
import cz.fi.muni.pa165.service.interfaces.EmployeeService;
import org.hibernate.service.spi.ServiceException;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Jozef Krcho
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class EmployeeFacadeImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeFacadeImpl employeeFacade;

    private Employee employee;

    private EmployeeDTO employeeDTO;

    private String email = "john.foo@muni.cz";
    private String password = "password";
    private String name = "John";
    private String surname = "Foo";
    private Long id = (long) 1;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);

        employee = new Employee(email, name, surname, password, Role.EMPLOYEE);
        employeeDTO = new EmployeeDTO(email, name, surname, password, Role.EMPLOYEE);
        employee.setId(id);
        employeeDTO.setId(id);
    }

    @Test
    public void testRegisterEmployee() {
        when(beanMappingService.mapTo(employeeDTO, Employee.class)).thenReturn(employee);

        employeeFacade.registerEmployee(employeeDTO, password);

        verify(employeeService).registerEmployee(employee, password);
    }

    @Test
    public void testAuthenticate() {
        when(beanMappingService.mapTo(employeeDTO, Employee.class)).thenReturn(employee);
        when(employeeService.authenticate(employee, password)).thenReturn(true);

        Assert.assertTrue(employeeFacade.authenticate(employeeDTO, password));

        verify(employeeService).authenticate(employee, password);
    }

    @Test
    public void testFindAllEmployee() {
        when(beanMappingService.mapTo(Collections.singletonList(employee), EmployeeDTO.class))
                .thenReturn(Collections.singletonList(employeeDTO));
        when(employeeService.findAll()).thenReturn(Collections.singletonList(employee));

        Collection<EmployeeDTO> list = employeeFacade.findAllEmployee();
        Assert.assertEquals(list.size(), 1);

        verify(employeeService).findAll();
    }

    @Test
    public void testFindByEmail() {
        when(beanMappingService.mapTo(employee, EmployeeDTO.class)).thenReturn(employeeDTO);
        when(employeeService.findByEmail(email)).thenReturn(employee);

        Assert.assertEquals(employeeFacade.findEmployeeByEmail(email).getEmail(), email);

        verify(employeeService).findByEmail(email);
    }

    @Test
    public void testFingById() {
        when(beanMappingService.mapTo(employee, EmployeeDTO.class)).thenReturn(employeeDTO);
        when(employeeService.findById(id)).thenReturn(employee);

        Assert.assertEquals(employeeFacade.findEmployeeById(id).getId(), id);

        verify(employeeService).findById(id);
    }

    @Test
    public void testRemoveById() {
        employeeFacade.removeById(id);
        verify(employeeService).removeById(id);
    }


}
