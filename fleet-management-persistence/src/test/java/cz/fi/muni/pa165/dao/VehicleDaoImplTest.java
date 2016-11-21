package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.InMemoryDatabaseContext;
import cz.fi.muni.pa165.dao.interfaces.EmployeeDao;
import cz.fi.muni.pa165.dao.interfaces.JourneyDao;
import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.Journey;
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
import java.util.Date;
import java.util.List;

/**
 * Created by JozeFe on 10/30/2016.
 * @author Jozef Krcho
 */

@ContextConfiguration(classes = InMemoryDatabaseContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class VehicleDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private JourneyDao journeyDao;

    private Vehicle vehicle1;
    private Vehicle vehicle2;

    @BeforeMethod
    public void setUp() {
        vehicle1 = new Vehicle("VRP", "Type", Year.of(1999), "EngineType", "VIN", (long) 7658.54);
        vehicle1.setEngineType("Diesel engine");
        vehicle1.setInitialKilometrage(105792L);
        vehicle1.setProductionYear(Year.of(2012));
        vehicle1.setType("Skoda Superb Greenline 1.6 TDI");
        vehicle1.setVin("2GNFLEEK1D6142368");
        vehicle1.setVrp("3A28888");
        vehicleDao.persist(vehicle1);

        vehicle2 = new Vehicle("VRP", "Type", Year.of(1999), "EngineType", "VIN", (long) 7658.54);
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
        Vehicle vehicle = new Vehicle("VRP", "Type", Year.of(1999), "EngineType", "VIN", (long) 7658.54);
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
        vehicleDao.removeById(vehicle1.getId());

        // Assert
        Assert.assertEquals(vehicleDao.findAll().size(), itemCountBefore - 1);
    }



    @Test
    public void testGetTotalKilometrage(){

        Employee e1 = new Employee("Name", "Username");
        Vehicle v1 = new Vehicle("VRP", "Type", Year.of(2222), "EngineType", "VIN", (long)666.6);
        Journey j1 = new Journey(new Date(), v1, e1);
        j1.returnVehicle(new Date(), (float)2342.1);
        Journey j2 = new Journey(new Date(), v1, e1);
        j2.returnVehicle(new Date(), (float)243);
        Journey j3 = new Journey(new Date(), v1, e1);
        j3.returnVehicle(new Date(), (float)2.0);
        Journey j4 = new Journey(new Date(), v1, e1);
        j4.returnVehicle(new Date(), (float)738.9);

        this.employeeDao.persist(e1);
        this.vehicleDao.persist(v1);
        this.journeyDao.persist(j1);
        this.journeyDao.persist(j2);
        this.journeyDao.persist(j3);
        this.journeyDao.persist(j4);

        double expectedKilometrage =
                        v1.getInitialKilometrage() +
                        j1.getDistance() +
                        j2.getDistance() +
                        j3.getDistance() +
                        j4.getDistance();

        double kilometrage = this.vehicleDao.getTotalKilometrage(1, this.journeyDao);

        Assert.assertEquals(expectedKilometrage, kilometrage);
    }
}
