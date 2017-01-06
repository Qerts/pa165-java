package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.Dao;
import cz.fi.muni.pa165.dao.interfaces.JourneyDao;
import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
import cz.fi.muni.pa165.entity.Journey;
import cz.fi.muni.pa165.entity.Vehicle;
import cz.fi.muni.pa165.service.interfaces.VehicleService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;
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

        Vehicle v = this.vehicleDao.findById(vehicleId);

        double result = this.round(v.getInitialKilometrage(), 1);

        List<Journey> journeys = this.journeyDao.findAllByVehicleId(vehicleId);

        for (Iterator<Journey> iterator = journeys.iterator(); iterator.hasNext(); ) {
            result = result + this.round(iterator.next().getDistance(), 1);
        }

        return result;
    }

    public List<Vehicle> findActiveVehicles() {
        List<Vehicle> all = vehicleDao.findAll();
        return getOnlyActive(all);
    }


    @Override
    public List<Vehicle> findVehiclesAvailable(long employeeId) {
        List<Vehicle> all = this.vehicleDao.findVehiclesAvailable(employeeId);
        return getOnlyActive(all);
    }

    private List<Vehicle> getOnlyActive(List<Vehicle> all) {
        List<Vehicle> active = new ArrayList<>();
        for (Vehicle v : all) {
            if (Boolean.TRUE.equals(v.getActive())) {
                active.add(v);
            }
        }
        return active;
    }


    @Override
    public void disable(long vehicleId) {
        List<Journey> journeys = this.journeyDao.findAllByVehicleId(vehicleId);
        journeys.sort(JourneyComparator);

        Vehicle v = this.vehicleDao.findById(vehicleId);
        v.setActive(false);
        this.vehicleDao.merge(v);
    }

    private double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    public static Comparator<Journey> JourneyComparator = new Comparator<Journey>() {

        @Override
        public int compare(Journey j1, Journey j2) {
            if (j1.getReturnedAt() == null)
                return 1;
            if (j2.getReturnedAt() == null)
                return -1;

            return j1.getReturnedAt().compareTo(j2.getReturnedAt());
        }
    };
}
