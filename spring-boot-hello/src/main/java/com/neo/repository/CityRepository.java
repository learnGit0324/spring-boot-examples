package com.neo.repository;

import com.neo.dao.City;

import java.util.List;

public interface CityRepository {
    List<City> findAll();
    List<City> findById(int id);
    List<City> findByName(String name);

    List<City> findByFuzzyName(String name);
    String updateById(int id, String name);
    void deleteById(int id);


    //TODO create table
    //TODO batch update

}
