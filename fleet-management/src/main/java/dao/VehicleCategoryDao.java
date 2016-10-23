package dao;

import entity.VehicleCategory;

import java.util.List;

/**
 * Created by Martin on 23.10.2016.
 * @author Martin Schmidt
 */
public interface VehicleCategoryDao {
    /**
     * Persists new vehicle category into database
     * @param vc vehicle category entity
     */
    void create(VehicleCategory vc);

    /**
     * Gets vehicle category entity for given id
     * @param id id of category
     * @return vehicle category entity
     */
    VehicleCategory findById(Long id);

    /**
     * Returns list of all vehicle categories from DB
     * @return list of category entity
     */
    List<VehicleCategory> findAll();

    /**
     * Update existing entity (vehicle cat.) or create new if not exist in DB
     * @param vc updated entity vehicle
     * @return the managed instance that the state was merged to
     */
    VehicleCategory update(VehicleCategory vc);

    /**
     * get vehicle category by name
     * @param name name of category
     * @return category entity
     */
    VehicleCategory findByName(String name);


}
