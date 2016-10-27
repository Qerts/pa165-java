package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.InMemoryDatabaseTestContext;
import cz.fi.muni.pa165.dao.interfaces.EmployeeDao;
import cz.fi.muni.pa165.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@ContextConfiguration(classes = InMemoryDatabaseTestContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class EmployeeDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private EmployeeDao employeeDao;

    @BeforeMethod
    public void setUp() {
    }

    @Test
    public void testFindById() {
        // TODO
        throw new NotImplementedException();
    }

    @Test
    public void testFindAll() {
        // TODO
        throw new NotImplementedException();
    }

    @Test
    public void testPersist() {
        Employee entity = new Employee("John", "Doe");
        employeeDao.persist(entity);
    }

    @Test
    public void testMerge() {
        // TODO
        throw new NotImplementedException();
    }

    @Test
    public void testRemove() {
        // TODO
        throw new NotImplementedException();
    }

    @Test
    public void testRemoveById() {
        // TODO
        throw new NotImplementedException();
    }
}
