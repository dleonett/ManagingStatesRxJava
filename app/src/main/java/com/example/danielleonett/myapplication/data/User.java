package com.example.danielleonett.myapplication.data;

import com.example.danielleonett.myapplication.ui.main.DataModel;

/**
 * Created by daniel.leonett on 16/01/2018.
 */

public class User implements DataModel {

    private Integer id;
    private String name;
    private String country;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
