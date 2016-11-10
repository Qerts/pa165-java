package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.InMemoryDatabaseTestContext;
import cz.fi.muni.pa165.dao.interfaces.EmployeeDao;
import cz.fi.muni.pa165.dao.interfaces.JourneyDao;
import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.Journey;
import cz.fi.muni.pa165.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Year;
import java.util.Date;

@ContextConfiguration(classes = InMemoryDatabaseTestContext.class)
public class OddTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private JourneyDao journeyDao;

    @Test
    public void oddBehaviourTest() {
        // Arrange
        Employee employee1 = new Employee("John", "Doe");
        Employee employee2 = new Employee("Joushua", "Bloch");
        employeeDao.persist(employee1);
        employeeDao.persist(employee2);
        Vehicle vehicle1 = new Vehicle("VRP1", "Type1", Year.of(1999), "EngineType1", "VIN1", (long) 7658.54);
        Vehicle vehicle2 = new Vehicle("VRP2", "Type2", Year.of(1999), "EngineType2", "VIN2", (long) 7658.54);
        vehicleDao.persist(vehicle1);
        vehicleDao.persist(vehicle2);
        Journey journey1 = new Journey(new Date(), vehicle1, employee1);
        Journey journey2 = new Journey(new Date(), vehicle2, employee2);
        journeyDao.persist(journey1);
        journeyDao.persist(journey2);

        // Act & Assert

        // It's TRUE - OK
        Assert.assertTrue(
            journeyDao.findById(journey1.getId()).equals(
                journeyDao.findById(journey2.getId())
            )
        );

        // It's FALSE - BUT WHY SHOULD IT BE?
        Assert.assertFalse(
            journeyDao.findById(journey1.getId()).getId().equals(
                journeyDao.findById(journey2.getId()).getId()
            )
        );

        // It's FALSE - BUT WHY SHOULD IT BE?
        Assert.assertFalse(
            journeyDao.findById(journey1.getId()).getEmployee().equals(
                journeyDao.findById(journey2.getId()).getEmployee()
            )
        );

        // It's FALSE - BUT WHY SHOULD IT BE?
        Assert.assertFalse(
            journeyDao.findById(journey1.getId()).getVehicle().equals(
                journeyDao.findById(journey2.getId()).getVehicle()
            )
        );

        Assert.assertFalse(true); // this test is not fine!
    }


    @Test
    public void endingOneJourneyEndsBothTest() {
        // Arrange
        Employee employee1 = new Employee("John", "Doe");
        Employee employee2 = new Employee("Joushua", "Bloch");
        employeeDao.persist(employee1);
        employeeDao.persist(employee2);
        Vehicle vehicle1 = new Vehicle("VRP1", "Type1", Year.of(1999), "EngineType1", "VIN1", (long) 7658.54);
        Vehicle vehicle2 = new Vehicle("VRP2", "Type2", Year.of(1999), "EngineType2", "VIN2", (long) 7658.54);
        vehicleDao.persist(vehicle1);
        vehicleDao.persist(vehicle2);
        Journey journey1 = new Journey(new Date(), vehicle1, employee1);
        Journey journey2 = new Journey(new Date(), vehicle2, employee2);
        journeyDao.persist(journey1);
        journeyDao.persist(journey2);

        // Act
        Journey foundJourney = journeyDao.findById(journey1.getId());
        foundJourney.returnVehicle(new Date(), (float) 150);
        journeyDao.persist(foundJourney);

        // Assert
        Journey foundJourney1 = journeyDao.findById(journey1.getId());
        Journey foundJourney2 = journeyDao.findById(journey1.getId());
        Assert.assertNotNull(foundJourney1.getReturnedAt());
        // Actually, this is NOT NULL! That's BIG FAIL!
        Assert.assertNull(foundJourney2.getReturnedAt());
    }

}

