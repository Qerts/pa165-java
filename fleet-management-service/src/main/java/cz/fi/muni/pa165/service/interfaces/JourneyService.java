package cz.fi.muni.pa165.service.interfaces;

import cz.fi.muni.pa165.entity.Journey;

import java.util.Date;
import java.util.List;

/**
 * @author Martin Schmidt
 */
public interface JourneyService extends Service<Journey, Long> {

    /**
     * get journeys for given employee
     * @param employeeId employee id
     * @return list of journeys
     */
    List<Journey> getJourneysByEmployee(Long employeeId);

    /**
     * get all journeys for given employee in the time interval (start of the journey)
     *
     * @param from     start of time interval
     * @param to       end of time interval
     * @param employeeId employee id
     * @return list of journeys
     */
    List<Journey> getAllJourneys(Date from, Date to, Long employeeId);

    /**
     * create new journey, begin journey
     * @param vehicleId vehicle id for new journey
     * @param employeeId employee id
     * @param startDate star date of journey
     */
    void beginJourney(Long vehicleId, Long employeeId, Date startDate);

    /**
     * final update of journey
     * @param journeyId journey id
     * @param drivenDistance total distance for journey
     * @param endDate date of journey ending
     */
    void finishJourney(Long journeyId, Float drivenDistance, Date endDate);

}
