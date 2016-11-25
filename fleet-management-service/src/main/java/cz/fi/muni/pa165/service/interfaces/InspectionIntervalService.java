package cz.fi.muni.pa165.service.interfaces;

import cz.fi.muni.pa165.entity.InspectionInterval;

import java.util.Collection;

/**
 * @author Jozef Krcho
 */
public interface InspectionIntervalService extends Service<InspectionInterval, Long> {

    /**
     * Returns inspection intervals, whose inspection needs to be performed withing the certain amount of days from
     * today.
     *
     * @param daysInFuture Specifies a horizon until when the inspections needs to be performed.
     * @return Inspection intervals matching condition.
     */
    Collection<InspectionInterval> findAllWithPlannedInspection(int daysInFuture);
}
