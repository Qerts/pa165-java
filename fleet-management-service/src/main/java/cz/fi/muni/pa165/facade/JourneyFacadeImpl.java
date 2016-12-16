package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.JourneyDTO;
import cz.fi.muni.pa165.service.interfaces.BeanMappingService;
import cz.fi.muni.pa165.service.interfaces.JourneyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * @author Martin Schmidt
 */
@Service
@Transactional
public class JourneyFacadeImpl implements JourneyFacade {

    @Inject
    private JourneyService journeyService;

    @Inject
    private BeanMappingService beanMappingService;

    public List<JourneyDTO> getAllJourneys() {
        return beanMappingService.mapTo(journeyService.findAll(), JourneyDTO.class);
    }

    public List<JourneyDTO> getJourneysByEmployee(Long employeeId) {
        return beanMappingService.mapTo(journeyService.getJourneysByEmployee(employeeId), JourneyDTO.class);
    }

    public List<JourneyDTO> getJourneys(Date from, Date to, Long employeeId) {
        return beanMappingService.mapTo(journeyService.getAllJourneys(from, to, employeeId), JourneyDTO.class);
    }


    public void addJourney(JourneyDTO journey) {
        Long id = journeyService.beginJourney(journey.getVehicle().getId(), journey.getEmployee().getId(), journey.getBorrowedAt()).getId();
        journeyService.finishJourney(id, journey.getDistance(), journey.getReturnedAt());
    }

    public JourneyDTO beginJourney(Long vehicleId, Long employeeId, Date startDate) {
        return beanMappingService.mapTo(journeyService.beginJourney(vehicleId, employeeId, startDate), JourneyDTO.class);
    }

    public void finishJourney(Long journeyId, Float drivenDistance, Date endDate) {
        journeyService.finishJourney(journeyId, drivenDistance, endDate);
    }
}
