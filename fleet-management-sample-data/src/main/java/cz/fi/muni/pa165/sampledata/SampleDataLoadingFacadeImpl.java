package cz.fi.muni.pa165.sampledata;

import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.Journey;
import cz.fi.muni.pa165.entity.Vehicle;
import cz.fi.muni.pa165.entity.VehicleCategory;
import cz.fi.muni.pa165.enums.Role;
import cz.fi.muni.pa165.service.interfaces.EmployeeService;
import cz.fi.muni.pa165.service.interfaces.JourneyService;
import cz.fi.muni.pa165.service.interfaces.VehicleCategoryService;
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
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Inject
    private EmployeeService employeeService;

    @Inject
    private VehicleService vehicleService;

    @Inject
    private JourneyService journeyService;

    @Inject
    private VehicleCategoryService vehicleCategoryService;

    public void loadData() throws IOException {
        VehicleCategory categoryL = vehicleCategory("Category L: light motor vehicles");
        VehicleCategory categoryM = vehicleCategory("Category M: used for the carriage of passengers");
        VehicleCategory categoryN = vehicleCategory("Category N: used for the carriage of goods");

        Employee admin = employee("admin@muni.cz", "Admin", "Man", "admin", Role.ADMINISTRATOR);
        Employee serviceman = employee("serviceman@muni.cz", "Thomas", "Cooper", "password", Role.SERVICEMAN);
        Employee employee1 = employee("employee1@muni.cz", "John", "Doe", "password", Role.EMPLOYEE);
        Employee employee2 = employee("employee2@muni.cz", "Arden", "Dyer", "password", Role.EMPLOYEE);
        Employee employee3 = employee("employee3@muni.cz", "Willie", "Bowers", "password", Role.EMPLOYEE);
        Employee employee4 = employee("employee4@muni.cz", "Eddie", "Terrell", "password", Role.EMPLOYEE);

        addEmployeeVehicleCategory(admin, categoryL, categoryM, categoryN);
        addEmployeeVehicleCategory(serviceman, categoryL, categoryM, categoryN);
        addEmployeeVehicleCategory(employee1,categoryL);
        addEmployeeVehicleCategory(employee2, categoryL, categoryM);
        addEmployeeVehicleCategory(employee3, categoryL, categoryM, categoryN);
        addEmployeeVehicleCategory(employee4, categoryM);

        Vehicle vehicle1 = vehicle(categoryM, "4A23000", "Citroen DS3 1.6 VTi DStyle 3dr", 2011, "Petrol engine", "1FMCU0C73AKC53597", (long) 31570);
        Vehicle vehicle2 = vehicle(categoryM, "6B26635", "Fiat 500 1.2 Sport 3dr", 2010, "Petrol engine", "1B7GL12X52S609193", (long) 98000);
        Vehicle vehicle3 = vehicle(categoryM, "4C82878", "BMW 3 SERIES 3.0 335i M Sport 2dr", 2007, "Petrol engine", "1G8ZE1598PZ242153", (long) 89500);
        Vehicle vehicle4 = vehicle(categoryM, "2E77010", "Volkswagen Golf 2.0 TDI GT DSG 5dr", 2012, "Diesel engine", "1JCUX7811FT114873", (long) 33000);
        Vehicle vehicle5 = vehicle(categoryM, "2H42270", "Audi TT 1.8 T Sport Quattro 3dr", 2002, "Petrol engine", "WMEEJ9AA5DK782726", (long) 170000);
        //Vehicle vehicle6 = vehicle(categoryM, "3J57942", "Peugeot 3008 1.6 HDi Active 5dr", 2013, "Diesel engine", "JSAAK47A052170790", (long) 56900);
        //Vehicle vehicle7 = vehicle(categoryM, "2L02404", "Nissan Leaf E Acenta 5dr", 2014, "Electric engine", "5KKHAEDE47PY01338", (long) 16700);
        //Vehicle vehicle8 = vehicle(categoryM, "2M51384", "Nissan X-Trail 2.2 dCi SVE 5dr", 2005, "Diesel engine", "1HGCA5524KA102482", (long) 147200);
        //Vehicle vehicle9 = vehicle(categoryM, "4P63285", "Renault Megane 1.5 dCi Expression 4dr", 2007, "Diesel engine", "1GNES13M582139763", (long) 231700);
        //Vehicle vehicle10 = vehicle(categoryM, "7S59790", "Alfa Romeo GT 1.9 JTDM 16v 2dr", 2005, "Diesel engine", "1FTPW14525KD01647", (long) 261900);

        Vehicle truck1 = vehicle(categoryN, "3A24987", "Mitsubishi FUSO FE 180", 2012, "Diesel engine", "2B3KA43D89H525247", (long) 221400);
        Vehicle truck2 = vehicle(categoryN, "1A97103", "Mitsubishi FUSO FE 160", 2012, "Diesel engine", "4S3BMEH68B2260267", (long) 356200);
        Vehicle truck3 = vehicle(categoryN, "1A29434", "Isuzu NPR HD", 2008, "Diesel engine", "1GC1KVE80CF291272", (long) 481800);
        Vehicle truck4 = vehicle(categoryN, "3A24987", "Ford E-450", 2006, "Diesel engine", "3GNGK26U4YG121594", (long) 297900);
        Vehicle truck5 = vehicle(categoryN, "2A31734", "Freightliner CASCADIA", 2009, "Diesel engine", "1FTJE34F6SHA08131", (long) 187100);

        Vehicle bike1 = vehicle(categoryL, "2A79905", "BMW R1200GS ADVENTURE TE 1170cc", 2016, "Petrol engine", "3VWMZ81K89M306845", (long) 7865);
        Vehicle bike2 = vehicle(categoryL, "2A34511", "Harley-Davidson SPORTSTER XL1200X Forty Eight 1200cc", 2013, "Petrol engine", "2GNFLEE54C6330164", (long) 2340);
        Vehicle bike3 = vehicle(categoryL, "2A44304", "Honda CB500 500cc", 2002, "Petrol engine", "WAUNF78P27A123055", (long) 35408);
        Vehicle bike4 = vehicle(categoryL, "2A39700", "Pioneer XF 124cc", 1997, "Petrol engine", "WVWYK73C18E115975", (long) 14148);
        Vehicle bike5 = vehicle(categoryL, "2A91101", "Honda CB1 398cc", 1989, "Petrol engine", "1FMCU0DG0AKB22433", (long) 77500);

        //vehicle journeys
        journey(2016, 1, 1, 2016, 1, 6, 1200, admin, vehicle1);
        journey(2016, 1, 15, 2016, 1, 23, 2304, serviceman, vehicle1);
        journey(2016, 1, 15, 2016, 1, 21, 1900, employee2, vehicle2);
        journey(2016, 1, 24, 2016, 1, 28, 1220, employee3, vehicle2);
        journey(2016, 1, 22, 2016, 1, 27, 1770, employee2, vehicle3);
        journey(2016, 1, 28, 2016, 2, 5, 3128, employee4, vehicle3);
        journey(2016, 1, 29, 2016, 2, 4, 870, employee2, vehicle4);
        journey(2016, 2, 7, 2016, 2, 13, 1320, employee3, vehicle4);
        journey(2016, 2, 7, 2016, 2, 8, 1528, employee4, vehicle5);
        journey(2016, 2, 15, 2016, 2, 21, 1128, employee3, vehicle5);

        //truck journeys
        journey(2016, 2, 28, 2016, 3, 3, 2820, employee3, truck1);
        journey(2016, 3, 4, 2016, 3, 7, 1729, employee3, truck1);
        journey(2016, 3, 9, 2016, 3, 11, 720, employee3, truck2);
        journey(2016, 3, 12, 2016, 3, 14, 1112, serviceman, truck2);
        journey(2016, 3, 13, 2016, 3, 20, 3423, employee3, truck3);
        journey(2016, 3, 23, 2016, 3, 27, 1128, employee3, truck3);
        journey(2016, 2, 12, 2016, 2, 20, 2243, serviceman, truck4);
        journey(2016, 3, 4, 2016, 3, 9, 4231, serviceman, truck4);
        journey(2016, 4, 1, 2016, 4, 3, 451, employee3, truck5);
        journey(2016, 4, 7, 2016, 4, 11, 1145, employee3, truck5);

        //bikes journeys
        journey(2016, 1, 4, 2016, 1, 8, 723, employee1, bike1);
        journey(2016, 1, 11, 2016, 1, 17, 1345, employee1, bike1);
        journey(2016, 1, 21, 2016, 1, 24, 453, employee1, bike2);
        journey(2016, 1, 27, 2016, 2, 1, 928, employee1, bike2);
        journey(2016, 2, 1, 2016, 2, 7, 2341, admin, bike3);
        journey(2016, 4, 6, 2016, 4, 13, 1728, admin, bike3);
        journey(2016, 2, 13, 2016, 2, 18, 1911, employee1, bike4);
        journey(2016, 2, 22, 2016, 2, 26, 1127, employee1, bike4);
        journey(2016, 3, 2, 2016, 3, 7, 1400, employee1, bike5);
        journey(2016, 3, 11, 2016, 3, 16, 1722, employee1, bike5);


    }

    private void addEmployeeVehicleCategory(Employee employee,VehicleCategory... vehicleCategorys) {
        for (VehicleCategory vehicleCategory : vehicleCategorys) {
            employee.addVehicleCategory(vehicleCategory);
        }
        employeeService.update(employee);
    }

    private VehicleCategory vehicleCategory(String name) {
        VehicleCategory vc = new VehicleCategory(name);
        vehicleCategoryService.create(vc);
        return vc;
    }

    private Journey journey(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay, float distance, Employee employee, Vehicle vehicle) {
        Date startDate = new GregorianCalendar(startYear, startMonth, startDay).getTime();
        Date endDate = new GregorianCalendar(endYear, endMonth, endDay).getTime();
        Journey j = journeyService.beginJourney(vehicle.getId(), employee.getId(), startDate);
        journeyService.finishJourney(j.getId(), distance, endDate);
        return j;
    }

    private Vehicle vehicle(VehicleCategory vehicleCategory, String vrp, String type, int year, String engineType, String vin, long initialKilometrage) {
        Vehicle v = new Vehicle(vrp, type, year, engineType, vin, initialKilometrage);
        v.setVehicleCategory(vehicleCategory);
        vehicleService.create(v);
        return v;
    }

    private Employee employee(String email, String name, String surname, String pass, Role role) {
        Employee e = new Employee(email, name, surname, pass, role);
        employeeService.registerEmployee(e, pass);
        return e;
    }


}
