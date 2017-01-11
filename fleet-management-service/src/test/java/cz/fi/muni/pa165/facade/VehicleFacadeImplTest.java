package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.config.ServiceConfiguration;
import cz.fi.muni.pa165.dto.VehicleCreateDTO;
import cz.fi.muni.pa165.dto.VehicleDTO;
import cz.fi.muni.pa165.entity.Vehicle;
import cz.fi.muni.pa165.entity.VehicleCategory;
import cz.fi.muni.pa165.service.interfaces.BeanMappingService;
import cz.fi.muni.pa165.service.interfaces.VehicleCategoryService;
import cz.fi.muni.pa165.service.interfaces.VehicleService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Jozef Krcho
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class VehicleFacadeImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private VehicleService vehicleService;

    @Mock
    private VehicleCategoryService vehicleCategoryService;

    @InjectMocks
    private VehicleFacadeImpl vehicleFacade;

    private Vehicle vehicle;

    private VehicleDTO vehicleDTO;

    private VehicleCreateDTO vehicleCreateDTO;

    private Long id = (long) 1;
    private String vrp = "VRP";
    private String type = "car type";
    private int productionYear = 1994;
    private String engineType = "engine Type";
    private String vin = "vin code";
    private Long initialKilometrage = (long) 10000;
    private boolean active = false;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);

        vehicle = new Vehicle(vrp, type, productionYear, engineType, vin, initialKilometrage);
        vehicleDTO = new VehicleDTO(vrp, type, productionYear, engineType, vin, initialKilometrage, active);
        vehicleCreateDTO = new VehicleCreateDTO(vrp, type, productionYear, engineType, vin, initialKilometrage, active);
        vehicle.setId(id);
        vehicleDTO.setId(id);


    }

    @Test
    public void testFindVehiclesAvailable() {
        when(beanMappingService.mapTo(Collections.singletonList(vehicle), VehicleDTO.class))
                .thenReturn(Collections.singletonList(vehicleDTO));
        when(vehicleService.findVehiclesToBeBorrowedByUser(id)).thenReturn(Collections.singletonList(vehicle));

        Assert.assertEquals(vehicleFacade.findVehiclesToBeBorrowedByUser(id).size(), 1);

        verify(vehicleService).findVehiclesToBeBorrowedByUser(id);
    }

    @Test
    public void testGetTotalKilometrage() {
        when(vehicleService.getTotalKilometrage(id)).thenReturn((double) initialKilometrage);

        Assert.assertEquals(vehicleFacade.getTotalKilometrage(id), (double) initialKilometrage);

        verify(vehicleService).getTotalKilometrage(id);
    }

    @Test
    public void testFindVehicleById() {
        when(beanMappingService.mapTo(vehicle, VehicleDTO.class)).thenReturn(vehicleDTO);
        when(vehicleService.findById(id)).thenReturn(vehicle);

        Assert.assertEquals(vehicleFacade.findVehicleById(id).getId(), id);

        verify(vehicleService).findById(id);
    }

    @Test
    public void testAddNewVehicle() {
        when(beanMappingService.mapTo(vehicleCreateDTO, Vehicle.class)).thenReturn(vehicle);
        when(vehicleCategoryService.findById(any())).thenReturn(new VehicleCategory("new Category"));

        vehicleFacade.addNewVehicle(vehicleCreateDTO);

        verify(vehicleService).create(vehicle);
    }

    @Test
    public void testUpdateVehicle() {
        when(beanMappingService.mapTo(vehicleDTO, Vehicle.class)).thenReturn(vehicle);

        vehicleFacade.updateVehicle(vehicleDTO);

        verify(vehicleService).update(vehicle);
    }

    @Test
    public void testDisableVehicle() {
        vehicleFacade.disableVehicle(id);
        verify(vehicleService).disable(id);
    }
}
