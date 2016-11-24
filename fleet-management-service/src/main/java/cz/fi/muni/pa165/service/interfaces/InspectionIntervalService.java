package cz.fi.muni.pa165.service.interfaces;

import cz.fi.muni.pa165.entity.InspectionInterval;

import java.util.Collection;

/**
 * @author Jozef Krcho
 */
public interface InspectionIntervalService extends Service<InspectionInterval, Long> {

    Collection<InspectionInterval> findAllWithPlannedInspection(int daysInFuture);
}
