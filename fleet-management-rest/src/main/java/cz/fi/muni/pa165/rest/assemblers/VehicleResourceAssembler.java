package cz.fi.muni.pa165.rest.assemblers;

import cz.fi.muni.pa165.dto.VehicleDTO;
import cz.fi.muni.pa165.rest.controllers.VehiclesControllerHateoas;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * This class shows a resource assembler for a HATEOAS REST Service we are
 * mapping the DTO to a resource that can provide links to the different parts
 * of the API See
 * http://docs.spring.io/spring-hateoas/docs/current/reference/html/
 *
 */
@Component
public class VehicleResourceAssembler implements ResourceAssembler<VehicleDTO, Resource<VehicleDTO>> {

    @Override
    public Resource<VehicleDTO> toResource(VehicleDTO vehicleDTO) {
        long id = vehicleDTO.getId();
        Resource<VehicleDTO> vehicleResource = new Resource<VehicleDTO>(vehicleDTO);

        try {
            vehicleResource.add(linkTo(VehiclesControllerHateoas.class).slash(vehicleDTO.getId()).withSelfRel());
            vehicleResource.add(linkTo(VehiclesControllerHateoas.class).slash(vehicleDTO.getId()).withRel("DELETE"));

        } catch (Exception ex) {
            Logger.getLogger(VehicleResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resource from VehiclesControllerHateoas", ex);
        }

        return vehicleResource;
    }
}
