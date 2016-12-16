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

    /**
     *
     * Get Vehicle by identifier id curl -i -X GET
     * http://localhost:8080/fleet-management-rest/vehicles/1
     *
     * @param id identifier for a vehicle
     * @return VehicleDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final VehicleDTO getVehicle(@PathVariable("id") long id) throws Exception {
        logger.debug("rest getVehicle({})", id);
        VehicleDTO vehicleDTO = vehicleFacade.findVehicleById(id);
        if (vehicleDTO != null) {
            return vehicleDTO;
        } else {
            throw new ResourceNotFoundException();
        }

    }

    /**
     * Delete (disable) one vehicle by id curl -i -X DELETE
     * http://localhost:8080/fleet-management-rest/vehicles/1
     *
     * @param id identifier for vehicle
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteVehicle(@PathVariable("id") long id) throws Exception {
        logger.debug("rest deleteVehicle({})", id);
        try {
            vehicleFacade.disableVehicle(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Create a new vehicle by POST method

     * http://localhost:8080/fleet-management-rest/vehicles/create
     *
     * @param vehicle VehicleCreateDTO with required fields for creation
     * @return the created vehicle VehicleDTO
     * @throws ResourceAlreadyExistingException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final VehicleDTO createVehicle(@RequestBody VehicleDTO vehicle) throws Exception {

        logger.debug("rest createVehicle()");

        try {
            Long id = vehicleFacade.addNewVehicle(vehicle);
            return vehicleFacade.findVehicleById(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }
}
