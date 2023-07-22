package com.neo.dao;

import com.neo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcCityTemplate implements CityRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<City> findAll() {
        return jdbcTemplate.query("select * from world.city", this::mapRowToCity);
    }

    private City mapRowToCity(ResultSet resultSet, int rowNum) throws SQLException {
        return new City(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("countryCode"));
    }

    @Override
    public List<City> findById(int id) {
	return jdbcTemplate.query(String.format("select * from world.city where id = %s", id), this::mapRowToCity);
    }

    @Override
    public List<City> findByName(String name) {
        // name = '%s' as the name is String type so the '' is needed if else it would be processed as int type
        return jdbcTemplate.query(String.format("select * from world.city where name = '%s'", name), this::mapRowToCity);
    }

    @Override
    public List<City> findByFuzzyName(String name) {
        return jdbcTemplate.query("select * from world.city where name like '%" + name + "%'", this::mapRowToCity);
    }

    @Override
    public String updateById(int id, String name) {
        // Kabul --> Kabul001 for id = 1
        int update = jdbcTemplate.update("update world.city set name = ? where id = ?", name, id);
        return update == 1 ? "update success" : "no record found to update";
    }

    @Override
    public void deleteById(int id) {
        int update = jdbcTemplate.update("delete from world.city where od =%s", id);
        System.out.println("result is:" + update);
    }
}
