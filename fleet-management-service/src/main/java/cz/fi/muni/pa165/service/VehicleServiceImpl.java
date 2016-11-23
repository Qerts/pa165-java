package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.Dao;
import cz.fi.muni.pa165.dao.interfaces.JourneyDao;
import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
import cz.fi.muni.pa165.entity.Journey;
import cz.fi.muni.pa165.entity.Vehicle;
import cz.fi.muni.pa165.service.interfaces.VehicleService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

/**
 * @author Martin Schmidt
 */
@Service
public class VehicleServiceImpl extends JpaService<Vehicle, Long> implements VehicleService {

    @Inject
    private VehicleDao vehicleDao;
    @Inject
    private JourneyDao journeyDao;

    @Override
    protected Dao<Vehicle, Long> getDao() {
        return vehicleDao;
    }

    @Override
    public double getTotalKilometrage(long vehicleId) {
        double result = this.round(this.vehicleDao.findById(vehicleId).getInitialKilometrage(), 1);

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
