package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.Dao;
import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
import cz.fi.muni.pa165.entity.Vehicle;
import cz.fi.muni.pa165.service.interfaces.VehicleService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author Martin Schmidt
 */
@Service
public class VehicleServiceImpl extends JpaService<Vehicle, Long> implements VehicleService {

    @Inject
    private VehicleDao vehicleDao;

    @Override
    protected Dao<Vehicle, Long> getDao() {
        return vehicleDao;
    }
}
