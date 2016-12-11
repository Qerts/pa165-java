package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.rest.ApiUris;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cz.fi.muni.pa165.dto.VehicleDTO;

import cz.fi.muni.pa165.facade.VehicleFacade;
import cz.fi.muni.pa165.rest.exceptions.InvalidParameterException;
import cz.fi.muni.pa165.rest.exceptions.ResourceAlreadyExistingException;
import cz.fi.muni.pa165.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

/**
 * REST Controller for Vehicles
 *
 * @author Martin Schmidt
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_VEHICLES)
public class VehiclesController {

    final static Logger logger = LoggerFactory.getLogger(VehiclesController.class);

    @Inject
    private VehicleFacade vehicleFacade;

    /**
     * Get list of Vehicles curl -i -X GET
     * http://localhost:8080/fleet-management-rest/vehicles
     *
     * @return VehicleDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<VehicleDTO> getVehicles() {
        logger.debug("rest getVehicles()");
        return vehicleFacade.getAllActiveVehicles();
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
