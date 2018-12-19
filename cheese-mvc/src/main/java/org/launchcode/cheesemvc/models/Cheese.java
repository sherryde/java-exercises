package org.launchcode.cheesemvc.models;

public class Cheese {
    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
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

    private String name;
    private String description;

}
//lc Models part 1 video w/timestamp - https://youtu.be/cRdjxG-Qbj8?t=843
/* Intellij Auto Generate
* Right click and select Generate... (Command N)
* 1. generate a Constructor
* 2. getters and setters
 */