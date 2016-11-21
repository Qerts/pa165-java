package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
import cz.fi.muni.pa165.entity.Vehicle;
import cz.fi.muni.pa165.exceptions.FleetManagementDAException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

import java.util.List;

/**
 * Created by Martin on 13.11.2016.
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    @Inject
    private VehicleDao vehicleDao;


    public Vehicle findById(Long id) {
        try {
            return vehicleDao.findById(id);
        } catch (RuntimeException ex) {
            throw new FleetManagementDAException("find vehicle by id error", ex);
        }
    }

    public List<Vehicle> findAll() {
        try {
            return vehicleDao.findAll();
        } catch(RuntimeException ex) {
            throw new FleetManagementDAException("find all vehicle error", ex);
        }
    }

    public void create(Vehicle vehicle) {
        if (vehicle == null) {
            throw new FleetManagementDAException("vehicle cannot be null");
        }
        try {
            vehicleDao.persist(vehicle);
        } catch(RuntimeException ex) {
            throw new FleetManagementDAException("vehicle create error", ex);
        }
    }

    public void update(Vehicle vehicle) {
        if (vehicle == null) {
            throw new FleetManagementDAException("vehicle cannot be null");
        }
        try {
            vehicleDao.merge(vehicle);
        } catch(RuntimeException ex) {
            throw new FleetManagementDAException("vehicle update error", ex);
        }
    }

    public void remove(Vehicle vehicle) {
        if (vehicle == null) {
            throw new FleetManagementDAException("vehicle cannot be null");
        }
        try {
            vehicleDao.remove(vehicle);
        } catch(RuntimeException ex) {
            throw new FleetManagementDAException("vehicle delete error", ex);
        }
    }

}
