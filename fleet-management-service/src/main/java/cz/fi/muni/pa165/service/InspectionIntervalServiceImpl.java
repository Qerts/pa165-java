package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.Dao;
import cz.fi.muni.pa165.dao.interfaces.InspectionIntervalDao;
import cz.fi.muni.pa165.entity.InspectionInterval;
import cz.fi.muni.pa165.entity.Vehicle;
import cz.fi.muni.pa165.service.interfaces.InspectionIntervalService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jozef Krcho
 * @author Richard Trebichavský
 */
@Service
public class InspectionIntervalServiceImpl extends JpaService<InspectionInterval, Long> implements InspectionIntervalService {

    @Inject
    private InspectionIntervalDao inspectionIntervalDao;

    @Inject
    private DateTimeService dateTimeService;

    @Override
    protected Dao<InspectionInterval, Long> getDao() {
        return inspectionIntervalDao;
    }

    @Override
    public List<InspectionInterval> findAllWithPlannedInspection(int daysInFuture) {
        return inspectionIntervalDao.findAll().stream().filter(
                (ii) -> {
                    if (!ii.hasInspections()) {
                        return true;
                    }

                    Calendar deadline = Calendar.getInstance();
                    deadline.setTime(ii.getNewestInspection().getPerformedAt());
                    deadline.add(Calendar.DATE, ii.getDays());

                    Calendar horizon = Calendar.getInstance();
                    horizon.setTime(dateTimeService.getCurrentDate());
                    horizon.add(Calendar.DATE, daysInFuture);

                    return deadline.before(horizon);
                }
        ).collect(Collectors.toList());
    }

    public List<InspectionInterval> getInspectionIntervalsForVehicle(Long vehicleId) {
        List<InspectionInterval> all = this.findAll();
        List<InspectionInterval> list = new ArrayList<>();
        for (InspectionInterval i : all) {
            Vehicle v = i.getVehicle();
            if (v != null && vehicleId.equals(v.getId())) {
                list.add(i);
            }
        }
        return list;
    }
}
