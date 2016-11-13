package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.dao.interfaces.VehicleCategoryDao;
import cz.fi.muni.pa165.entity.VehicleCategory;
import org.springframework.stereotype.Repository;

/**
 * @author Martin Schmidt
 */
@Repository
public class VehicleCategoryDaoImpl extends JpaDao<VehicleCategory, Long> implements VehicleCategoryDao {

}
