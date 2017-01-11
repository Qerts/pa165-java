package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.Dao;
import cz.fi.muni.pa165.dao.interfaces.InspectionIntervalDao;
import cz.fi.muni.pa165.entity.Inspection;
import cz.fi.muni.pa165.entity.InspectionInterval;
import cz.fi.muni.pa165.service.interfaces.InspectionIntervalService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
                    deadline.setTime(ii.getNewestInspection().getPerformedOn());
                    deadline.add(Calendar.DATE, ii.getDays());

                    Calendar horizon = Calendar.getInstance();
                    horizon.setTime(dateTimeService.getCurrentDate());
                    horizon.add(Calendar.DATE, daysInFuture);

                    return deadline.before(horizon);
                }
        ).collect(Collectors.toList());
    }

    public List<InspectionInterval> getInspectionIntervalsForVehicle(Long vehicleId) {
        return findAll().stream()
                .filter(ii -> ii.getVehicle().getId().equals(vehicleId))
                .collect(Collectors.toList());
    }

    public Date getLastInspectionWasPerformedOn(InspectionInterval ii) {
        Inspection lastInspection = ii.getInspections().stream().reduce(
                null,
                (Inspection newest, Inspection current)
                        -> newest == null || current.getPerformedOn().after(newest.getPerformedOn()) ? current : newest
        );

        return lastInspection == null ? null : lastInspection.getPerformedOn();
    }

    public Date getNextInspectionShouldBePerformedUntil(InspectionInterval ii) {
        if (getLastInspectionWasPerformedOn(ii) == null) {
            return Calendar.getInstance().getTime();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(getLastInspectionWasPerformedOn(ii));
        cal.add(Calendar.DAY_OF_MONTH, ii.getDays());

        return cal.getTime();
    }

    public int getNextInspectionShouldBePerformedInDays(InspectionInterval ii) {
        return (int) TimeUnit.DAYS.convert(
            getNextInspectionShouldBePerformedUntil(ii).getTime() - dateTimeService.getCurrentDate().getTime(),
            TimeUnit.MILLISECONDS
        );
    }
}
