package cz.fi.muni.pa165.service.interfaces;

import cz.fi.muni.pa165.entity.Vehicle;

import java.util.List;

/**
 * @author Martin Schmidt
 */
public interface VehicleService extends Service<Vehicle, Long> {
    /**
     * @param vehicleId - id of given vehicle
     * @return total amount of kilometers gone with this vehicle, in simple words - state of tachometer
     */
    double getTotalKilometrage(long vehicleId);


    /**
     * Disables vehicle. Vehicle should be soft deleted.
     *
     * @param vehicleId Id of the vehicle
     */
    void disable(long vehicleId);

    /**
     * get all vehicles that are not disabled
     *
     * @return list of vehicles
     */
    List<Vehicle> findAllActiveVehicles();

    /**
     * get all vehicles that could be borrowed
     *
     * @return list of vehicles
     */
    List<Vehicle> findAllVehiclesToBeBorrowed();

    /**
     * Finds all vehicles available to borrow for given employee. Vehicle must be not on active journey, not borrowed
     * and in category the user has access to.
     *
     * @param employeeId Id of given employee
     * @return Collection of all vehicles that are borrowable by the employee
     */
    List<Vehicle> findVehiclesToBeBorrowedByUser(long employeeId);

    /**
     * obsolete vehicles
     *
     * @return obsolete vehicles
     */
    List<Vehicle> getAllObsoleteVehicles();
}
