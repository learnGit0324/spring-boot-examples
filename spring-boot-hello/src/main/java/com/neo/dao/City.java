package com.neo.dao;

import lombok.Data;

@Data
public class City {
    private int id;
    private String name;
    private String countryCode;

    public City(int id, String name, String countryCode) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
    }
}
