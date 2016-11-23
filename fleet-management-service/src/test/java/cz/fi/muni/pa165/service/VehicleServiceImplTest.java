package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.config.ServiceConfiguration;
import cz.fi.muni.pa165.dao.interfaces.EmployeeDao;
import cz.fi.muni.pa165.dao.interfaces.JourneyDao;
import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Year;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by MBalicky on 23/11/2016.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class VehicleServiceImplTest extends AbstractTestNGSpringContextTests{

    @Mock
    private VehicleDao vehicleDao;
    @Mock
    private EmployeeDao employeeDao;
    @Mock
    private JourneyDao journeyDao;
    @Autowired
    private VehicleServiceImpl vehicleService;

    @BeforeTest
    public void init() throws ServiceException{
        MockitoAnnotations.initMocks(this);
        this.vehicleService = new VehicleServiceImpl();
    }

    @Test
    public void testGetTotalKilometrage(){

        Employee e1 = new Employee("Name", "Username");
        Vehicle v1 = new Vehicle("VRP", "Type", Year.of(2222), "EngineType", "VIN", (long)666.6);
        v1.setId(0L);
        Journey j1 = new Journey(new Date(), v1, e1);
        j1.returnVehicle(new Date(), (float)2342.1);
        Journey j2 = new Journey(new Date(), v1, e1);
        j2.returnVehicle(new Date(), (float)243);
        Journey j3 = new Journey(new Date(), v1, e1);
        j3.returnVehicle(new Date(), (float)2.0);
        Journey j4 = new Journey(new Date(), v1, e1);
        j4.returnVehicle(new Date(), (float)738.9);

        this.employeeDao.persist(e1);
        //this.vehicleDao.persist(v1);
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

        when(this.vehicleDao.findById(anyLong())).thenReturn(v1);


        List<Journey> list = new LinkedList();
        list.add(j1);
        list.add(j2);
        list.add(j3);
        list.add(j4);

        when(journeyDao.findAllByVehicleId(any(long.class)))
                .thenReturn(list);

        double kilometrage = this.vehicleService.getTotalKilometrage(v1.getId());

        Assert.assertEquals(expectedKilometrage, kilometrage);
    }
}
