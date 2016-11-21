package cz.fi.muni.pa165.service;

import org.springframework.stereotype.Service;
import cz.fi.muni.pa165.entity.Vehicle;

import java.util.List;

/**
 * Created by Martin on 13.11.2016.
 */
@Service
public interface VehicleService {

    /**
     * find vehicle by id
     * @param id id
     * @return vehicle
     */
    public Vehicle findById(Long id);

    /**
     * find all vehicles
     * @return list of vehicles
     */
    public List<Vehicle> findAll();

    /**
     * save new vehicle
     * @param vehicle vehicle
     */
    public void create(Vehicle vehicle);

    /**
     * update saved vehicle
     * @param vehicle vehicle
     */
    public void update(Vehicle vehicle);

    /**
     * remove saved vehicle
     * @param vehicle vehicle
     */
    public void remove(Vehicle vehicle);

}
