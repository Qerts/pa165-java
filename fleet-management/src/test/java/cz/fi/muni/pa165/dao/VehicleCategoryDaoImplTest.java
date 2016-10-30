package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.InMemoryDatabaseTestContext;
import cz.fi.muni.pa165.dao.interfaces.VehicleCategoryDao;
import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
import cz.fi.muni.pa165.entity.Vehicle;
import cz.fi.muni.pa165.entity.VehicleCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

/**
 * Created by MBalicky on 23/10/2016.
 * @author Michal Balický
 */

@ContextConfiguration(classes = InMemoryDatabaseTestContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class VehicleCategoryDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private VehicleCategoryDao vehicleCategoryDao;

    private VehicleCategory vehicleCategory1;
    private VehicleCategory vehicleCategory2;

    private Vehicle vehicle1;
    private Vehicle vehicle2;

    @BeforeMethod
    public void setUp() {
        vehicle1 = new Vehicle();
        vehicle1.setEngineType("Diesel engine");
        vehicle1.setInitialDrivenDistance(new Long(105792));
        vehicle1.setProductionYear("2012");
        vehicle1.setType("Skoda Superb Greenline 1.6 TDI");
        vehicle1.setVin("2GNFLEEK1D6142368");
        vehicle1.setVrp("3A28888");

        vehicle2 = new Vehicle();
        vehicle2.setEngineType("Petrol engine");
        vehicle2.setInitialDrivenDistance(new Long(234889));
        vehicle2.setProductionYear("2000");
        vehicle2.setType("Skoda Octavia 1.6");
        vehicle2.setVin("1NXBR32E07Z887915");
        vehicle2.setVrp("3B33333");

        Set<Vehicle> set = new HashSet<Vehicle>();
        set.add(vehicle2);

        vehicleCategory1 = new VehicleCategory();
        vehicleCategory1.setName("Supply");
        vehicleCategory1.setVehicles(set);
        vehicleCategoryDao.persist(vehicleCategory1);

        set.clear();
        set.add(vehicle1);

        vehicleCategory2 = new VehicleCategory();
        vehicleCategory2.setName("Representational");
        vehicleCategory2.setVehicles(set);
        vehicleCategoryDao.persist(vehicleCategory2);
    }

    @Test
    public void testFindById() {
        // Act
        VehicleCategory foundVehiclecCategory = vehicleCategoryDao.findById(vehicleCategory1.getId());

        // Assert
        Assert.assertEquals(foundVehiclecCategory, vehicleCategory1);
    }

    @Test
    public void testFindAll() {
        // Act
        List<VehicleCategory> foundVehicleCategories = vehicleCategoryDao.findAll();

        // Assert
        Assert.assertEquals(foundVehicleCategories.get(0), vehicleCategory1);
        Assert.assertEquals(foundVehicleCategories.get(1), vehicleCategory2);
    }

    @Test
    public void testPersist() {
        // Arrange
        int itemCountBefore = vehicleCategoryDao.findAll().size();

        // Act
        VehicleCategory vehicleCategory = new VehicleCategory();
        vehicleCategory.setName("Facility Department");
        vehicleCategoryDao.persist(vehicleCategory);

        // Assert
        Assert.assertEquals(vehicleCategoryDao.findAll().size(), itemCountBefore + 1);
    }

    @Test
    public void testMerge() {
        // Arrange
        String name = "MadeUpName";
        VehicleCategory foundVehicleCategory = vehicleCategoryDao.findById(vehicleCategory1.getId());
        foundVehicleCategory.setName(name);

        // Act
        vehicleCategoryDao.merge(foundVehicleCategory);

        // Assert
        VehicleCategory foundAfterMergeVehicleCategory = vehicleCategoryDao.findById(vehicleCategory1.getId());
        Assert.assertEquals(foundAfterMergeVehicleCategory.getName(), name);
    }

    @Test
    public void testRemove() {
        // Arrange
        int itemCountBefore = vehicleCategoryDao.findAll().size();

        // Act
        vehicleCategoryDao.remove(vehicleCategory1);

        // Assert
        Assert.assertEquals(vehicleCategoryDao.findAll().size(), itemCountBefore - 1);
    }

    @Test
    public void testRemoveById() {
        // Arrange
        int itemCountBefore = vehicleCategoryDao.findAll().size();

        // Act
        vehicleCategoryDao.remove(vehicleCategory1);

        // Assert
        Assert.assertEquals(vehicleCategoryDao.findAll().size(), itemCountBefore - 1);
    }
}
