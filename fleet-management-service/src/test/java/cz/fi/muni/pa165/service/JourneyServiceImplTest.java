package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.config.ServiceConfiguration;
import cz.fi.muni.pa165.dao.interfaces.EmployeeDao;
import cz.fi.muni.pa165.dao.interfaces.JourneyDao;
import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.Journey;
import cz.fi.muni.pa165.entity.Vehicle;
import cz.fi.muni.pa165.enums.Role;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Martin Schmidt
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class JourneyServiceImplTest extends AbstractTestNGSpringContextTests {
    @Mock
    private JourneyDao journeyDao;

    @Mock
    private EmployeeDao employeeDao;

    @Mock
    private VehicleDao vehicleDao;

    @Inject
    @InjectMocks
    private JourneyServiceImpl journeyService;

    private Date in;
    private Date out;
    private Employee employee;
    private Vehicle vehicle;

    @BeforeMethod
    public void createCommon() {
        in = new GregorianCalendar(2016, 10, 10).getTime();
        out = new GregorianCalendar(2016, 9, 1).getTime();
        employee = new Employee("john.doe@muni.cz", "John", "Doe", "password", Role.EMPLOYEE);
        vehicle = mock(Vehicle.class);
    }

    @BeforeClass
    public void setUp() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetJourney() {
        // Arrange
        Journey expectedJourney = new Journey(in, mock(Vehicle.class), mock(Employee.class));
        when(journeyDao.findById(any(Long.class))).thenReturn(expectedJourney);

        // Act
        Journey returnedJourney = journeyService.findById(expectedJourney.getId());

        // Assert
        Assert.assertEquals(returnedJourney, expectedJourney);
    }

    @Test
    public void testGetAllJourneys() {
        Date from = new GregorianCalendar(2016, 10, 1).getTime();
        Date to = new GregorianCalendar(2016, 11, 1).getTime();

        Journey journey1 = new Journey(in, vehicle, employee);
        Journey journey2 = new Journey(out, vehicle, employee);
        List<Journey> journeyList = new ArrayList<>();
        journeyList.add(journey1);
        journeyList.add(journey2);

        when(employeeDao.findById(any(Long.class))).thenReturn(employee);

        when(journeyDao.findByEmployee(any(Employee.class))).thenReturn(journeyList);

        Assert.assertEquals(journeyService.getAllJourneys(from, to, employee.getId()).get(0), journey1);

    }

    @Test
    public void testGetJourneysByEmployee() {
        Journey journey1 = new Journey(in, vehicle, employee);
        when(journeyDao.findByEmployee(any(Employee.class))).thenReturn(Collections.singletonList(journey1));

        Assert.assertEquals(journeyService.getJourneysByEmployee(employee.getId()).get(0), journey1);
    }

    @Test
    public void testStartJourney() {
        Date startDate = new GregorianCalendar(2016, 10, 1).getTime();

        when(employeeDao.findById(any(Long.class))).thenReturn(employee);
        when(vehicleDao.findById(any(Long.class))).thenReturn(vehicle);
        journeyService.startJourney(vehicle.getId(), employee.getId(),startDate);

        Journey expectedJourney = new Journey(startDate, vehicle, employee);

        verify(journeyDao).persist(expectedJourney);

    }

    @Test
    public void testFinishJourney() {
        Date startDate = new GregorianCalendar(2016, 10, 1).getTime();
        Date endDate = new GregorianCalendar(2016, 10, 20).getTime();
        Float distance = 1000f;
        Journey journey1 = new Journey(startDate, vehicle, employee);

        when(journeyDao.findById(any(Long.class))).thenReturn(journey1);
        journeyService.finishJourney(journey1.getId(),distance,endDate);

        Journey expectedJourney = journey1;
        expectedJourney.setDistance(distance);
        expectedJourney.setReturnedAt(endDate);

        verify(journeyDao).merge(expectedJourney);

    }


}
