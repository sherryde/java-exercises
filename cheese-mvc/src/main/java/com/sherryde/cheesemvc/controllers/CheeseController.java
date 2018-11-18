package com.sherryde.cheesemvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * Created by Sherryde for LaunchCode 101
 */

@Controller
@RequestMapping("cheese") // ever handler in this class will be preceded by this prefix for routing to lineup
public class CheeseController {

    // ArrayList placed above the handler when adding input from form.
    // static member- ArrayList cheese only exist while app is running
    static ArrayList<String> cheeses = new ArrayList<>();

    // Request path: /cheese
    @RequestMapping(value = "")
    // add a title message/attribute in the template/model
    public String index(Model model) {

        //models pass data into the view
        model.addAttribute("cheeses", cheeses);
        //This model will pass the String My Cheese
        model.addAttribute("title", "My Cheese");
        return "cheese/index"; // to match the global routing handler /cheese
    }

    // handler to display the form on the same page
    // Request path: form cheese/add GET
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");

        return "cheese/add";
    }

    // Request path: cheese/add POST
    @RequestMapping(value = "add", method = RequestMethod.POST)
    // handler expects to be passed a parameter that is a String/label - cheeseName
    // @RequestParam annotation says ==> Spring should look for a Request Parameter
    // with the same name (String/label cheeseName) as the Method parameter.
    // If it finds it it should insert it into this Method call
    public String processAddCheeseForm(@RequestParam String cheeseName) {
        cheeses.add(cheeseName);

        // Redirect to /cheese
        return "redirect:";

    }

}
