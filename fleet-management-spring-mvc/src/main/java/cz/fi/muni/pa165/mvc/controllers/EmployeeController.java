package cz.fi.muni.pa165.mvc.controllers;

import cz.fi.muni.pa165.dto.JourneyDTO;
import cz.fi.muni.pa165.facade.JourneyFacade;
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
import java.util.Date;


/**
 * Created by MBalicky on 14/12/2016.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    final static Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private VehicleFacade vehicleFacade;
    @Autowired
    private JourneyFacade journeyFacade;

    @RequestMapping(value = "/vehicleListView/{id}", method = RequestMethod.GET)
    public String vehicleList(@PathVariable long id, Model model){
        model.addAttribute("vehicles", this.vehicleFacade.findVehiclesAvailable(id));
        return "employee/vehicleListView";
    }

    @RequestMapping(value = "/journeyListView/{id}", method = RequestMethod.GET)
    public String jorneyList(@PathVariable long id, Model model){
        model.addAttribute("journeys", this.journeyFacade.getJourneysByEmployee(id));
        return "employee/journeyListView";
    }

    @RequestMapping(value = "/vehicleAddJourneyView/{id}", method = RequestMethod.GET)
    public String addJourneyToVehicle(Model model) {
        log.debug("new()");
        model.addAttribute("journeyCreate", new JourneyDTO());
        return "employee/vehicleAddJourneyView";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("journeyCreate") JourneyDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(journeyCreate={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "employee/vehicleAddJourneyView";
        }
        //create journey
        this.journeyFacade.addJourney(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Journey " + " was created and added to vehicle and employee");
        return "redirect:" + uriBuilder.path("/employee/journeyListView").buildAndExpand().encode().toUriString();
    }
}
