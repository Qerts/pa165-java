package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.config.ServiceConfiguration;
import cz.fi.muni.pa165.dto.EmployeeDTO;
import cz.fi.muni.pa165.dto.JourneyDTO;
import cz.fi.muni.pa165.dto.VehicleDTO;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.Journey;
import cz.fi.muni.pa165.entity.Vehicle;
import cz.fi.muni.pa165.enums.Role;
import cz.fi.muni.pa165.service.JourneyServiceImpl;
import cz.fi.muni.pa165.service.interfaces.BeanMappingService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.*;

/**
 * @author Martin Schmidt
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class JourneyFacadeImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private BeanMappingService beanMappingService;

    @InjectMocks
    private JourneyFacadeImpl journeyFacade;

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
    private JourneyDTO journeyDTO;

    @BeforeMethod
    public void init() {
        in = new GregorianCalendar(2016, 10, 10).getTime();
        out = new GregorianCalendar(2016, 9, 1).getTime();
        employee = new Employee("john.doe@muni.cz", "John", "Doe", "password", Role.EMPLOYEE);
        vehicle = mock(Vehicle.class);
        journey = new Journey(in, vehicle, employee);
        EmployeeDTO employeeDTO = new EmployeeDTO("john.doe@muni.cz", "John", "Doe", "password", Role.EMPLOYEE);
        VehicleDTO vehicleDTO = new VehicleDTO("vrp", "type", 2012, "engineType", "vin", 20000l, false);
        journeyDTO = new JourneyDTO(in, vehicleDTO, employeeDTO);

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
        when(journeyService.getAllJourneys(any(Date.class), any(Date.class), any(Long.class))).thenReturn(Collections.singletonList(journey));
        journeyFacade.getJourneys(in, out, employee.getId());
        verify(journeyService).getAllJourneys(in, out, employee.getId());
    }

    @Test
    public void testBeginJourney() {
        when(journeyService.beginJourney(vehicle.getId(), employee.getId(), in)).thenReturn(journey);
        when(beanMappingService.mapTo(journey, JourneyDTO.class)).thenReturn(journeyDTO);
        journeyFacade.beginJourney(vehicle.getId(), employee.getId(), in);
        verify(journeyService).beginJourney(vehicle.getId(), employee.getId(), in);
    }

    @Test
    public void testFinishJourney() {
        float distance = 1000f;
        journeyFacade.finishJourney(journey.getId(), distance, in);
        verify(journeyService).finishJourney(journey.getId(), distance, in);
    }

}
