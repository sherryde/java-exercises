package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Category;
import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.data.CategoryDao;
import org.launchcode.cheesemvc.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

/**
 * Created by LaunchCode
 */

@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    private CategoryDao categoryDao;

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    // handler to display the add form
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/add";
    }

    // handler to process the add form
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese,
                                       Errors errors, @RequestParam int categoryId, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/add";
        }

        Category cat = categoryDao.findOne(categoryId);
        newCheese.setCategory(cat);
        cheeseDao.save(newCheese);
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
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    // handler to process the remove form
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            cheeseDao.delete(cheeseId);
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
// findOne : https://docs.spring.io/spring-data/data-graph/docs/1.1.0.RELEASE/api/org/springframework/data/neo4j/repository/CRUDRepository.html
// https://stackoverflow.com/questions/36613270/cant-use-method-findone-in-spring-boot#36614972