package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.VehicleCategory;

import java.util.List;

/**
 * Created by JozeFe on 11/20/2016.
 * @author Jozef Krcho
 */
public interface VehicleCategoryService {
    /**
     * find vehicleCategory by id
     * @param id id of vehicleCategory
     * @return  vehicleCategory
     */
    public VehicleCategory findById(Long id);

    /**
     * find all vehicleCategorys
     * @return list of vehicleCategorys
     */
    public List<VehicleCategory> findAll();

    /**
     * save new vehicleCategory
     * @param vehicleCategory  vehicleCategory
     */
    public void create(VehicleCategory vehicleCategory );

    /**
     * update saved vehicleCategory
     * @param vehicleCategory  vehicleCategory
     */
    public void update(VehicleCategory vehicleCategory );

    /**
     * remove saved vehicleCategory
     * @param vehicleCategory  vehicleCategory
     */
    public void remove(VehicleCategory vehicleCategory );
}
