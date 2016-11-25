package cz.fi.muni.pa165.dao.interfaces;

import cz.fi.muni.pa165.entity.Vehicle;

import java.util.Collection;
import java.util.List;

/**
 * @author Martin Schmidt
 */
public interface VehicleDao extends Dao<Vehicle, Long>{
    List<Vehicle> findVehiclesAvailable(long employeeId);
}
