package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.VehicleDTO;

import java.util.Collection;

/**
 * Created by Michal Balicky on 24/11/2016.
 */
public interface VehicleFacade {

/*
    /**
     * Finds all vehicles that are borrowable by the employee.
     * @param employeeId Id of the employee
     * @return List of vehicles available to borrow for given employee.
     */
    //Collection<VehicleDTO> findVehiclesAvailable(Long employeeId);

    /**
     * Counts total kilometres gone with given vehicle. It sums all journey and initial kilometrage of the vehicle.
     * @param vehicleId Id of the vehicle
     * @return Get current state of the vehicle's tachometer.
     */
    double getTotalKilometrage(long vehicleId);

    /**
     * Finds vehicle by its id.
     * @param vehicleId Id of the vehicle
     * @return
     */
    VehicleDTO findVehicleById(Long vehicleId);

    /**
     * Adds new vehicle to company's fleet.
     * @param vehicle Vehicle to be created
     */
    void addNewVehicle(VehicleDTO vehicle);

    /**
     * Updates given vehicle.
     * @param vehicle Vehicle to be updated
     */
    void updateVehicle(VehicleDTO vehicle);

    /**
     * Disables given vehicle. Record still stays in database.
     * @param vehicleId Id of the vehicle
     */
    void disableVehicle(Long vehicleId);
}
