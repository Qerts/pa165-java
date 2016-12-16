package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.InspectionDTO;
import cz.fi.muni.pa165.dto.InspectionIntervalDTO;
import cz.fi.muni.pa165.entity.Inspection;
import cz.fi.muni.pa165.service.interfaces.BeanMappingService;
import cz.fi.muni.pa165.service.interfaces.InspectionIntervalService;
import cz.fi.muni.pa165.service.interfaces.InspectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collection;

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
        return bms.mapTo(inspectionIntervalService.findAll(), InspectionIntervalDTO.class);
    }

    @Override
    public Collection<InspectionIntervalDTO> listPlannedInspectionIntervals(Integer daysInFuture) {
        return bms.mapTo(
                inspectionIntervalService.findAllWithPlannedInspection(daysInFuture),
                InspectionIntervalDTO.class
        );
    }

    @Override
    public void performInspection(InspectionDTO inspectionDTO) {
        inspectionService.create(bms.mapTo(inspectionDTO, Inspection.class));
    }
}
