package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.EmployeeDao;
import cz.fi.muni.pa165.dao.interfaces.JourneyDao;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.Journey;
import cz.fi.muni.pa165.exceptions.FleetManagementDAException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Martin on 19.11.2016.
 */
@Service
public class JourneyServiceImpl implements JourneyService {
    @Inject
    private JourneyDao journeyDao;

    public Journey findById(Long id) {
        try {
            return journeyDao.findById(id);
        } catch (RuntimeException ex) {
            throw new FleetManagementDAException("find journey by id error", ex);
        }
    }

    public List<Journey> findAll() {
        try {
            return journeyDao.findAll();
        } catch(RuntimeException ex) {
            throw new FleetManagementDAException("find all journey error", ex);
        }
    }

    public void create(Journey j) {
        if (j == null) {
            throw new FleetManagementDAException("journey cannot be null");
        }
        try {
            journeyDao.persist(j);
        } catch(RuntimeException ex) {
            throw new FleetManagementDAException("journey create error", ex);
        }
    }

    public void update(Journey j) {
        if (j == null) {
            throw new FleetManagementDAException("journey cannot be null");
        }
        try {
            journeyDao.merge(j);
        } catch(RuntimeException ex) {
            throw new FleetManagementDAException("journey update error", ex);
        }
    }

    public void remove(Journey j) {
        if (j == null) {
            throw new FleetManagementDAException("journey cannot be null");
        }
        try {
            journeyDao.remove(j);
        } catch(RuntimeException ex) {
            throw new FleetManagementDAException("journey delete error", ex);
        }
    }

    public List<Journey> getAllJourneys(Date from, Date to, Employee employee) {
        List<Journey> journeys;

        try {
            journeys = journeyDao.findByEmployee(employee);
        } catch(RuntimeException ex) {
            throw new FleetManagementDAException("employee error", ex);
        }

        List<Journey> journeysDate = new ArrayList<>();
        for (Journey j : journeys) {
            if (j.getBorrowedAt().compareTo(to) <=0 && j.getBorrowedAt().compareTo(from) >= 0) {
                journeysDate.add(j);
            }
        }
        return journeysDate;
    }
    
}
