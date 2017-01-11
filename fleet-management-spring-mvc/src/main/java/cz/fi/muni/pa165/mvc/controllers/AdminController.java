package cz.fi.muni.pa165.mvc.controllers;

import cz.fi.muni.pa165.facade.EmployeeFacade;
import cz.fi.muni.pa165.facade.InspectionFacade;
import cz.fi.muni.pa165.facade.JourneyFacade;
import cz.fi.muni.pa165.facade.VehicleFacade;
import cz.fi.muni.pa165.service.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by MBalicky on 14/12/2016.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private VehicleFacade vehicleFacade;
    @Autowired
    private InspectionFacade inspectionFacade;
    @Autowired
    private JourneyFacade journeyFacade;
    @Autowired
    private EmployeeFacade employeeFacade;
    @Autowired
    private DateTimeService dateTimeService;

    private List<String> list;

    public AdminController() {
        list = new LinkedList<String>();
        list.add("vehicle");
        list.add("employee");
        list.add("journey");
        list.add("inspectionInterval");
    }

    @RequestMapping(value = "/entityListView", method = RequestMethod.GET)
    public String vehicleList(Model model) {
        model.addAttribute("entities", this.list);
        return "admin/entityListView";
    }

    @RequestMapping(value = "entityListView/selectTable", method = RequestMethod.GET)
    public String vehicleList(@RequestParam("entity") String entity, Model model) {

        switch (entity) {
            case "vehicle":
                model.addAttribute("items", this.vehicleFacade.getAllActiveVehicles());
                break;
            case "employee":
                model.addAttribute("items", this.employeeFacade.findAllEmployee());
                break;
            case "inspectionInterval":
                model.addAttribute("items", this.inspectionFacade.listAllInspectionIntervals());
                break;
            case "journey":
                model.addAttribute("items", this.journeyFacade.getAllJourneys());
                break;
        }

        model.addAttribute("entityType", entity);
        model.addAttribute("entities", this.list);

        return "admin/entityListView";
    }


    @RequestMapping(value = "/obsolete-vehicles", method = RequestMethod.GET)
    public String listObsoleteVehicles(
        Model model
    ) {
        model.addAttribute("vehicles", vehicleFacade.getObsoleteVehicles());
        return "admin/obsoleteVehiclesListView";
    }

    @RequestMapping(value = "/active-journeys", method = RequestMethod.GET)
    public String activeJourneys(Model model)
    {
        model.addAttribute("journeys", journeyFacade.getAllUnfinishedJourneys());
        return "admin/activeJourneysView";
    }

    @RequestMapping(value = "/finish-journey/{journeyId}", method = RequestMethod.GET)
    public String finishJourney(
            @PathVariable("journeyId") Long journeyId,
            @RequestParam(value = "drivenDistance") float drivenDistance,
            UriComponentsBuilder uriBuilder,
            RedirectAttributes redirectAttributes
    ) {
        journeyFacade.finishJourney(journeyId, drivenDistance, dateTimeService.getCurrentDate());
        redirectAttributes.addFlashAttribute("alert_success", "Journey was finished and vehicle returned");
        return "redirect:" + uriBuilder.path("/admin/active-journeys").buildAndExpand().encode().toUriString();
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
}
