package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Hall;
import edu.school21.cinema.services.HallService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/admin/panel/")
@Controller
public class HallController {

    @Autowired
    private HallService hallService;

    public HallController() {}

    @GetMapping("halls/new")
    public String addHalls () {
        return "halls";
    }

    @PostMapping("hall")
    public String addHalls(@Valid Hall hall, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            model.addAttribute("noErrors", true);
            hallService.save(hall);
            return "redirect:/admin/panel/halls";
        } else {
            model.addAttribute("hall", hall);
            return "halls";
        }
    }

    @GetMapping("halls")
    public String showHalls (Model model){
        List<Hall> halls= hallService.findAll();
        model.addAttribute("halls", halls);
        return "showhall";
    }


    @GetMapping("halls/update/{id}")
    public String updateHall(@PathVariable("id") Long id, Model model) {
        Hall hall = hallService.findById(id);
        model.addAttribute("hall", hall);
        model.addAttribute("id", id);
        return "updateHall";
    }

    @PostMapping("halls/update/{id}")
    public String updateHall (@Valid Hall hall, BindingResult bindingResult, Model model,
                              @PathVariable("id") Long id) {
        System.out.println("====" + hall);
        if (!bindingResult.hasErrors()) {
            model.addAttribute("noError", true);
            Hall tempHall = hallService.findById(id);
            tempHall.setName(hall.getName());
            tempHall.setNumber(hall.getNumber());
            tempHall.setAvailableSeats(hall.getAvailableSeats());
            hallService.update(tempHall);
            System.out.println("====" + tempHall);
            return "redirect:/admin/panel/halls";
        } else {
            model.addAttribute("hall", hall);
            return "updateHall";
        }
    }

    @GetMapping("halls/delete/{id}")
    public String deleteHall(@PathVariable(value = "id") Long id){
        Hall hall = hallService.findById(id);
        hallService.delete(hall);
        return "redirect:/admin/panel/halls";
    }

}