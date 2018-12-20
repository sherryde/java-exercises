package org.launchcode.cheesemvc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity // tells spring boot to store this class in a database
public class Cheese {

    @Id // a primary key id; unique
    @GeneratedValue // data layer will generate and manage
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;

    @ManyToOne
    private Category category;

    @ManyToMany(mappedBy = "cheeses")
    private List<Menu> menus;

    //private CheeseType type; // ManyToOne category

    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Cheese() { }

    public int getId() {
        return id;
    } // not setter because it shouldn't be changed outside this class

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }
}
//lc Models part 1 video w/timestamp - https://youtu.be/cRdjxG-Qbj8?t=843

/* Intellij Auto Generate
* Right click and select Generate... (Command N)
* 1. generate a Constructor
* 2. getters and setters
 */