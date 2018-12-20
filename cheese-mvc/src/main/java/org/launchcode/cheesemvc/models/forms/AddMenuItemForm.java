package org.launchcode.cheesemvc.models.forms;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.Menu;

import javax.validation.constraints.NotNull;


public class AddMenuItemForm {

    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

    private Iterable<Cheese> cheeses;

    private Menu menu;

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }

    public int getMenuId() { return menuId; }

    public void setMenuId(int menuId) { this.menuId = menuId; }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) { this.cheeseId = cheeseId; }

    public Iterable<Cheese> getCheese() { return cheeses; }

    public Menu getMenu() {
        return menu;
    }

    public AddMenuItemForm() {
    }

    public AddMenuItemForm(Menu menu, Iterable<Cheese> cheeses) {
        this.menu = menu;
        this.cheeses = cheeses;
    }
}