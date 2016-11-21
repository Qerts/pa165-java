package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.Employee;
import org.springframework.stereotype.Service;
import cz.fi.muni.pa165.entity.Journey;

import java.util.Date;
import java.util.List;

/**
 * Created by Martin on 19.11.2016.
 */
@Service
public interface JourneyService {
    /**
     * find journey by id
     * @param id id of journey
     * @return  journey
     */
    public Journey findById(Long id);

    /**
     * find all journeys
     * @return list of journeys
     */
    public List<Journey> findAll();

    /**
     * save new journey
     * @param journey journey
     */
    public void create(Journey journey);

    /**
     * update saved journey
     * @param journey journey
     */
    public void update(Journey journey);

    /**
     * remove saved journey
     * @param journey journey
     */
    public void remove(Journey journey);

    /**
     * get all journeys for given employee in the time interval (start of the journey)
     * @param from start of time interval
     * @param to end of time interval
     * @param employee employee
     * @return list of journeys
     */
    public List<Journey> getAllJourneys(Date from, Date to, Employee employee);

}
