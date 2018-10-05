package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.FlightService;
import com.apap.tutorial4.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private PilotService pilotService;

    @RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
    private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
        FlightModel flight = new FlightModel();
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        flight.setPilot(pilot);
        model.addAttribute("flight", flight);
        return "addFlight";
    }

    @RequestMapping(value = "/flight/add", method = RequestMethod.POST)
    private String addFlightSumbit(@ModelAttribute FlightModel flight) {
        flightService.addFlight(flight);
        return "add";
    }

    @RequestMapping(value = "/flight/delete/{flightId}", method = RequestMethod.GET)
    private String deleteFlight(@PathVariable(value = "flightId") long flightId) {
        flightService.deleteFlight(flightId);
        return "deleted";
    }

    @RequestMapping(value = "/flight/update/{flightId}", method = RequestMethod.POST)
    private String updateFlight(@PathVariable(value = "flightId") long flightId, @ModelAttribute FlightModel flightModel) {
        flightModel.setId(flightId);
        flightService.addFlight(flightModel);

        return "updated";
    }

    @RequestMapping(value = "/flight/update/{flightId}", method = RequestMethod.GET)
    private String updateFlight(@PathVariable(value = "flightId") long flightId, Model model) {
        model.addAttribute("flight", flightService.findById(flightId));
        return "update-flight";
    }

    @RequestMapping(value = "/flight/all", method = RequestMethod.GET)
    private String viewFlight(Model model) {
        model.addAttribute("flights", flightService.findAll());
        return "list-flight";
    }

}
