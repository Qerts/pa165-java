package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.InMemoryDatabaseTestContext;
import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
import cz.fi.muni.pa165.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Year;
import java.util.List;

/**
 * Created by JozeFe on 10/30/2016.
 * @author Jozef Krcho
 */

@ContextConfiguration(classes = InMemoryDatabaseTestContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class VehicleDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private VehicleDao vehicleDao;

    private Vehicle vehicle1;
    private Vehicle vehicle2;

    @BeforeMethod
    public void setUp() {
        vehicle1 = new Vehicle();
        vehicle1.setEngineType("Diesel engine");
        vehicle1.setInitialKilometrage(105792L);
        vehicle1.setProductionYear(Year.of(2012));
        vehicle1.setType("Skoda Superb Greenline 1.6 TDI");
        vehicle1.setVin("2GNFLEEK1D6142368");
        vehicle1.setVrp("3A28888");
        vehicleDao.persist(vehicle1);

        vehicle2 = new Vehicle();
        vehicle2.setEngineType("Petrol engine");
        vehicle2.setInitialKilometrage(234889L);
        vehicle2.setProductionYear(Year.of(2000));
        vehicle2.setType("Skoda Octavia 1.6");
        vehicle2.setVin("1NXBR32E07Z887915");
        vehicle2.setVrp("3B33333");
        vehicleDao.persist(vehicle2);
    }

    @Test
    public void testFindById() {
        // Act
        Vehicle foundVehicle = vehicleDao.findById(vehicle1.getId());

        // Assert
        Assert.assertEquals(foundVehicle, vehicle1);
    }

    @Test
    public void testFindAll() {
        // Act
        List<Vehicle> foundVehicles = vehicleDao.findAll();

        // Assert
        Assert.assertEquals(foundVehicles.get(0), vehicle1);
        Assert.assertEquals(foundVehicles.get(1), vehicle2);
    }

    @Test
    public void testPersist() {
        // Arrange
        int itemCountBefore = vehicleDao.findAll().size();

        // Act
        Vehicle vehicle = new Vehicle();
        vehicle.setEngineType("Petrol engine");
        vehicle.setInitialKilometrage(493256L);
        vehicle.setProductionYear(Year.of(2000));
        vehicle.setType("Skoda Octavia 1.6");
        vehicle.setVin("JH4DC54835S007069");
        vehicle.setVrp("0FIMUNI");
        vehicleDao.persist(vehicle);

        // Assert
        Assert.assertEquals(vehicleDao.findAll().size(), itemCountBefore + 1);
    }

    @Test
    public void testMerge() {
        // Arrange
        Vehicle foundVehicle = vehicleDao.findById(vehicle1.getId());
        foundVehicle.setProductionYear(Year.of(1994));

        // Act
        vehicleDao.merge(foundVehicle);

        // Assert
        Vehicle foundAfterMergeEmployee = vehicleDao.findById(vehicle1.getId());
        Assert.assertEquals(foundAfterMergeEmployee.getProductionYear(), Year.of(1994));
        Assert.assertEquals(foundAfterMergeEmployee, foundVehicle);
    }

    @Test
    public void testRemove() {
        // Arrange
        int itemCountBefore = vehicleDao.findAll().size();

        // Act
        vehicleDao.remove(vehicle1);

        // Assert
        Assert.assertEquals(vehicleDao.findAll().size(), itemCountBefore - 1);
    }

    @Test
    public void testRemoveById() {
        // Arrange
        int itemCountBefore = vehicleDao.findAll().size();

        // Act
        vehicleDao.remove(vehicle1);

        // Assert
        Assert.assertEquals(vehicleDao.findAll().size(), itemCountBefore - 1);
    }
}
