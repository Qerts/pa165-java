package cz.fi.muni.pa165.mvc.controllers;

import cz.fi.muni.pa165.facade.EmployeeFacade;
import cz.fi.muni.pa165.facade.InspectionFacade;
import cz.fi.muni.pa165.facade.JourneyFacade;
import cz.fi.muni.pa165.facade.VehicleFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "entityListView/selectTable/{entity}", method = RequestMethod.GET)
    public String vehicleList(@PathVariable String entity, Model model) {

        switch (entity) {
            case "vehicle":
                model.addAttribute("items", this.vehicleFacade.getAllVehicles());
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

        model.addAttribute("tableName", entity + "Table");
        model.addAttribute("entities", this.list);

        return "admin/entityListView";
    }
}
