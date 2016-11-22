package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.VehicleCategoryDao;
import cz.fi.muni.pa165.entity.VehicleCategory;
import cz.fi.muni.pa165.service.interfaces.VehicleCategoryService;

import javax.inject.Inject;
import java.util.List;

/**
  * @author Jozef Krcho
 */
public class VehicleCategoryServiceImpl implements VehicleCategoryService {

    @Inject
    private VehicleCategoryDao vehicleCategoryDao;

    @Override
    public VehicleCategory findById(Long id) {
        return vehicleCategoryDao.findById(id);
    }

    @Override
    public List<VehicleCategory> findAll() {
        return vehicleCategoryDao.findAll();
    }

    @Override
    public void create(VehicleCategory vehicleCategory) {
        vehicleCategoryDao.persist(vehicleCategory);
    }

    @Override
    public void update(VehicleCategory vehicleCategory) {
        vehicleCategoryDao.merge(vehicleCategory);
    }

    @Override
    public void remove(VehicleCategory vehicleCategory) {
        vehicleCategoryDao.remove(vehicleCategory);
    }
}
