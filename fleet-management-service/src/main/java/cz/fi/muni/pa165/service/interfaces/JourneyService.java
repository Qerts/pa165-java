package cz.fi.muni.pa165.service.interfaces;

import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.Journey;

import java.util.Date;
import java.util.List;

/**
 * @author Martin Schmidt
 */
public interface JourneyService extends Service<Journey, Long> {
    /**
     * get all journeys for given employee in the time interval (start of the journey)
     *
     * @param from     start of time interval
     * @param to       end of time interval
     * @param employee employee
     * @return list of journeys
     */
    List<Journey> getAllJourneys(Date from, Date to, Employee employee);

}
