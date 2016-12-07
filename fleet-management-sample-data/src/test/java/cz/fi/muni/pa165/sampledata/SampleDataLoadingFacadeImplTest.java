package cz.fi.muni.pa165.sampledata;

import cz.fi.muni.pa165.service.interfaces.EmployeeService;
import cz.fi.muni.pa165.service.interfaces.JourneyService;
import cz.fi.muni.pa165.service.interfaces.VehicleCategoryService;
import cz.fi.muni.pa165.service.interfaces.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.io.IOException;

/**
 * @author Jozef Krcho
 */
@ContextConfiguration(classes = {FleetManagementWithSampleDataConfig.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SampleDataLoadingFacadeImplTest extends AbstractTestNGSpringContextTests{

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImplTest.class);

    @Inject
    private EmployeeService employeeService;

    @Inject
    private VehicleService vehicleService;

    @Inject
    private VehicleCategoryService vehicleCategoryService;

    @Inject
    private JourneyService journeyService;

    @Test
    public void testSampleData() throws IOException {
        log.debug("testing sample data");

        Assert.assertTrue(employeeService.findAll().size() > 0, "No employees found.");
        Assert.assertTrue(vehicleService.findAll().size() > 0, "No vehicles found.");
        Assert.assertTrue(vehicleCategoryService.findAll().size() > 0, "No vehicle categorys found.");
        Assert.assertTrue(journeyService.findAll().size() > 0, "No journeys found.");
    }
}
