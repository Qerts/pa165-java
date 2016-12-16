package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.InMemoryDatabaseContext;
import cz.fi.muni.pa165.dao.interfaces.EmployeeDao;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.enums.Role;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Richard Trebichavský
 */
@ContextConfiguration(classes = InMemoryDatabaseContext.class)
public class EmployeeDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Inject
    private EmployeeDao uut;

    private Employee employee1;
    private Employee employee2;

    private Employee employee1duplicate;

    @BeforeMethod
    public void setUp() {
        employee1 = new Employee("john.doe@muni.cz", "John", "Doe", "password", Role.EMPLOYEE);
        employee1duplicate = new Employee("john.doe@muni.cz", "John", "Doe", "password", Role.EMPLOYEE);
        employee2 = new Employee("jane.doe@muni.cz", "Jane", "Doe", "password", Role.EMPLOYEE);

        uut.persist(employee1);
        uut.persist(employee2);
    }

    @Test
    public void testFindById() {
        // Act
        Employee foundEmployee = uut.findById(employee1.getId());

        // Assert
        Assert.assertEquals(foundEmployee, employee1);
    }

    @Test
    public void testFindAll() {
        // Act
        List<Employee> foundEmployees = uut.findAll();

        // Assert
        Assert.assertEquals(foundEmployees.get(0), employee1);
        Assert.assertEquals(foundEmployees.get(1), employee2);
    }

    @Test
    public void testFindByEmail() {
        // Act
        Employee foundEmployee1 = uut.findByEmail(employee1.getEmail());
        Employee foundEmployee2 = uut.findByEmail(employee2.getEmail());
        // Assert
        Assert.assertEquals(foundEmployee1, employee1);
        Assert.assertEquals(foundEmployee2, employee2);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testFindByNullEmail() {
        // Act
        Employee foundEmployee1 = uut.findByEmail(null);
        // Assert
        Assert.assertEquals(foundEmployee1, employee1);
    }

    @Test
    public void testPersist() {
        // Arrange
        int itemCountBefore = uut.findAll().size();

        // Act
        Employee entity = new Employee("jim.doe@muni.cz", "Jim", "Doe", "password", Role.EMPLOYEE);
        uut.persist(entity);

        // Assert
        Assert.assertEquals(uut.findAll().size(), itemCountBefore + 1);
    }

    @Test
    public void testMerge() {
        // Arrange
        Employee foundEmployee = uut.findById(employee1.getId());
        foundEmployee.setName("Johnny");

        // Act
        uut.merge(foundEmployee);

        // Assert
        Employee foundAfterMergeEmployee = uut.findById(employee1.getId());
        Assert.assertEquals(foundAfterMergeEmployee.getName(), "Johnny");
        Assert.assertEquals(foundAfterMergeEmployee, foundEmployee);
    }

    @Test
    public void testRemove() {
        // Arrange
        int itemCountBefore = uut.findAll().size();

        // Act
        uut.remove(employee1);

        // Assert
        Assert.assertEquals(uut.findAll().size(), itemCountBefore - 1);
    }

    @Test
    public void testRemoveById() {
        // Arrange
        int itemCountBefore = uut.findAll().size();

        // Act
        uut.removeById(employee1.getId());

        // Assert
        Assert.assertEquals(uut.findAll().size(), itemCountBefore - 1);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testUniqueEmail() {
        uut.persist(employee1duplicate);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testNullEmail() {
        Employee employeeNullEmail = new Employee(null, "NullEmail", "NullEmail", "password", Role.EMPLOYEE);
        uut.persist(employeeNullEmail);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testNullName() {
        Employee employeeNullName = new Employee("nullname@muni.cz", null, "NullName", "password", Role.EMPLOYEE);
        uut.persist(employeeNullName);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testNullSurname() {
        Employee employeeNullSurame = new Employee("nullsurname@muni.cz", "NullSurname", null, "password", Role.EMPLOYEE);
        uut.persist(employeeNullSurame);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testNullPassword() {
        Employee employeeNullPassword = new Employee("nullPassword@muni.cz", "NullPass", "NullPass", null, Role.EMPLOYEE);
        uut.persist(employeeNullPassword);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testNullRole() {
        Employee employeeNullRole = new Employee("nullRole@muni.cz", "NullRole", "NullRole", "password", null);
        uut.persist(employeeNullRole);
    }
}
