package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.JourneyDTO;

import java.util.Date;
import java.util.List;

/**
 * @author Martin Schmidt
 */
public interface JourneyFacade {
    /**
     * get list of all journeys
     *
     * @return list of journeys
     */
    List<JourneyDTO> getAllJourneys();

    /**
     * get all Journeys for given employee
     *
     * @param employeeId employee id
     * @return list of journeys
     */
    List<JourneyDTO> getJourneysByEmployee(Long employeeId);

    /**
     * get journeys for given employee started in given interval
     *
     * @param from       start date of interval
     * @param to         end of interval
     * @param employeeId employee id
     * @return list of journeys
     */
    List<JourneyDTO> getJourneys(Date from, Date to, Long employeeId);


    /**
     * add existing journey
     *
     * @param journey
     */
    void addJourney(JourneyDTO journey);

    /**
     * creates new journey
     *
     * @param vehicleId  vehicle id for new journey
     * @param employeeId employee id who goes on journey
     * @param startDate  start date of journey
     */
    JourneyDTO beginJourney(Long vehicleId, Long employeeId, Date startDate);

    /**
     * end journey
     *
     * @param journeyId      journey id to end
     * @param drivenDistance total driven distance on journey
     * @param endDate        end date of journey
     */
    void finishJourney(Long journeyId, Float drivenDistance, Date endDate);

    /**
     * get list of unfinished journeys
     *
     * @return list of journeys
     */
    List<JourneyDTO> getUnfinishedJourneys();
}
