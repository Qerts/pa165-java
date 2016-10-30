package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
import cz.fi.muni.pa165.entity.Vehicle;
import org.springframework.stereotype.Repository;

/**
 * @author Martin Schmidt
 */
@Repository
public class VehicleDaoImpl extends JpaDao<Vehicle, Long> implements VehicleDao {

}
