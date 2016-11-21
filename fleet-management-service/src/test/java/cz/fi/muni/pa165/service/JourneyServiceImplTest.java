package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.config.ServiceConfiguration;
import cz.fi.muni.pa165.dao.interfaces.JourneyDao;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.Journey;
import cz.fi.muni.pa165.entity.Vehicle;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

/**
 * Created by Martin on 19.11.2016.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class JourneyServiceImplTest extends AbstractTestNGSpringContextTests {
    @Mock
    private JourneyDao journeyDao;

    @Autowired
    @InjectMocks
    private JourneyService journeyService;


    @BeforeMethod
    public void setUp() {
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetAllJourneys() {
        Date from = new GregorianCalendar(2016, 10, 1).getTime();
        Date to = new GregorianCalendar(2016, 11, 1).getTime();
        Date in = new GregorianCalendar(2016, 10, 10).getTime();
        Date out = new GregorianCalendar(2016, 9, 1).getTime();

        Employee employee = new Employee("John", "Doe");
        Vehicle vehicle = mock(Vehicle.class);

        Journey journey1 = new Journey(in, vehicle, employee);
        Journey journey2 = new Journey(out, vehicle, employee);
        List<Journey> journeyList = new ArrayList<>();
        journeyList.add(journey1);
        journeyList.add(journey2);

        when(journeyDao.findByEmployee(any(Employee.class))).thenReturn(journeyList);

        Assert.assertEquals(journeyService.getAllJourneys(from, to, employee).get(0),journey1);

    }

}