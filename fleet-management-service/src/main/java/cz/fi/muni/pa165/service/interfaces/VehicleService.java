package cz.fi.muni.pa165.service.interfaces;

import cz.fi.muni.pa165.entity.Vehicle;

/**
 * @author Martin Schmidt
 */
public interface VehicleService extends Service<Vehicle, Long> {
    /**
     * @param vehicleId - id of given vehicle
     * @return total amount of kilometers gone with this vehicle, in simple words - state of tachometer
     */
    double getTotalKilometrage(long vehicleId);
}
