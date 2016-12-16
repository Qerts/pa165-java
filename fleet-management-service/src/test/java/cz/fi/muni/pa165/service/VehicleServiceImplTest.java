package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.config.ServiceConfiguration;
import cz.fi.muni.pa165.dao.interfaces.EmployeeDao;
import cz.fi.muni.pa165.dao.interfaces.JourneyDao;
import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.Journey;
import cz.fi.muni.pa165.entity.Vehicle;
import cz.fi.muni.pa165.enums.Role;
import org.dozer.inject.Inject;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by Michal Balicky on 23/11/2016.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class VehicleServiceImplTest {

    @Mock
    private VehicleDao vehicleDao;
    @Mock
    private EmployeeDao employeeDao;
    @Mock
    private JourneyDao journeyDao;
    @Inject
    @InjectMocks
    private VehicleServiceImpl vehicleService = new VehicleServiceImpl();

    private Employee e1;
    private Vehicle v1;
    private Journey j1;
    private Journey j2;
    private Journey j3;
    private Journey j4;

    @BeforeTest
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);

        this.e1 = new Employee("email@email.com", "Name", "Username", "PasswordHash", Role.EMPLOYEE);
        e1.setId(0L);
        this.v1 = new Vehicle("VRP", "Type", 2222, "EngineType", "VIN", (long) 666.6);
        v1.setId(0L);
        this.j1 = new Journey(new Date(), v1, e1);
        j1.returnVehicle(new Date(), (float) 2342.1);
        this.j2 = new Journey(new Date(), v1, e1);
        j2.returnVehicle(new Date(), (float) 243);
        this.j3 = new Journey(new Date(), v1, e1);
        j3.returnVehicle(new Date(), (float) 2.0);
        this.j4 = new Journey(new Date(), v1, e1);
        j4.returnVehicle(new Date(), (float) 738.9);

        List<Journey> list = new LinkedList();
        list.add(j1);
        list.add(j2);
        list.add(j3);
        list.add(j4);

        List<Vehicle> vehicleList = new LinkedList();
        vehicleList.add(v1);

        when(this.vehicleDao.findById(anyLong())).thenReturn(v1);
        when(this.journeyDao.findAllByVehicleId(any(long.class))).thenReturn(list);
        when(this.vehicleDao.findVehiclesAvailable(0L)).thenReturn(vehicleList);
    }

    @Test
    public void testGetTotalKilometrage() {
        double expectedKilometrage =
                v1.getInitialKilometrage() +
                        j1.getDistance() +
                        j2.getDistance() +
                        j3.getDistance() +
                        j4.getDistance();

        double kilometrage = this.vehicleService.getTotalKilometrage(v1.getId());

        Assert.assertEquals(expectedKilometrage, kilometrage);
    }

    @Test
    public void testSoftDelete() {
        this.vehicleService.disable(v1.getId());
        Vehicle v = this.vehicleService.findById(v1.getId());
        Assert.assertEquals(v.getActive(), Boolean.FALSE);
    }

    @Test
    public void testGetAllAvailable() {
        List<Vehicle> list = this.vehicleService.findVehiclesAvailable(e1.getId());

        Assert.assertEquals(list.size(), 1);
    }
}
