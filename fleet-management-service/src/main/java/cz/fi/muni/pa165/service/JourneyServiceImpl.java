package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.Dao;
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
 * @author Martin Schmidt
 */
@Service
public class JourneyServiceImpl extends JpaService<Journey, Long> implements JourneyService {
    @Inject
    private JourneyDao journeyDao;

    @Override
    protected Dao<Journey, Long> getDao() {
        return journeyDao;
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
