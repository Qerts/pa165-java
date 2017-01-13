package cz.fi.muni.pa165.mvc.controllers;

import cz.fi.muni.pa165.dto.InspectionDTO;
import cz.fi.muni.pa165.dto.InspectionIntervalDTO;
import cz.fi.muni.pa165.dto.VehicleDTO;
import cz.fi.muni.pa165.facade.InspectionFacade;
import cz.fi.muni.pa165.facade.VehicleFacade;
import cz.fi.muni.pa165.service.DateTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * Created by MBalicky on 14/12/2016.
 */
@Controller
@RequestMapping("/technician")
public class TechnicianController {

    private static final String DEFAULT_WITHIN_DAYS = "25";

    final static Logger log = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private VehicleFacade vehicleFacade;
    @Autowired
    private InspectionFacade inspectionFacade;
    @Autowired
    private DateTimeService dateTimeService;


    @RequestMapping(value = "/planned-inspections")
    public String plannedInspections(
            @RequestParam(value = "withinDays", required = false, defaultValue = DEFAULT_WITHIN_DAYS) int withinDays,
            Model model
    ) {
        model.addAttribute("plannedInspectionIntervals", inspectionFacade.listPlannedInspectionIntervals(withinDays));
        model.addAttribute("withinDays", withinDays);
        return "technician/plannedInspectionsView";
    }

    @RequestMapping(value = "/inspections-history")
    public String inspectionsHistory(Model model) {
        model.addAttribute("inspections", inspectionFacade.listAllInspections());
        return "technician/inspectionsHistoryView";
    }

    @RequestMapping(value = "/vehicles-list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("vehicles", vehicleFacade.getAllActiveVehicles());
        return "technician/vehiclesListView";
    }

    @RequestMapping(value = "/vehicleAddInspectionView/{vehicleId}", method = RequestMethod.GET)
    public String addInspectionToVehicle(@PathVariable long vehicleId, Model model) {
        log.debug("new()");
        InspectionIntervalDTO inspection = new InspectionIntervalDTO();
        VehicleDTO v = vehicleFacade.findVehicleById(vehicleId);
        inspection.setVehicle(v);
        model.addAttribute("inspectionCreate", inspection);
        return "technician/vehicleAddInspectionView";
    }

    @RequestMapping(value = "/perform-inspection-now/{inspectionIntervalId}", method = RequestMethod.GET)
    public String performInspectionNow(
        @PathVariable long inspectionIntervalId,
        @RequestParam(value = "withinDays", required = false, defaultValue = DEFAULT_WITHIN_DAYS) int withinDays,
        UriComponentsBuilder uriBuilder,
        RedirectAttributes redirectAttributes
    ) {
        inspectionFacade.performInspection(inspectionIntervalId, dateTimeService.getCurrentDate());
        redirectAttributes.addFlashAttribute("alert_success", "Inspection was performed");
        return "redirect:" + uriBuilder.path("/technician/planned-inspections").queryParam("withinDays", withinDays).buildAndExpand().encode().toUriString();
    }

    // create InspectionInterval
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("inspectionCreate") InspectionIntervalDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(inspectionCreate={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "technician/vehicleAddInspectionView";
        }
        //create inspection
        inspectionFacade.addNewInspectionInterval(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "InspectionInterval " + " was created and added to vehicle");
        return "redirect:" + uriBuilder.path("/technician/vehicles-list").buildAndExpand().encode().toUriString();
    }


    @RequestMapping(value = "/vehicleDetailView/{vehicleId}", method = RequestMethod.GET)
    public String vehicleDetail(@PathVariable long vehicleId, Model model) {
        log.debug("detail()");
        VehicleDTO v = vehicleFacade.findVehicleById(vehicleId);
        double kilometrage = vehicleFacade.getTotalKilometrage(vehicleId);
        Collection<InspectionIntervalDTO> inspections = inspectionFacade.getInspectionInterval(vehicleId);

        model.addAttribute("vehicle", v);
        model.addAttribute("kilometrage", kilometrage);
        model.addAttribute("inspections", inspections);
        return "technician/vehicleDetailView";
    }

    @RequestMapping(value = "/inspectionIntervalsView", method = RequestMethod.GET)
    public String listInspections(Model model) {
        model.addAttribute("inspections", inspectionFacade.listAllInspectionIntervals());
        return "technician/inspectionIntervalsView";
    }


}
