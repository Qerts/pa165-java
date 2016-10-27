package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.dao.interfaces.VehicleCategoryDao;
import cz.fi.muni.pa165.entity.VehicleCategory;
import org.springframework.stereotype.Repository;

/**
 * Created by Martin on 23.10.2016.
 * @author Martin Schmidt
 */
@Repository
public class VehicleCategoryDaoImpl extends JpaDao<VehicleCategory, Long> implements VehicleCategoryDao {

}
