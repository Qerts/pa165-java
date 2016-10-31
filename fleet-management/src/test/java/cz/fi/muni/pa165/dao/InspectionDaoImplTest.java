package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.InMemoryDatabaseTestContext;
import cz.fi.muni.pa165.dao.interfaces.InspectionDao;
import cz.fi.muni.pa165.entity.Inspection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Martin Schmidt
 */
@ContextConfiguration(classes = InMemoryDatabaseTestContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class InspectionDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private InspectionDao uut;

    private Inspection Inspection1;
    private Inspection Inspection2;

    @BeforeMethod
    public void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 1, 1);
        Date date1 = calendar.getTime();
        Inspection1 = new Inspection(date1);
        uut.persist(Inspection1);
        calendar.set(2016, 2, 2);
        Date date2 = calendar.getTime();
        Inspection2 = new Inspection(date2);
        uut.persist(Inspection2);
    }

    @Test
    public void testFindById() {
        // Act
        Inspection foundInspection = uut.findById(Inspection1.getId());

        // Assert
        Assert.assertEquals(foundInspection, Inspection1);
    }

    @Test
    public void testFindAll() {
        // Act
        List<Inspection> foundInspections = uut.findAll();

        // Assert
        Assert.assertEquals(foundInspections.get(0), Inspection1);
        Assert.assertEquals(foundInspections.get(1), Inspection2);
    }

    @Test
    public void testPersist() {
        // Arrange
        int itemCountBefore = uut.findAll().size();

        // Act
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 3, 3);
        Date date = calendar.getTime();
        Inspection entity = new Inspection(date);
        uut.persist(entity);

        // Assert
        Assert.assertEquals(uut.findAll().size(), itemCountBefore + 1);
    }

    @Test
    public void testMerge() {
        // Arrange
        Inspection foundInspection = uut.findById(Inspection1.getId());
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 4, 4);
        Date date = calendar.getTime();
        foundInspection.setPerformedAt(date);

        // Act
        uut.merge(foundInspection);

        // Assert
        Inspection foundAfterMergeInspection = uut.findById(Inspection1.getId());
        Assert.assertEquals(foundAfterMergeInspection.getPerformedAt(), date);
        Assert.assertEquals(foundAfterMergeInspection, foundInspection);
    }

    @Test
    public void testRemove() {
        // Arrange
        int itemCountBefore = uut.findAll().size();

        // Act
        uut.remove(Inspection1);

        // Assert
        Assert.assertEquals(uut.findAll().size(), itemCountBefore - 1);
    }

    @Test
    public void testRemoveById() {
        // Arrange
        int itemCountBefore = uut.findAll().size();

        // Act
        uut.removeById(Inspection1.getId());

        // Assert
        Assert.assertEquals(uut.findAll().size(), itemCountBefore - 1);
    }
}
