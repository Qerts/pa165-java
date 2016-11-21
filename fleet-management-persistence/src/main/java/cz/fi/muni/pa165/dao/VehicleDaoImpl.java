package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.dao.interfaces.JourneyDao;
import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
import cz.fi.muni.pa165.entity.Journey;
import cz.fi.muni.pa165.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

/**
 * @author Martin Schmidt
 */
@Repository
public class VehicleDaoImpl extends JpaDao<Vehicle, Long> implements VehicleDao {

    @Override
    public double getTotalKilometrage(long vehicleId, JourneyDao journeyDao) {
        double result = this.findById(vehicleId).getInitialKilometrage();

        List<Journey> journeys = journeyDao.findAllByVehicleId(vehicleId);

        for (Iterator<Journey> iterator = journeys.iterator(); iterator.hasNext();){
            result =+ iterator.next().getDistance();
        }

        return result;
    }
}
