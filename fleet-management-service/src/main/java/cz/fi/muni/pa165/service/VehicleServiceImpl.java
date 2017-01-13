package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.Dao;
import cz.fi.muni.pa165.dao.interfaces.JourneyDao;
import cz.fi.muni.pa165.dao.interfaces.VehicleDao;
import cz.fi.muni.pa165.entity.Journey;
import cz.fi.muni.pa165.entity.Vehicle;
import cz.fi.muni.pa165.service.interfaces.JourneyService;
import cz.fi.muni.pa165.service.interfaces.VehicleService;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Martin Schmidt
 */
@Service
public class VehicleServiceImpl extends JpaService<Vehicle, Long> implements VehicleService {

    @Inject
    private VehicleDao vehicleDao;

    @Inject
    private JourneyDao journeyDao;

    @Inject
    private JourneyService journeyService;

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
            Float distance = iterator.next().getDistance();
            if (distance != null) {
                result = result + this.round(distance, 1);
            }
        }

        return result;
    }

    @Override
    public List<Vehicle> findAllActiveVehicles() {
        return getOnlyActive(vehicleDao.findAll());
    }

    @Override
    public List<Vehicle> findAllVehiclesToBeBorrowed() {
        return getOnlyAvailableToBorrow(getOnlyActive(vehicleDao.findAll()));
    }

    @Override
    public List<Vehicle> findVehiclesToBeBorrowedByUser(long employeeId) {
        return getOnlyAvailableToBorrow(getOnlyActive(vehicleDao.findVehiclesAvailable(employeeId)));
    }

    @Override
    public List<Vehicle> getAllObsoleteVehicles() {
        return vehicleDao.findAll().stream().filter(v -> v.getActive() == false).collect(Collectors.toList());
    }

    private List<Vehicle> getOnlyActive(List<Vehicle> all) {
        return all.stream().filter(Vehicle::getActive).collect(Collectors.toList());
    }

    private List<Vehicle> getOnlyAvailableToBorrow(List<Vehicle> vehicles) {
        return vehicles.stream().filter(v -> !journeyService.hasActiveJourney(v.getId())).collect(Collectors.toList());
    }

    @Override
    public void disable(long vehicleId) {
        List<Journey> journeys = journeyDao.findAllByVehicleId(vehicleId);
        journeys.sort(JourneyComparator);

        Vehicle v = vehicleDao.findById(vehicleId);
        v.setActive(false);
        vehicleDao.merge(v);

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
