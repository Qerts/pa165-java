package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.VehicleCategoryDTO;
import cz.fi.muni.pa165.dto.VehicleCreateDTO;
import cz.fi.muni.pa165.dto.VehicleDTO;

import java.util.Collection;

/**
 * @author Michal Balicky
 */
public interface VehicleFacade {

    /**
     * find all vehicles
     *
     * @return list of all vehicles
     */
    Collection<VehicleDTO> getAllVehicles();


    /**
     * Finds all vehicles that are borrowable by the employee.
     *
     * @param employeeId Id of the employee
     * @return List of vehicles available to borrow for given employee.
     */
    Collection<VehicleDTO> findVehiclesToBeBorrowedByUser(Long employeeId);

    /**
     * Counts total kilometres gone with given vehicle. It sums all journey and initial kilometrage of the vehicle.
     *
     * @param vehicleId Id of the vehicle
     * @return Get current state of the vehicle's tachometer.
     */
    double getTotalKilometrage(long vehicleId);

    /**
     * Finds vehicle by its id.
     *
     * @param vehicleId Id of the vehicle
     * @return Vehicle.
     */
    VehicleDTO findVehicleById(Long vehicleId);

    /**
     * Adds new vehicle to company's fleet.
     *
     * @param vehicle Vehicle to be created
     * @return vehicle id
     */
    Long addNewVehicle(VehicleCreateDTO vehicle);

    /**
     * Updates given vehicle.
     *
     * @param vehicle Vehicle to be updated
     */
    void updateVehicle(VehicleDTO vehicle);

    /**
     * Disables given vehicle. Record still stays in database.
     *
     * @param vehicleId Id of the vehicle
     */
    void disableVehicle(Long vehicleId);

    /**
     * get all vehicles that are not disabled
     *
     * @return collection of vehicles dto
     */
    Collection<VehicleDTO> getAllActiveVehicles();

    /**
     * get all vehicle categories
     *
     * @return collection of vehicle categories dto
     */
    Collection<VehicleCategoryDTO> getAllVehicleCategories();

    /**
     * obsolete vehicles
     *
     * @return obsolete vehicles
     */
    Collection<VehicleDTO> getObsoleteVehicles();
}
