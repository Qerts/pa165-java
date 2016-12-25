package cz.fi.muni.pa165.mvc.controllers;

import cz.fi.muni.pa165.dto.InspectionDTO;
import cz.fi.muni.pa165.dto.InspectionIntervalDTO;
import cz.fi.muni.pa165.dto.VehicleDTO;
import cz.fi.muni.pa165.facade.InspectionFacade;
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
import java.util.List;

/**
 * Created by MBalicky on 14/12/2016.
 */
@Controller
@RequestMapping("/technician")
public class TechnicianController {

    final static Logger log = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private VehicleFacade vehicleFacade;
    @Autowired
    private InspectionFacade inspectionFacade;

    @RequestMapping(value = "/vehicleListView", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("vehicles", vehicleFacade.getAllActiveVehicles());
        return "technician/vehicleListView";
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
        return "redirect:" + uriBuilder.path("/technician/vehicleListView").buildAndExpand().encode().toUriString();
    }


    @RequestMapping(value = "/vehicleDetailView/{vehicleId}", method = RequestMethod.GET)
    public String vehicleDetail(@PathVariable long vehicleId, Model model) {
        log.debug("detail()");
        VehicleDTO v = vehicleFacade.findVehicleById(vehicleId);
        double kilometrage = vehicleFacade.getTotalKilometrage(vehicleId);
        List<InspectionIntervalDTO> inspections = inspectionFacade.getInspectionInterval(vehicleId);

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
