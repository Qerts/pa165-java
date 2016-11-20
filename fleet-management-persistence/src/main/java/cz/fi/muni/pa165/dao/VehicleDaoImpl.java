package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.dao.interfaces.JourneyDao;
import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
import cz.fi.muni.pa165.entity.Journey;
import cz.fi.muni.pa165.entity.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

/**
 * @author Martin Schmidt
 */
@Repository
public class VehicleDaoImpl extends JpaDao<Vehicle, Long> implements VehicleDao {

    private JourneyDaoImpl journeyDao;

    @Override
    public double getTotalKilometrage(long vehicleId) {
        double result = this.findById(vehicleId).getInitialKilometrage();

        List<Journey> journeys = this.journeyDao.findAllByVehicleId(vehicleId);

        for (Iterator<Journey> iterator = journeys.iterator(); iterator.hasNext();){
            result =+ iterator.next().getDistance();
        }

        return result;
    }
}
