package org.launchcode.cheesemvc.models;

public class Cheese {

    private String name;
    private String description;
    private int cheeseId; // unique for each cheese
    private static int nextId = 1;

    public Cheese(String name, String description) {
        this(); //call the default constructor for the given class.
        this.name = name;
        this.description = description;
    }

    public Cheese() {
        cheeseId = nextId;
        nextId++;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

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

}
//lc Models part 1 video w/timestamp - https://youtu.be/cRdjxG-Qbj8?t=843

/* Intellij Auto Generate
* Right click and select Generate... (Command N)
* 1. generate a Constructor
* 2. getters and setters
 */