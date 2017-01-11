package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.JourneyDTO;
import cz.fi.muni.pa165.service.interfaces.BeanMappingService;
import cz.fi.muni.pa165.service.interfaces.JourneyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Martin Schmidt
 */
@Service
@Transactional
public class JourneyFacadeImpl implements JourneyFacade {

    @Inject
    private JourneyService journeyService;

    @Inject
    private BeanMappingService bms;

    public List<JourneyDTO> getAllJourneys() {
        return bms.mapTo(journeyService.findAll(), JourneyDTO.class);
    }

    public List<JourneyDTO> getJourneysByEmployee(Long employeeId) {
        return bms.mapTo(journeyService.getJourneysByEmployee(employeeId), JourneyDTO.class);
    }

    public List<JourneyDTO> getJourneys(Date from, Date to, Long employeeId) {
        return bms.mapTo(journeyService.getAllJourneys(from, to, employeeId), JourneyDTO.class);
    }


    public void addJourney(JourneyDTO journey) {
        Long id = journeyService.beginJourney(journey.getVehicle().getId(), journey.getEmployee().getId(), journey.getBorrowedAt()).getId();
        journeyService.finishJourney(id, journey.getDistance(), journey.getReturnedAt());
    }

    public JourneyDTO beginJourney(Long vehicleId, Long employeeId, Date startDate) {
        if (journeyService.hasActiveJourney(vehicleId)) {
            throw new RuntimeException("Vehicle #" + vehicleId + " is already borrowed. It must be returned prior to be borrowed agian.");
        }

        return bms.mapTo(journeyService.beginJourney(vehicleId, employeeId, startDate), JourneyDTO.class);
    }

    public void finishJourney(Long journeyId, Float drivenDistance, Date endDate) {
        journeyService.finishJourney(journeyId, drivenDistance, endDate);
    }

    public List<JourneyDTO> getAllUnfinishedJourneys() {
        return bms.mapTo(journeyService.findAllUnfinished(), JourneyDTO.class);
    }

    public List<JourneyDTO> getUnfinishedJourneysOfUser(long userId) {
        return getAllUnfinishedJourneys().stream().filter(j -> j.getEmployee().getId().equals(userId))
                .collect(Collectors.toList());
    }
}
