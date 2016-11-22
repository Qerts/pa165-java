package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.InMemoryDatabaseContext;
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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Martin Schmidt
 */
@ContextConfiguration(classes = InMemoryDatabaseContext.class)
public class InspectionDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private InspectionDao uut;

    private Inspection inspection1;
    private Inspection inspection2;

    @BeforeMethod
    public void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 1, 1);
        Date date1 = calendar.getTime();
        inspection1 = new Inspection(date1);
        uut.persist(inspection1);
        calendar.set(2016, 2, 2);
        Date date2 = calendar.getTime();
        inspection2 = new Inspection(date2);
        uut.persist(inspection2);
    }

    @Test
    public void testFindById() {
        // Act
        Inspection foundInspection = uut.findById(inspection1.getId());

        // Assert
        Assert.assertEquals(foundInspection, inspection1);
    }

    @Test
    public void testFindAll() {
        // Act
        List<Inspection> foundInspections = uut.findAll();

        // Assert
        Assert.assertEquals(foundInspections.get(0), inspection1);
        Assert.assertEquals(foundInspections.get(1), inspection2);
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
        Inspection foundInspection = uut.findById(inspection1.getId());
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 4, 4);
        Date date = calendar.getTime();
        foundInspection.setPerformedAt(date);

        // Act
        uut.merge(foundInspection);

        // Assert
        Inspection foundAfterMergeInspection = uut.findById(inspection1.getId());
        Assert.assertEquals(foundAfterMergeInspection.getPerformedAt(), date);
        Assert.assertEquals(foundAfterMergeInspection, foundInspection);
    }

    @Test
    public void testRemove() {
        // Arrange
        int itemCountBefore = uut.findAll().size();

        // Act
        uut.remove(inspection1);

        // Assert
        Assert.assertEquals(uut.findAll().size(), itemCountBefore - 1);
    }

    @Test
    public void testRemoveById() {
        // Arrange
        int itemCountBefore = uut.findAll().size();

        // Act
        uut.removeById(inspection1.getId());

        // Assert
        Assert.assertEquals(uut.findAll().size(), itemCountBefore - 1);
    }

    @Test(expectedExceptions = Exception.class)
    public void testNullDate() {
        Inspection inspectionNullDate = new Inspection(null);
        uut.persist(inspectionNullDate);
    }
}
