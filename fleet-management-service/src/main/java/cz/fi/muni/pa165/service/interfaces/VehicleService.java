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
     * @param vehicleId Id of the vehicle
     */
    void disable(long vehicleId);

    /**
     * Finds all vehicles available to borrow for given employee
     * @param employeeId Id of given employee
     * @return Collection of all vehicles that are borrowable by the employee
     */
    List<Vehicle> findVehiclesAvailable(long employeeId);

}
