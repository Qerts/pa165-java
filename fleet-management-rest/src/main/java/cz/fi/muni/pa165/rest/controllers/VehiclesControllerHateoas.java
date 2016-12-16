package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.dto.VehicleDTO;
import cz.fi.muni.pa165.facade.VehicleFacade;
import cz.fi.muni.pa165.rest.assemblers.VehicleResourceAssembler;
import cz.fi.muni.pa165.rest.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.rest.exceptions.ResourceNotModifiedException;
import cz.fi.muni.pa165.rest.tools.ApiUris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * REST HATEOAS Controller for Vehicles
 * This class shows Spring support for full HATEOAS REST services
 * it uses the {@link cz.fi.muni.pa165.rest.assemblers.VehicleResourceAssembler} to create
 * resources to be returned as ResponseEntities
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_VEHICLES_HATEOAS)
public class VehiclesControllerHateoas {

    final static Logger logger = LoggerFactory.getLogger(VehiclesControllerHateoas.class);

    @Inject
    private VehicleFacade vehicleFacade;

    @Inject
    private VehicleResourceAssembler vehicleResourceAssembler;

    /**
     * Get list of vehicles
     *
     * @return HttpEntity<Resources<Resource<VehicleDTO>>>
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<Resources<Resource<VehicleDTO>>> getVehicles() {

        logger.debug("rest getVehicles({}) hateoas");

        Collection<VehicleDTO> vehiclesDTO = vehicleFacade.getAllVehicles();
        Collection<Resource<VehicleDTO>> vehicleResourceCollection = new ArrayList();

        for (VehicleDTO p : vehiclesDTO) {
            vehicleResourceCollection.add(vehicleResourceAssembler.toResource(p));
        }

        Resources<Resource<VehicleDTO>> vehiclesResources = new Resources<Resource<VehicleDTO>>(vehicleResourceCollection);
        vehiclesResources.add(linkTo(VehiclesControllerHateoas.class).withSelfRel());

        return new ResponseEntity<Resources<Resource<VehicleDTO>>>(vehiclesResources, HttpStatus.OK);

    }

    /**
     * Get list of vehicles - this method also supports HTTP caching
     * See http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html#mvc-caching
     * <p>
     * See also http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/context/request/WebRequest.html#checkNotModified-java.lang.String-
     * <p>
     * The conditional request can be sent with
     * curl -i -X GET http://localhost:8080/fleet-management-rest/vehicles_hateoas/cached  --header 'If-None-Match: "077e8fe377ab6dfa8b765b166972ba2d6"'
     *
     * @return HttpEntity<Resources<Resource<VehicleDTO>>>
     */
    @RequestMapping(value = "/cached", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<Resources<Resource<VehicleDTO>>> getVehiclesCached(WebRequest webRequest) {

        logger.debug("rest getVehicles({}) hateoas cached version");

        final Collection<VehicleDTO> vehiclesDTO = vehicleFacade.getAllVehicles();
        final Collection<Resource<VehicleDTO>> vehicleResourceCollection = new ArrayList();

        for (VehicleDTO p : vehiclesDTO) {
            vehicleResourceCollection.add(vehicleResourceAssembler.toResource(p));
        }

        Resources<Resource<VehicleDTO>> vehiclesResources = new Resources(vehicleResourceCollection);
        vehiclesResources.add(linkTo(VehiclesControllerHateoas.class).withSelfRel());

        final StringBuffer eTag = new StringBuffer("\"");
        eTag.append(Integer.toString(vehiclesResources.hashCode()));
        eTag.append('\"');

        if (webRequest.checkNotModified(eTag.toString())) {
            throw new ResourceNotModifiedException();
        }

        return ResponseEntity.ok().eTag(eTag.toString()).body(vehiclesResources);
    }

    /**
     * Get one vehicle according to id
     *
     * @param id identifier for vehicle
     * @return HttpEntity<Resource<VehicleDTO>>
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<Resource<VehicleDTO>> getVehicle(@PathVariable("id") long id) throws Exception {

        logger.debug("rest getVehicle({}) hateoas", id);

        try {
            VehicleDTO vehicleDTO = vehicleFacade.findVehicleById(id);
            Resource<VehicleDTO> resource = vehicleResourceAssembler.toResource(vehicleDTO);
            return new ResponseEntity<Resource<VehicleDTO>>(resource, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Delete one vehicle by id curl -i -X DELETE
     * http://localhost:8080/fleet-management-rest/vehicles/1
     *
     * @param id identifier for vehicle
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteVehicle(@PathVariable("id") long id) throws Exception {
        logger.debug("rest deleteVehicle({}) hateoas", id);
        try {
            vehicleFacade.disableVehicle(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

}
