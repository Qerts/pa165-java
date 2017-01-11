package cz.fi.muni.pa165.sampledata;

import cz.fi.muni.pa165.entity.*;
import cz.fi.muni.pa165.enums.Role;
import cz.fi.muni.pa165.service.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.IOException;
import java.util.*;

/**
 * @author Jozef Krcho
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Inject
    private EmployeeService employeeService;

    @Inject
    private VehicleService vehicleService;

    @Inject
    private JourneyService journeyService;

    @Inject
    private VehicleCategoryService vehicleCategoryService;

    @Inject
    private InspectionIntervalService inspectionIntervalService;

    @Inject
    private InspectionService inspectionService;

    public void loadData() throws IOException {
        VehicleCategory categoryL = vehicleCategory("Category L: light motor vehicles");
        VehicleCategory categoryM = vehicleCategory("Category M: used for the carriage of passengers");
        VehicleCategory categoryN = vehicleCategory("Category N: used for the carriage of goods");
        log.info("Loaded vehicle categories.");

        Employee admin = employee("admin@muni.cz", "Admin", "Man", "admin", Role.ADMINISTRATOR);
        Employee serviceman = employee("serviceman@muni.cz", "Thomas", "Cooper", "password", Role.SERVICEMAN);
        Employee employee1 = employee("employee1@muni.cz", "John", "Doe", "password", Role.EMPLOYEE);
        Employee employee2 = employee("employee2@muni.cz", "Arden", "Dyer", "password", Role.EMPLOYEE);
        Employee employee3 = employee("employee3@muni.cz", "Willie", "Bowers", "password", Role.EMPLOYEE);
        Employee employee4 = employee("employee4@muni.cz", "Eddie", "Terrell", "password", Role.EMPLOYEE);

        addEmployeeVehicleCategory(admin, categoryL, categoryM, categoryN);
        addEmployeeVehicleCategory(serviceman, categoryL, categoryM, categoryN);
        addEmployeeVehicleCategory(employee1, categoryL);
        addEmployeeVehicleCategory(employee2, categoryL, categoryM);
        addEmployeeVehicleCategory(employee3, categoryL, categoryM, categoryN);
        addEmployeeVehicleCategory(employee4, categoryM);
        log.info("Loaded employees.");

        Vehicle citroenVehicle = vehicle(categoryM, "4A23000", "Citroen DS3 1.6 VTi DStyle 3dr", 2011, "Petrol engine", "1FMCU0C73AKC53597", (long) 31570);
        Vehicle fiatVehicle = vehicle(categoryM, "6B26635", "Fiat 500 1.2 Sport 3dr", 2010, "Petrol engine", "1B7GL12X52S609193", (long) 98000);
        Vehicle vehicle3 = vehicle(categoryM, "4C82878", "BMW 3 SERIES 3.0 335i M Sport 2dr", 2007, "Petrol engine", "1G8ZE1598PZ242153", (long) 89500);
        Vehicle vehicle4 = vehicle(categoryM, "2E77010", "Volkswagen Golf 2.0 TDI GT DSG 5dr", 2012, "Diesel engine", "1JCUX7811FT114873", (long) 33000);
        Vehicle vehicle5 = vehicle(categoryM, "2H42270", "Audi TT 1.8 T Sport Quattro 3dr", 2002, "Petrol engine", "WMEEJ9AA5DK782726", (long) 170000);

        Vehicle truck1 = vehicle(categoryN, "3A24987", "Mitsubishi FUSO FE 180", 2012, "Diesel engine", "2B3KA43D89H525247", (long) 221400);
        Vehicle truck2 = vehicle(categoryN, "1A97103", "Mitsubishi FUSO FE 160", 2012, "Diesel engine", "4S3BMEH68B2260267", (long) 356200);
        Vehicle truck3 = vehicle(categoryN, "1A29434", "Isuzu NPR HD", 2008, "Diesel engine", "1GC1KVE80CF291272", (long) 481800);
        Vehicle truck4 = vehicle(categoryN, "3A21245", "Ford E-450", 2006, "Diesel engine", "3GNGK26U4YG121594", (long) 297900);
        Vehicle truck5 = vehicle(categoryN, "2A31734", "Freightliner CASCADIA", 2009, "Diesel engine", "1FTJE34F6SHA08131", (long) 187100);

        Vehicle bike1 = vehicle(categoryL, "2A79905", "BMW R1200GS ADVENTURE TE 1170cc", 2016, "Petrol engine", "3VWMZ81K89M306845", (long) 7865);
        Vehicle bike2 = vehicle(categoryL, "2A34511", "Harley-Davidson SPORTSTER XL1200X Forty Eight 1200cc", 2013, "Petrol engine", "2GNFLEE54C6330164", (long) 2340);
        Vehicle bike3 = vehicle(categoryL, "2A44304", "Honda CB500 500cc", 2002, "Petrol engine", "WAUNF78P27A123055", (long) 35408);
        Vehicle bike4 = vehicle(categoryL, "2A39700", "Pioneer XF 124cc", 1997, "Petrol engine", "WVWYK73C18E115975", (long) 14148);
        Vehicle bike5 = vehicle(categoryL, "2A91101", "Honda CB1 398cc", 1989, "Petrol engine", "1FMCU0DG0AKB22433", (long) 77500);
        log.info("Loaded vehicles.");

        //inspection intervals
        InspectionInterval citroenTiresHalfYearInspection = inspectionInterval(citroenVehicle, "Tires (Citroen)", 180); // 1/2 year
        InspectionInterval citroenOil1YearInspection = inspectionInterval(citroenVehicle, "Oil (Citroen)", 365); // 1 year
        // TODO: test that 1 inspection interval cannot be added to multiple vehicles
        InspectionInterval fiatTires3monthsInspection = inspectionInterval(fiatVehicle, "Tires (Fiat)", 60); // 3 months
        InspectionInterval fiatOil1YearInspection = inspectionInterval(fiatVehicle, "Oil (Fiat)", 365); // 1 year
        InspectionInterval fiatSTK2YearsInspection = inspectionInterval(fiatVehicle, "STK (Fiat)", 730); // 2 years

        citroenVehicle.setInspectionIntervals(new HashSet<>(Arrays.asList(citroenTiresHalfYearInspection, citroenOil1YearInspection)));
        fiatVehicle.setInspectionIntervals(new HashSet<>(Arrays.asList(fiatTires3monthsInspection, fiatOil1YearInspection, fiatSTK2YearsInspection)));

        //inspections
        Inspection citroenTiresInspected = inspection(citroenTiresHalfYearInspection, todayBuilder("-170d").getTime()); // needs to be performed in 10 days
        Inspection fiatTiresInspected = inspection(fiatTires3monthsInspection, todayBuilder("-1m").getTime()); // needs to be performed in 2 months
        Inspection fiatOilInspected = inspection(fiatOil1YearInspection, todayBuilder("-9m").getTime()); // needs to be performed in 3 months
        Inspection fiatSTKInspected = inspection(fiatSTK2YearsInspection, todayBuilder("-3y").getTime()); // overdue by 1 year
        log.info("Loaded inspections");

        //vehicle journeys
        journey(today(":y"), today(":m"), today(":d"), today("+5d:y"), today("+5d:m"), today("+5d:d"), 1200, admin, citroenVehicle);
        journey(today("+14d:y"), today("+14d:m"), today("+14d:d"), today("+22d:y"), today("+22d:m"), today("+22d:d"), 2304, serviceman, citroenVehicle);
        journey(today("+14d:y"), today("+14d:m"), today("+14d:d"), today("+20d:y"), today("+20d:m"), today("+20d:d"), 1900, employee2, fiatVehicle);
        journey(today("+23d:y"), today("+23d:m"), today("+23d:d"), today("+27d:y"), today("+27d:m"), today("+27d:d"), 1220, employee3, fiatVehicle);
        journey(today("+21d:y"), today("+21d:m"), today("+21d:d"), today("+26d:y"), today("+26d:m"), today("+26d:d"), 1770, employee2, vehicle3);
        journey(today("+27d:y"), today("+27d:m"), today("+27d:d"), today("+1m+4d:y"), today("+1m+4d:m"), today("+1m+4d:d"), 3128, employee4, vehicle3);
        journey(today("+28d:y"), today("+28d:m"), today("+28d:d"), today("+1m+3d:y"), today("+1m+3d:m"), today("+1m+3d:d"), 870, employee2, vehicle4);
        journey(today("+1m+6d:y"), today("+1m+6d:m"), today("+1m+6d:d"), today("+1m+12d:y"), today("+1m+12d:m"), today("+1m+12d:d"), 1320, employee3, vehicle4);
        journey(today("+1m+14d:y"), today("+1m+14d:m"), today("+1m+14d:d"), today("+1m+20d:y"), today("+1m+20d:m"), today("+1m+20d:d"), 1128, employee3, vehicle5);
        journey(today("+1m+6d:y"), today("+1m+6d:m"), today("+1m+6d:d"), today("+1m+7d:y"), today("+1m+7d:m"), today("+1m+7d:d"), 1528, employee4, vehicle5);

        //unfinished (active) journeys
        journeyUnfinished(today(":y"), today(":m"), today(":d"), employee2, fiatVehicle);
        journeyUnfinished(today("-1d:y"), today("-1d:m"), today("-1d:d"), employee3, citroenVehicle);
        journeyUnfinished(today("-7d:y"), today("-7d:m"), today("-7d:d"), admin, vehicle3);
        journeyUnfinished(today(":y"), today(":m"), today(":d"), admin, vehicle4);

        //truck journeys
        journey(today("+1m+27d:y"), today("+1m+27d:m"), today("+1m+27d:d"), today("+2m+3d:y"), today("+2m+3d:m"), today("+2m+3d:d"), 2820, employee3, truck1);
        journey(today("+2m+3d:y"), today("+2m+3d:m"), today("+2m+3d:d"), today("+2m+6d:y"), today("+2m+6d:m"), today("+2m+6d:d"), 1729, employee3, truck1);
        journey(today("+2m+8d:y"), today("+2m+8d:m"), today("+2m+8d:d"), today("+2m+10d:y"), today("+2m+10d:m"), today("+2m+10d:d"), 720, employee3, truck2);
        journey(today("+2m+11d:y"), today("+2m+11d:m"), today("+2m+11d:d"), today("+2m+11d:y"), today("+2m+11d:m"), today("+2m+11d:d"), 1112, serviceman, truck2);
        journey(today("+2m+12d:y"), today("+2m+12d:m"), today("+2m+12d:d"), today("+2m+19d:y"), today("+2m+19d:m"), today("+2m+19d:d"), 3423, employee3, truck3);
        journey(today("+2m+22d:y"), today("+2m+22d:m"), today("+2m+22d:d"), today("+2m+26d:y"), today("+2m+26d:m"), today("+2m+26d:d"), 1128, employee3, truck3);
        journey(today("+1m+11d:y"), today("+1m+11d:m"), today("+1m+11d:d"), today("+1m+19d:y"), today("+1m+19d:m"), today("+1m+19d:d"), 2243, serviceman, truck4);
        journey(today("+2m+3d:y"), today("+2m+3d:m"), today("+2m+3d:d"), today("+2m+8d:y"), today("+2m+8d:m"), today("+2m+8d:d"), 4231, serviceman, truck4);
        journey(today("+3m:y"), today("+3m:m"), today("+3m:d"), today("+3m+2d:y"), today("+3m+2d:m"), today("+3m+2d:d"), 451, employee3, truck5);
        journey(today("+3m+6d:y"), today("+3m+6d:m"), today("+3m+6d:d"), today("+3m+10d:y"), today("+3m+10d:m"), today("+3m+10d:d"), 1145, employee3, truck5);

        //bikes journeys
        journey(today("+4d:y"), today("+4d:m"), today("+4d:d"), today("+7d:y"), today("+7d:m"), today("+7d:d"), 723, employee1, bike1);
        journey(today("+11d:y"), today("+11d:m"), today("+11d:d"), today("+16d:y"), today("+16d:m"), today("+16d:d"), 1345, employee1, bike1);
        journey(today("+20d:y"), today("+20d:m"), today("+20d:d"), today("+23d:y"), today("+23d:m"), today("+23d:d"), 453, employee1, bike2);
        journey(today("+26d:y"), today("+26d:m"), today("+26d:d"), today("+1m:y"), today("+1m:m"), today("+1m:d"), 928, employee1, bike2);
        journey(today("+1m:y"), today("+1m:m"), today("+1m:d"), today("+1d+6d:y"), today("+1d+6d:m"), today("+1d+6d:d"), 2341, admin, bike3);
        journey(today("+3m+5d:y"), today("+3m+5d:m"), today("+3m+5d:d"), today("+3m+12d:y"), today("+3m+12d:m"), today("+3m+12d:d"), 1728, admin, bike3);
        journey(today("+1m+12d:y"), today("+1m+12d:m"), today("+1m+12d:d"), today("+1m+17d:y"), today("+1m+17d:m"), today("+1m+17d:d"), 1911, employee1, bike4);
        journey(today("+1m+21d:y"), today("+1m+21d:m"), today("+1m+21d:d"), today("+1m+25d:y"), today("+1m+25d:m"), today("+1m+25d:d"), 1127, employee1, bike4);
        journey(today("+2m+1d:y"), today("+2m+1d:m"), today("+2m+1d:d"), today("+2m+6d:y"), today("+2m+6d:m"), today("+2m+6d:d"), 1400, employee1, bike5);
        journey(today("+2m+10d:y"), today("+2m+10d:m"), today("+2m+10d:d"), today("+2m+15d:y"), today("+2m+15d:m"), today("+2m+15d:d"), 1722, employee1, bike5);
        log.info("Loaded journeys.");
    }

    private void addEmployeeVehicleCategory(Employee employee, VehicleCategory... vehicleCategories) {
        for (VehicleCategory vehicleCategory : vehicleCategories) {
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

    private Journey journeyUnfinished(int startYear, int startMonth, int startDay, Employee employee, Vehicle vehicle) {
        Date startDate = new GregorianCalendar(startYear, startMonth, startDay).getTime();
        return journeyService.beginJourney(vehicle.getId(), employee.getId(), startDate);
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

    private InspectionInterval inspectionInterval(Vehicle vehicle, String name, int interval) {
        InspectionInterval ii = new InspectionInterval(name, interval);
        ii.setVehicle(vehicle);
        inspectionIntervalService.create(ii);
        return ii;
    }

    private Inspection inspection(InspectionInterval ii, Date performedAt) {
        Inspection i = new Inspection(performedAt);
        i.setInspectionInterval(ii);

        Set<Inspection> inspections = ii.getInspections();
        inspections.add(i);
        ii.setInspections(inspections);
        inspectionService.create(i);
        inspectionIntervalService.update(ii);
        return i;
    }


    //
    // Helper methods
    //


    /**
     * Returns a modified current date.
     *
     * @param code E.g. "+1y+3m-15d" means: to current day add 1 year, add 3 months, subtract 15 days and return year.
     * @return Calendar
     */
    private Calendar todayBuilder(String code) {
        Calendar today = Calendar.getInstance();

        int state = 0; // States: 0 - default, 1 - change date
        int sign = 0;
        int amount = 0;
        int multiply = 1;
        int i = 0;

        for (char ch : code.toCharArray()) {
            switch (state) {
                // state default
                case 0:
                    switch (ch) {
                        case '+':
                            state = 1;
                            multiply = 1;
                            sign = 1;
                            break;

                        case '-':
                            state = 1;
                            multiply = 1;
                            sign = -1;
                            break;

                        default:
                            throw new RuntimeException("Invalid input, unexpected: '" + ch + "' at position " + i);
                    }
                    break;

                // state change date
                case 1:
                    if (ch >= '0' && ch <= '9') {
                        amount = Character.getNumericValue(ch) * multiply;
                        multiply *= 10;
                    } else {
                        switch (ch) {
                            case 'd':
                                today.add(Calendar.DAY_OF_MONTH, sign * amount);
                                state = 0;
                                break;

                            case 'm':
                                today.add(Calendar.MONTH, sign * amount);
                                state = 0;
                                break;

                            case 'y':
                                today.add(Calendar.YEAR, sign * amount);
                                state = 0;
                                break;

                            default:
                                throw new RuntimeException("Invalid input, unexpected: '" + ch + "' at position " + i);
                        }
                    }
                    break;

                // return modified date
                case 2:
                    return today;
            }

            i++;
        }

        return today;
    }


    /**
     * Returns year, month or date of modified current date.
     *
     * @param code E.g. "+1y+3m-15d:y" means: to current day add 1 year, add 3 months, subtract 15 days and return year
     *             as integer.
     * @return int
     */
    private int today(String code) {
        String[] codeParts = code.split(":");
        if (codeParts.length != 2) {
            throw new RuntimeException("Invalid code: '" + code + "'");
        }

        Calendar today = todayBuilder(codeParts[0]);

        for (char ch : codeParts[1].toCharArray()) {
            switch (ch) {
                case 'd':
                    return today.get(Calendar.DAY_OF_MONTH);

                case 'm':
                    return today.get(Calendar.MONTH);

                case 'y':
                    return today.get(Calendar.YEAR);

                default:
                    throw new RuntimeException("Invalid input, unexpected: '" + ch + "'");
            }
        }

        throw new RuntimeException("Invalid input, unexpected EOL.");
    }
}
