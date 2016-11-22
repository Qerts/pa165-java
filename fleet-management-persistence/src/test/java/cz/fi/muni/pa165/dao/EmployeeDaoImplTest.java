package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.InMemoryDatabaseContext;
import cz.fi.muni.pa165.dao.interfaces.EmployeeDao;
import cz.fi.muni.pa165.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Richard Trebichavský
 */
@ContextConfiguration(classes = InMemoryDatabaseContext.class)
public class EmployeeDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private EmployeeDao uut;

    private Employee employee1;
    private Employee employee2;

    private Employee employee1duplicate;

    @BeforeMethod
    public void setUp() {
        employee1 = new Employee("John", "Doe");
        employee1duplicate = new Employee("John", "Doe");
        employee2 = new Employee("Jane", "Doe");

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
    public void testPersist() {
        // Arrange
        int itemCountBefore = uut.findAll().size();

        // Act
        Employee entity = new Employee("Jim", "Doe");
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

    @Test(expectedExceptions = org.springframework.orm.jpa.JpaSystemException.class)
    public void testUniqueConstraint() {
        uut.persist(employee1duplicate);
    }

    @Test(expectedExceptions = org.springframework.orm.jpa.JpaSystemException.class)
    public void testNullName() {
        Employee employeeNullName = new Employee(null, "NullName");
        uut.persist(employeeNullName);
    }

    @Test(expectedExceptions = org.springframework.orm.jpa.JpaSystemException.class)
    public void testNullSurname() {
        Employee employeeNullSurame = new Employee("NullSurname", null);
        uut.persist(employeeNullSurame);
    }
}
