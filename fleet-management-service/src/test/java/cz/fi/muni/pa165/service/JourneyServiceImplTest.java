package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.config.ServiceConfiguration;
import cz.fi.muni.pa165.dao.interfaces.EmployeeDao;
import cz.fi.muni.pa165.dao.interfaces.JourneyDao;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.Journey;
import cz.fi.muni.pa165.entity.Vehicle;
import cz.fi.muni.pa165.enums.Role;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
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

    @Autowired
    @InjectMocks
    private JourneyServiceImpl journeyService;


    @BeforeClass
    public void setUp() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetJourney() {
        // Arrange
        Date in = new GregorianCalendar(2016, 10, 10).getTime();
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
        Date in = new GregorianCalendar(2016, 10, 10).getTime();
        Date out = new GregorianCalendar(2016, 9, 1).getTime();

        Employee employee = new Employee("john.doe@muni.cz", "John", "Doe", "password", Role.EMPLOYEE);
        Vehicle vehicle = mock(Vehicle.class);

        Journey journey1 = new Journey(in, vehicle, employee);
        Journey journey2 = new Journey(out, vehicle, employee);
        List<Journey> journeyList = new ArrayList<>();
        journeyList.add(journey1);
        journeyList.add(journey2);

        when(employeeDao.findById(any(Long.class))).thenReturn(employee);

        when(journeyDao.findByEmployee(any(Employee.class))).thenReturn(journeyList);

        Assert.assertEquals(journeyService.getAllJourneys(from, to, employee.getId()).get(0), journey1);

    }

}
