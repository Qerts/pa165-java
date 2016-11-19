package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
import cz.fi.muni.pa165.entity.Vehicle;
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
        return vehicleDao.findById(id);
    }

    public List<Vehicle> findAll() {
        return vehicleDao.findAll();
    }

    public void create(Vehicle v) {
        vehicleDao.persist(v);
    }

    public void update(Vehicle v) {
        vehicleDao.merge(v);
    }

    public void remove(Vehicle v) {
        vehicleDao.remove(v);
    }

}
