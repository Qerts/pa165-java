package cz.fi.muni.pa165.sampledata;

import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.Journey;
import cz.fi.muni.pa165.entity.Vehicle;
import cz.fi.muni.pa165.enums.Role;
import cz.fi.muni.pa165.service.interfaces.EmployeeService;
import cz.fi.muni.pa165.service.interfaces.JourneyService;
import cz.fi.muni.pa165.service.interfaces.VehicleService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Jozef Krcho
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements  SampleDataLoadingFacade{

    @Inject
    private EmployeeService employeeService;

    @Inject
    private VehicleService vehicleService;

    @Inject
    private JourneyService journeyService;

    public void loadData() throws IOException {
        //TODO add all startup data
        Employee admin = employee("admin@muni.cz", "Admin", "Foo", "admin", Role.ADMINISTRATOR);

        Vehicle vehicle1 = vehicle("VRP", "Type", 1999, "EngineType", "VIN", (long) 7658.54);

        Journey jourmey1 = journey(new GregorianCalendar(2016,1,1).getTime(),
                new GregorianCalendar(2016,1,6).getTime(), 1200, admin, vehicle1);

    }

    private Journey journey(Date startDate, Date endDate, float distance, Employee employee, Vehicle vehicle) {
        Journey j = journeyService.beginJourney(vehicle.getId(), employee.getId(), startDate);
        journeyService.finishJourney(j.getId(), distance, endDate);
        return j;
    }

    private Vehicle vehicle(String vrp, String type, int year, String engineType, String vin, long initialKilometrage) {
        Vehicle v = new Vehicle(vrp, type, year, engineType, vin, initialKilometrage);
        vehicleService.create(v);
        return v;
    }

    private Employee employee(String email, String name, String surname, String pass, Role role) {
        Employee e = new Employee(email, name, surname, pass, role);
        employeeService.registerEmployee(e, pass);
        return e;
    }



}
