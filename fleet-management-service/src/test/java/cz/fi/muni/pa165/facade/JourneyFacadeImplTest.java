package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.Journey;
import cz.fi.muni.pa165.entity.Vehicle;
import cz.fi.muni.pa165.enums.Role;
import cz.fi.muni.pa165.service.BeanMappingServiceImpl;
import cz.fi.muni.pa165.service.JourneyServiceImpl;
import cz.fi.muni.pa165.service.interfaces.BeanMappingService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

/**
 * Created by Martin on 24.11.2016.
 */
public class JourneyFacadeImplTest {

    @Spy
    @Inject
    private final BeanMappingService beanMappingService = new BeanMappingServiceImpl();

    @InjectMocks
    private final JourneyFacadeImpl journeyFacade = new JourneyFacadeImpl();

    @Mock
    private JourneyServiceImpl journeyService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Date in;
    private Date out;
    private Employee employee;
    private Vehicle vehicle;
    private Journey journey;

    @BeforeMethod
    public void init() {
        in = new GregorianCalendar(2016, 10, 10).getTime();
        out = new GregorianCalendar(2016, 9, 1).getTime();
        employee = new Employee("john.doe@muni.cz", "John", "Doe", "password", Role.EMPLOYEE);
        vehicle = mock(Vehicle.class);
        journey = new Journey(in, vehicle, employee);

    }

    @Test
    public void testGetAllJourneys() {

        when(journeyService.findAll()).thenReturn(Collections.singletonList(journey));
        journeyFacade.getAllJourneys();
        verify(journeyService).findAll();
    }

    @Test
    public void testGetJourneysByEmployee() {
        when(journeyService.getJourneysByEmployee(any(Long.class))).thenReturn(Collections.singletonList(journey));
        journeyFacade.getJourneysByEmployee(employee.getId());
        verify(journeyService).getJourneysByEmployee(employee.getId());
    }

    @Test
    public void testGetJourneys() {

    }

    @Test
    public void testBeginJourney() {

    }

    @Test
    public void testFinishJourney() {

    }

}