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
        Employee admin = employee("admin@muni.cz", "Admin", "Foo", "admin", Role.ADMINISTRATOR);

        Vehicle vehicle1 = vehicle("4A23000", "Citroen DS3 1.6 VTi DStyle 3dr", 2011, "Petrol engine", "1FMCU0C73AKC53597", (long) 31570);
        Vehicle vehicle2 = vehicle("6B26635", "Fiat 500 1.2 Sport 3dr", 2010, "Petrol engine", "1B7GL12X52S609193", (long) 98000);
        Vehicle vehicle3 = vehicle("4C82878", "BMW 3 SERIES 3.0 335i M Sport 2dr", 2007, "Petrol engine", "1G8ZE1598PZ242153", (long) 89500);
        Vehicle vehicle4 = vehicle("2E77010", "Volkswagen Golf 2.0 TDI GT DSG 5dr", 2012, "Diesel engine", "1JCUX7811FT114873", (long) 33000);
        Vehicle vehicle5 = vehicle("2H42270", "Audi TT 1.8 T Sport Quattro 3dr", 2002, "Petrol engine", "WMEEJ9AA5DK782726", (long) 170000);
        Vehicle vehicle6 = vehicle("3J57942", "Peugeot 3008 1.6 HDi Active 5dr", 2013, "Diesel engine", "JSAAK47A052170790", (long) 56900);
        Vehicle vehicle7 = vehicle("2L02404", "Nissan Leaf E Acenta 5dr", 2014, "Electric engine", "5KKHAEDE47PY01338", (long) 16700);
        Vehicle vehicle8 = vehicle("2M51384", "Nissan X-Trail 2.2 dCi SVE 5dr", 2005, "Diesel engine", "1HGCA5524KA102482", (long) 147200);
        Vehicle vehicle9 = vehicle("4P63285", "Renault Megane 1.5 dCi Expression 4dr", 2007, "Diesel engine", "1GNES13M582139763", (long) 231700);
        Vehicle vehicle10 = vehicle("7S59790", "Alfa Romeo GT 1.9 JTDM 16v 2dr", 2005, "Diesel engine", "1FTPW14525KD01647", (long) 261900);

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
