package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.Dao;
import cz.fi.muni.pa165.dao.interfaces.VehicleCategoryDao;
import cz.fi.muni.pa165.entity.VehicleCategory;
import cz.fi.muni.pa165.service.interfaces.VehicleCategoryService;

import javax.inject.Inject;

/**
 * @author Jozef Krcho
 */
public class VehicleCategoryServiceImpl extends JpaService<VehicleCategory, Long> implements VehicleCategoryService {

    @Inject
    private VehicleCategoryDao vehicleCategoryDao;

    @Override
    protected Dao<VehicleCategory, Long> getDao() {
        return vehicleCategoryDao;
    }

}
