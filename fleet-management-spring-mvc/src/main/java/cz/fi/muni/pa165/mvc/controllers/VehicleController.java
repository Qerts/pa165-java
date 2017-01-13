package cz.fi.muni.pa165.mvc.controllers;

import cz.fi.muni.pa165.dto.VehicleCategoryDTO;
import cz.fi.muni.pa165.dto.VehicleCreateDTO;
import cz.fi.muni.pa165.dto.VehicleDTO;
import cz.fi.muni.pa165.facade.VehicleFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
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
        model.addAttribute("vehicles", vehicleFacade.getAllActiveVehicles());
        return "vehicle/list";
    }

    @RequestMapping(value = "/deactivate-vehicle/{vehicleId}", method = RequestMethod.GET)
    public String deactivateVehicle(
            @PathVariable("vehicleId") Long vehicleId,
            UriComponentsBuilder uriBuilder,
            RedirectAttributes redirectAttributes
    ) {
        vehicleFacade.disableVehicle(vehicleId);
        redirectAttributes.addFlashAttribute("alert_success", "Vehicle was disabled");
        return "redirect:" + uriBuilder.path("/admin/entityListView/selectTable").queryParam("entity", "vehicle").buildAndExpand().encode().toUriString();
    }


    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("vehicle", vehicleFacade.findVehicleById(id));
        return "vehicle/view";
    }

    @ModelAttribute("vehicleCategories")
    public List<VehicleCategoryDTO> vehicleCategories() {
        log.debug("vehicleCategories()");
        List<VehicleCategoryDTO> vehicleCategories = new ArrayList(vehicleFacade.getAllVehicleCategories());
        return vehicleCategories;
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
        model.addAttribute("vehicleCreate", new VehicleCreateDTO());
        return "admin/entities/newVehicleView";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("vehicleCreate") VehicleCreateDTO formBean, BindingResult bindingResult,
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
            return "admin/entities/newVehicleView";
        }
        //create vehicle
        Long id = vehicleFacade.addNewVehicle(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Vehicle " + id + " was created");
        return "redirect:" + uriBuilder.path("/admin/entityListView/selectTable").queryParam("entity", "vehicle").buildAndExpand().encode().toUriString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String editVehicle(@PathVariable long id, Model model) {
        log.debug("update()");
        VehicleDTO v = vehicleFacade.findVehicleById(id);
        VehicleCreateDTO vc = new VehicleCreateDTO(v.getVrp(), v.getType(), v.getProductionYear(),v.getEngineType(), v.getVin(), v.getInitialKilometrage(), v.getActive());
        vc.setId(v.getId());
        model.addAttribute("vehicleEdit", vc);
        return "admin/entities/editVehicleView";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("vehicleEdit") VehicleCreateDTO formBean, BindingResult bindingResult,
                       Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("edit(vehicleEdit={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            log.debug("some error");
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);

            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "admin/entities/editVehicleView";

        }
        vehicleFacade.updateVehicle(formBean);

        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Vehicle was edited");
        return "redirect:" + uriBuilder.path("/admin/entityListView/selectTable").queryParam("entity", "vehicle").buildAndExpand().encode().toUriString();

    }


}
