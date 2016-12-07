package cz.fi.muni.pa165.mvc.controllers;

import cz.fi.muni.pa165.dto.VehicleCategoryDTO;
import cz.fi.muni.pa165.dto.VehicleDTO;
import cz.fi.muni.pa165.entity.VehicleCategory;
import cz.fi.muni.pa165.facade.VehicleFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Martin Schmidt
 */
@Controller
@RequestMapping("/vehicle")
public class VehicleController {

    final static Logger log = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private VehicleFacade vehicleFacade;



    /**
     * Shows a list of vehicles with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("vehicles", vehicleFacade.getAllVehicles());
        return "vehicle/list";
    }

    @RequestMapping(value = "/disable/{id}", method = RequestMethod.POST)
    public String disable(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        VehicleDTO vehicle = vehicleFacade.findVehicleById(id);
        vehicleFacade.disableVehicle(id);
        log.debug("disable({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Vehicle \"" + vehicle.getType() + "\" was disabled.");
        return "redirect:" + uriBuilder.path("/vehicle/list").toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("vehicle", vehicleFacade.findVehicleById(id));
        return "vehicle/view";
    }

    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newVehicle(Model model) {
        log.debug("new()");
        model.addAttribute("vehicleCreate", new VehicleDTO());
        return "vehicle/new";
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("vehicleCreate") VehicleDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(vehicleCreate={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "vehicle/new";
        }
        //create vehicle
        Long id = vehicleFacade.addNewVehicle(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Vehicle " + id + " was created");
        return "redirect:" + uriBuilder.path("/vehicle/view/{id}").buildAndExpand(id).encode().toUriString();
    }
}
