package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.IntegrationTestContext;
import cz.fi.muni.pa165.dao.interfaces.EmployeeDao;
import cz.fi.muni.pa165.entity.Employee;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Test
@ContextConfiguration(classes = IntegrationTestContext.class)
public class EmployeeDaoImplTest extends AbstractTestNGSpringContextTests {

    private EmployeeDao uut;

    @BeforeMethod
    public void setUp()
    {
        uut = new EmployeeDaoImpl();
    }

    @Test
    public void testTest() {
        Employee entity = new Employee();
        entity.setName("John Doe");
        uut.persist(entity);
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
        // TODO
        throw new NotImplementedException();
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
