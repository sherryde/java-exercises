package org.launchcode.cheesemvc.models;

import org.hibernate.mapping.OneToMany;
// import org.hibernate.mapping.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private int Id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @javax.persistence.OneToMany
    @JoinColumn(name = "category_id")
    private List<Cheese> cheeses = new ArrayList<>();

    public Category() { }

    public Category(String name){
        this();
        this.name = name;
    }

    public int getId(){ return Id; }

    public String getName(){ return name; }

    public void setName(String name){
        this.name = name;
    }
}