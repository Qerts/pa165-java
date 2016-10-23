package dao;

import entity.Vehicle;
import entity.VehicleCategory;

import java.util.List;

/**
 * Created by Martin on 23.10.2016.
 * @author Martin Schmidt
 */
public interface VehicleDao {

    /**
     * Persists new vehicle into database
     * @param vehicle vehicle entity
     */
    void create(Vehicle vehicle);

    /**
     * Gets vehicle entity for given id
     * @param id id of vehicle
     * @return vehicle entity
     */
    Vehicle findById(Long id);

    /**
     * Returns list of all vehicles from DB
     * @return list of vehicles entity
     */
    List<Vehicle> findAll();

    /**
     * Update existing entity (vehicle) or create new if not exist in DB
     * @param vehicle updated entity vehicle
     * @return the managed instance that the state was merged to
     */
    Vehicle update(Vehicle vehicle);

    /**
     *  Remove vehicle from DB
     * @param vehicle vehicle entity to be removed from DB
     * @throws IllegalArgumentException if given vehicle is null or is not stored in DB
     */
    void remove(Vehicle vehicle) throws IllegalArgumentException;

    /**
     * Get list of vehicles of given category
     * @param vehicleCategory vehicle category enetity
     * @return list of vehicles
     */
    List<Vehicle> findByVehicleCategory (VehicleCategory vehicleCategory);

}
