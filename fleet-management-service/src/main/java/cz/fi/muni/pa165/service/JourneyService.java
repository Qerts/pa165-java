package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.Journey;

import java.util.Date;
import java.util.List;

/**
 * @author Martin Schmidt
 */
public interface JourneyService {
    /**
     * find journey by id
     * @param id id of journey
     * @return  journey
     */
    Journey findById(Long id);

    /**
     * find all journeys
     * @return list of journeys
     */
    List<Journey> findAll();

    /**
     * save new journey
     * @param journey journey
     */
    void create(Journey journey);

    /**
     * update saved journey
     * @param journey journey
     */
    void update(Journey journey);

    /**
     * remove saved journey
     * @param journey journey
     */
    void remove(Journey journey);

    /**
     * get all journeys for given employee in the time interval (start of the journey)
     * @param from start of time interval
     * @param to end of time interval
     * @param employee employee
     * @return list of journeys
     */
    List<Journey> getAllJourneys(Date from, Date to, Employee employee);

}
