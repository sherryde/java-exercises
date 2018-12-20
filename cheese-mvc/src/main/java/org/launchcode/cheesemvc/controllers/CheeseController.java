package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    // handler to display the add form
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    // handler to process the add form
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute Cheese newCheese) {
        CheeseData.add(newCheese);
        return "redirect:";
        // Spring Boot Binds model class to request handler with the annotation @ModelAttribute
        /* Creates a new object - Cheese newCheese = new Cheese(); (call the default constructor)
         * Goes to the request and looks for a param of the field name -  newCheese.setName(Request.getParameter("name"));
         * set description - newCheese.setDescription(Request.getParameter("description")
         */
        // the field name and the post form name need to match up
    }

    // handler to display the remove form
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    // handler to process the remove form
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int CheeseId : cheeseIds) {
            CheeseData.remove(CheeseId);
        }

        return "redirect:";
    }

}
// Thymeleaf cheat sheet: https://github.com/LaunchCodeEducation/cheatsheets/tree/master/thymeleaf
/*
* Good practice to use a RequestMapping annotation on each individual handler and controller
* Each controller corresponds to a specific base URL segment
* Good practice to organize templates in separate files as well.
 */