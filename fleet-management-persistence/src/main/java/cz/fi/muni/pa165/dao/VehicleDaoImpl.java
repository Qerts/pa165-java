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

    @Autowired
    private JourneyDao journeyDao;

    @Override
    public double getTotalKilometrage(long vehicleId) {
        double result = this.round(this.findById(vehicleId).getInitialKilometrage(), 1);

        List<Journey> journeys = this.journeyDao.findAllByVehicleId(vehicleId);

        for (Iterator<Journey> iterator = journeys.iterator(); iterator.hasNext();){
            result = result + this.round(iterator.next().getDistance(), 1);
        }

        return result;
    }

    private double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
