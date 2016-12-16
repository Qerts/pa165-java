package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.InspectionDTO;
import cz.fi.muni.pa165.dto.InspectionIntervalDTO;

import java.util.Collection;

/**
 * @author Richard Trebichavský
 */
public interface InspectionFacade {

    /**
     * List all InspectionIntervals.
     *
     * @return List of InspectionIntervals.
     */
    Collection<InspectionIntervalDTO> listAllInspectionIntervals();


    /**
     * List all InspectionIntervals, whose inspection needs to be performed within given number of days.
     *
     * @param daysInFuture Number of days.
     * @return List of InspectionIntervals matching condition.
     */
    Collection<InspectionIntervalDTO> listPlannedInspectionIntervals(Integer daysInFuture);


    /**
     * Record that inspection was performed.
     *
     * @param inspection Inspection DTO.
     */
    void performInspection(InspectionDTO inspection);
}
