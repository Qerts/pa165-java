package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.Dao;
import cz.fi.muni.pa165.dao.interfaces.EmployeeDao;
import cz.fi.muni.pa165.dao.interfaces.JourneyDao;
import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.Journey;
import cz.fi.muni.pa165.entity.Vehicle;
import cz.fi.muni.pa165.exceptions.FleetManagementDAException;
import cz.fi.muni.pa165.service.interfaces.JourneyService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Martin Schmidt
 */
@Service
public class JourneyServiceImpl extends JpaService<Journey, Long> implements JourneyService {
    @Inject
    private JourneyDao journeyDao;

    @Inject
    private EmployeeDao employeeDao;

    @Inject
    private VehicleDao vehicleDao;

    @Override
    protected Dao<Journey, Long> getDao() {
        return journeyDao;
    }

    public List<Journey> getJourneysByEmployee(Long employeeId) {
        Employee employee = employeeDao.findById(employeeId);
        return journeyDao.findByEmployee(employee);
    }

    public List<Journey> getAllJourneys(Date from, Date to, Long employeeId) {
        Employee employee = employeeDao.findById(employeeId);
        List<Journey> journeys;

        try {
            journeys = journeyDao.findByEmployee(employee);
        } catch (RuntimeException ex) {
            throw new FleetManagementDAException("employee error", ex);
        }

        List<Journey> journeysDate = new ArrayList<>();
        for (Journey j : journeys) {
            if (j.getBorrowedAt().compareTo(to) <= 0 && j.getBorrowedAt().compareTo(from) >= 0) {
                journeysDate.add(j);
            }
        }
        return journeysDate;
    }

    public void startJourney(Long vehicleId, Long employeeId, Date startDate) {
        Employee employee = employeeDao.findById(employeeId);
        Vehicle vehicle = vehicleDao.findById(vehicleId);
        Journey jouney = new Journey(startDate, vehicle, employee);
        journeyDao.persist(jouney);
    }

    @Override
    public void finishJourney(Long journeyId, Float drivenDistance, Date endDate) {
        Journey journey = journeyDao.findById(journeyId);
        journey.setDistance(drivenDistance);
        journey.setReturnedAt(endDate);
        journeyDao.merge(journey);
    }

}
