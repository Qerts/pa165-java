package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.config.ServiceConfiguration;
import cz.fi.muni.pa165.dto.*;
import cz.fi.muni.pa165.entity.*;
import cz.fi.muni.pa165.enums.Role;
import cz.fi.muni.pa165.service.interfaces.BeanMappingService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.time.Year;
import java.util.Date;

/**
 * @author Jozef Krcho
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class BeanMappingServiceImplTest extends AbstractTestNGSpringContextTests {

    @Inject
    private BeanMappingService beanMappingService;

    @Test
    public void testEmployeeMapping() {
        Employee employee = new Employee("employee.test@muni.cz", "employee", "test", "password", Role.EMPLOYEE);
        EmployeeDTO employeeDTO = beanMappingService.mapTo(employee, EmployeeDTO.class);

        Assert.assertEquals(employee.getEmail(), employeeDTO.getEmail());
        Assert.assertEquals(employee.getName(), employeeDTO.getName());
        Assert.assertEquals(employee.getSurname(), employeeDTO.getSurname());
        Assert.assertEquals(employee.getPasswordHash(), employeeDTO.getPasswordHash());
        Assert.assertEquals(employee.getRole(), employeeDTO.getRole());
    }

    @Test
    public void testInspectionIntervalMapping() {
        InspectionInterval inspectionInterval = new InspectionInterval("inspection name", 30);
        InspectionIntervalDTO inspectionIntervalDTO = beanMappingService.mapTo(inspectionInterval, InspectionIntervalDTO.class);

        Assert.assertEquals(inspectionInterval.getName(), inspectionIntervalDTO.getName());
        Assert.assertEquals(inspectionInterval.getDays(), inspectionIntervalDTO.getDays());
    }

    @Test
    public void testInspectionMapping() {
        Inspection inspection = new Inspection(new Date());
        InspectionDTO inspectionDTO = beanMappingService.mapTo(inspection, InspectionDTO.class);

        Assert.assertEquals(inspection.getPerformedAt(), inspectionDTO.getPerformedAt());
    }

    @Test
    public void testJourneyMapping() {
        Employee employee = new Employee("journey.test@muni.cz", "journey", "test", "password", Role.EMPLOYEE);
        Vehicle vehicle = new Vehicle("VRP", "Type", Year.of(1999), "EngineType", "VIN", (long) 7658.54);
        Journey journey = new Journey(new Date(), vehicle, employee);
        journey.returnVehicle(new Date(), (float)1000);
        JourneyDTO journeyDTO = beanMappingService.mapTo(journey, JourneyDTO.class);

        Assert.assertEquals(journey.getBorrowedAt(), journeyDTO.getBorrowedAt());
        Assert.assertEquals(journey.getReturnedAt(), journeyDTO.getReturnedAt());
        Assert.assertEquals(journey.getDistance(), journeyDTO.getDistance());
        Assert.assertEquals(journey.getEmployee().getEmail(), journeyDTO.getEmployee().getEmail());
        Assert.assertEquals(journey.getVehicle().getVrp(), journeyDTO.getVehicle().getVrp());
    }

    @Test
    public void testVehicleMapping() {
        Vehicle vehicle = new Vehicle("vehicleMapping", "Type", Year.of(1999), "EngineType", "VIN", (long) 7658.54);
        VehicleDTO vehicleDTO = beanMappingService.mapTo(vehicle, VehicleDTO.class);

        Assert.assertEquals(vehicle.getVrp(), vehicleDTO.getVrp());
        /*
            TODO change Year type filed to something else,
            dozer can't mapping this class, now is excluded in ServiceConfiguration

            Exception:
            org.dozer.MappingException: java.lang.NoSuchMethodException: java.time.Year.<init>()
         */
        //Assert.assertEquals(vehicle.getProductionYear(), vehicleDTO.getProductionYear());
        Assert.assertEquals(vehicle.getEngineType(), vehicleDTO.getEngineType());
        Assert.assertEquals(vehicle.getType(), vehicleDTO.getType());
        Assert.assertEquals(vehicle.getVin(), vehicleDTO.getVin());
        Assert.assertEquals(vehicle.getInitialKilometrage(), vehicleDTO.getInitialKilometrage());
    }

    @Test
    public void testVehicleCategoryMapping() {
        VehicleCategory vehicleCategory = new VehicleCategory("vehicle category mapping test");
        VehicleCategoryDTO vehicleCategoryDTO = beanMappingService.mapTo(vehicleCategory, VehicleCategoryDTO.class);

        Assert.assertEquals(vehicleCategory.getName(), vehicleCategoryDTO.getName());
    }
}
