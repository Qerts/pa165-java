package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.InspectionDTO;
import cz.fi.muni.pa165.dto.InspectionIntervalDTO;
import cz.fi.muni.pa165.entity.Inspection;
import cz.fi.muni.pa165.entity.InspectionInterval;
import cz.fi.muni.pa165.service.interfaces.BeanMappingService;
import cz.fi.muni.pa165.service.interfaces.InspectionIntervalService;
import cz.fi.muni.pa165.service.interfaces.InspectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Richard Trebichavský
 */
@Service
@Transactional
public class InspectionFacadeImpl implements InspectionFacade {

    @Inject
    private BeanMappingService bms;

    @Inject
    private InspectionIntervalService inspectionIntervalService;

    @Inject
    private InspectionService inspectionService;

    @Override
    public Collection<InspectionIntervalDTO> listAllInspectionIntervals() {
        return computeInspectionIntervalComputedProperties(
                bms.mapTo(inspectionIntervalService.findAll(), InspectionIntervalDTO.class)
        );
    }

    @Override
    public Collection<InspectionIntervalDTO> listPlannedInspectionIntervals(Integer daysInFuture) {
        return computeInspectionIntervalComputedProperties(
                bms.mapTo(
                        inspectionIntervalService.findAllWithPlannedInspection(daysInFuture),
                        InspectionIntervalDTO.class
                )
        );
    }

    @Override
    public void performInspection(long inspectionIntervalId, Date performedOn) {
        InspectionInterval ii = inspectionIntervalService.findById(inspectionIntervalId);
        Inspection i = new Inspection(performedOn);
        i.setInspectionInterval(ii);
        inspectionService.create(i);
        Set<Inspection> inspections = ii.getInspections();
        inspections.add(i);
        ii.setInspections(inspections);
        inspectionIntervalService.update(ii);
    }

    @Override
    public void addNewInspectionInterval(InspectionIntervalDTO i) {
        inspectionIntervalService.create(bms.mapTo(i, InspectionInterval.class));
    }

    @Override
    public Collection<InspectionIntervalDTO> getInspectionInterval(Long vehicleId) {
        return bms.mapTo(inspectionIntervalService.getInspectionIntervalsForVehicle(vehicleId), InspectionIntervalDTO.class);
    }

    @Override
    public List<InspectionDTO> listAllInspections() {
        return bms.mapTo(inspectionService.findAll(), InspectionDTO.class);
    }

    private Collection<InspectionIntervalDTO> computeInspectionIntervalComputedProperties(Collection<InspectionIntervalDTO> iiDTOs) {
        for (InspectionIntervalDTO iiDTO : iiDTOs) {
            InspectionInterval ii = inspectionIntervalService.findById(iiDTO.getId());
            iiDTO.setLastInspectionWasPerformedOn(inspectionIntervalService.getLastInspectionWasPerformedOn(ii));
            iiDTO.setNextInspectionShouldBePerformedUntil(inspectionIntervalService.getNextInspectionShouldBePerformedUntil(ii));
            iiDTO.setNextInspectionShouldBePerformedInDays(inspectionIntervalService.getNextInspectionShouldBePerformedInDays(ii));
        }

        return iiDTOs;
    }
}
