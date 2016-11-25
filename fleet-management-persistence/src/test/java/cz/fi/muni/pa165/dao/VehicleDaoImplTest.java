package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.InMemoryDatabaseContext;
import cz.fi.muni.pa165.dao.interfaces.EmployeeDao;
import cz.fi.muni.pa165.dao.interfaces.JourneyDao;
import cz.fi.muni.pa165.dao.interfaces.VehicleCategoryDao;
import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.Journey;
import cz.fi.muni.pa165.entity.Vehicle;
import cz.fi.muni.pa165.entity.VehicleCategory;
import cz.fi.muni.pa165.enums.Role;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by JozeFe on 10/30/2016.
 * @author Jozef Krcho
 */

@ContextConfiguration(classes = InMemoryDatabaseContext.class)
public class VehicleDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private JourneyDao journeyDao;
    @Autowired
    private VehicleCategoryDao vehicleCategoryDao;

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
                       
    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testNullVrp() {
        Vehicle vehicleNullVrp = new Vehicle(null, "type", Year.of(1994), "enigneType", "VinNullVrp",(long) 5000);
        vehicleDao.persist(vehicleNullVrp);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testUniqueVrp() {
        Vehicle vehicleUniqueVrp1 = new Vehicle("Unique", "type", Year.of(1994), "enigneType", "VinUniqueVrp1",(long) 5000);
        Vehicle vehicleUniqueVrp2 = new Vehicle("Unique", "type", Year.of(1994), "enigneType", "VinUniqueVrp2",(long) 5000);
        vehicleDao.persist(vehicleUniqueVrp1);
        vehicleDao.persist(vehicleUniqueVrp2);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testNullType() {
        Vehicle vehicleNullType = new Vehicle("NullType", null, Year.of(1994), "enigneType", "VinNullType",(long) 5000);
        vehicleDao.persist(vehicleNullType);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testNullYear() {
        Vehicle vehicleNullYear = new Vehicle("NullYear", "type", null, "enigneType", "VinNullYear",(long) 5000);
        vehicleDao.persist(vehicleNullYear);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testNullEngineType() {
        Vehicle vehicleNullEngineType= new Vehicle("NullEngineType", "type", Year.of(1994), null, "VinNullEngineType",(long) 5000);
        vehicleDao.persist(vehicleNullEngineType);        
    }

 
    @Test(expectedExceptions = org.springframework.orm.jpa.JpaSystemException.class)
    public void testNullVin() {
        Vehicle vehicleNullVin = new Vehicle("NullVin", "type", Year.of(1994), "enigneType", null,(long) 5000);
        vehicleDao.persist(vehicleNullVin);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testUniqueVin() {
        Vehicle vehicleUniqueVin1 = new Vehicle("UniqueVin1", "type", Year.of(1994), "enigneType", "VinUnique",(long) 5000);
        Vehicle vehicleUniqueVin2 = new Vehicle("UniqueVin2", "type", Year.of(1994), "enigneType", "VinUnique",(long) 5000);
        vehicleDao.persist(vehicleUniqueVin1);
        vehicleDao.persist(vehicleUniqueVin2);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testNullInitialKilometrage() {
        Vehicle vehicleNullInitialKilometrage = new Vehicle("NullInitialKilometrage", "type", Year.of(1994), "enigneType"
                , "VinNullInitialKilometrage", null);
        vehicleDao.persist(vehicleNullInitialKilometrage);
    }

    @Test
    public void findVehiclesAvailableTest(){
        Employee emp1 = new Employee("email@seznikov.com", "name", "surname", "hash", Role.EMPLOYEE);
        Vehicle veh1 = new Vehicle("vrp1", "type", Year.of(1991), "engineType", "vin1", 666L);
        Vehicle veh2 = new Vehicle("vrp2", "type", Year.of(1991), "engineType", "vin2", 666L);
        Vehicle veh3 = new Vehicle("vrp3", "type", Year.of(1991), "engineType", "vin3", 666L);
        VehicleCategory vc1 = new VehicleCategory("category");
        Set<VehicleCategory> set = new HashSet();
        set.add(vc1);
        emp1.setVehicleCategories(set);
        veh1.setVehicleCategory(vc1);
        veh2.setVehicleCategory(vc1);
        veh3.setVehicleCategory(vc1);

        this.vehicleDao.persist(veh1);
        this.vehicleDao.persist(veh2);
        this.vehicleDao.persist(veh3);
        this.employeeDao.persist(emp1);
        this.vehicleCategoryDao.persist(vc1);

        List<Vehicle> list = this.vehicleDao.findVehiclesAvailable(emp1.getId());

        Assert.assertEquals(list.size(), 3);
    }
}
