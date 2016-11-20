package cz.fi.muni.pa165.dao.interfaces;

import cz.fi.muni.pa165.entity.Journey;

import java.util.List;

/**
 * @author Richard Trebichavský
 */
public interface JourneyDao extends Dao<Journey, Long> {
    List<Journey> findAllByVehicleId(double vehicleId);
}
