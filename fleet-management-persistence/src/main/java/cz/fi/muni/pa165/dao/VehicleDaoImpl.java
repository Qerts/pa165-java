package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.dao.interfaces.JourneyDao;
import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
import cz.fi.muni.pa165.entity.Journey;
import cz.fi.muni.pa165.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

/**
 * @author Martin Schmidt + Michal Balicky
 */
@Repository
public class VehicleDaoImpl extends JpaDao<Vehicle, Long> implements VehicleDao {
}
