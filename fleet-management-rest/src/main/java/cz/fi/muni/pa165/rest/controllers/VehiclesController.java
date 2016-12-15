package cz.fi.muni.pa165.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.fi.muni.pa165.dto.VehicleDTO;
import cz.fi.muni.pa165.facade.VehicleFacade;
import cz.fi.muni.pa165.rest.tools.ApiUris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Richard Trebichavský
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_VEHICLES)
public class VehiclesController {
    final static Logger logger = LoggerFactory.getLogger(VehiclesController.class);

    @Inject
    private VehicleFacade vehiclesFacade;

    /**
     * Get list of all Vehicles GET
     * http://localhost:8080/rest/vehicles
     *
     * @return list of VehicleDTO
     * @throws JsonProcessingException
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<VehicleDTO> getVehicles() throws JsonProcessingException {
        logger.debug("rest getVehicles()");
        List<VehicleDTO> allVehicles = new ArrayList<>(vehiclesFacade.getAllVehicles());
        return allVehicles;
    }
}
