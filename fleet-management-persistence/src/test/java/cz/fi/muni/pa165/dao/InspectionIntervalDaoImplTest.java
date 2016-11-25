package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.InMemoryDatabaseContext;
import cz.fi.muni.pa165.dao.interfaces.InspectionIntervalDao;
import cz.fi.muni.pa165.entity.InspectionInterval;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Martin Schmidt
 */
@ContextConfiguration(classes = InMemoryDatabaseContext.class)
public class InspectionIntervalDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Inject
    private InspectionIntervalDao uut;

    private InspectionInterval InspectionInterval1;
    private InspectionInterval InspectionInterval2;

    @BeforeMethod
    public void setUp() {
        InspectionInterval1 = new InspectionInterval("brakes", 12);
        uut.persist(InspectionInterval1);
        InspectionInterval2 = new InspectionInterval("oil",6);
        uut.persist(InspectionInterval2);
    }

    @Test
    public void testFindById() {
        // Act
        InspectionInterval foundInspectionInterval = uut.findById(InspectionInterval1.getId());

        // Assert
        Assert.assertEquals(foundInspectionInterval, InspectionInterval1);
    }

    @Test
    public void testFindAll() {
        // Act
        List<InspectionInterval> foundInspectionIntervals = uut.findAll();

        // Assert
        Assert.assertEquals(foundInspectionIntervals.get(0), InspectionInterval1);
        Assert.assertEquals(foundInspectionIntervals.get(1), InspectionInterval2);
    }

    @Test
    public void testPersist() {
        // Arrange
        int itemCountBefore = uut.findAll().size();

        // Act
        InspectionInterval entity = new InspectionInterval("tyres", 6);
        uut.persist(entity);

        // Assert
        Assert.assertEquals(uut.findAll().size(), itemCountBefore + 1);
    }

    @Test
    public void testMerge() {
        // Arrange
        InspectionInterval foundInspectionInterval = uut.findById(InspectionInterval1.getId());
        foundInspectionInterval.setName("steering");

        // Act
        uut.merge(foundInspectionInterval);

        // Assert
        InspectionInterval foundAfterMergeInspectionInterval = uut.findById(InspectionInterval1.getId());
        Assert.assertEquals(foundAfterMergeInspectionInterval.getName(), "steering");
        Assert.assertEquals(foundAfterMergeInspectionInterval, foundInspectionInterval);
    }

    @Test
    public void testRemove() {
        // Arrange
        int itemCountBefore = uut.findAll().size();

        // Act
        uut.remove(InspectionInterval1);

        // Assert
        Assert.assertEquals(uut.findAll().size(), itemCountBefore - 1);
    }

    @Test
    public void testRemoveById() {
        // Arrange
        int itemCountBefore = uut.findAll().size();

        // Act
        uut.removeById(InspectionInterval1.getId());

        // Assert
        Assert.assertEquals(uut.findAll().size(), itemCountBefore - 1);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testNullName() {
        InspectionInterval inspectionIntervalNullName = new InspectionInterval(null, 6);
        uut.persist(inspectionIntervalNullName);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testUniqueName() {
        InspectionInterval inspectionIntervalUnique1 = new InspectionInterval("unique", 12);
        InspectionInterval inspectionIntervalUnique2 = new InspectionInterval("unique", 30);
        uut.persist(inspectionIntervalUnique1);
        uut.persist(inspectionIntervalUnique2);
    }
}
