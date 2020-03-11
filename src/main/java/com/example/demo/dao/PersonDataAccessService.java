package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("postgres")
public class PersonDataAccessService implements PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public List<Person> SelectAllPeople() {
            final String sql = "SELECT id, name FROM person";
            return jdbcTemplate.query(sql, (resultSet, i)  -> {
                return new Person (UUID.fromString(resultSet.getString("id")), resultSet.getString("name"));
            });
//        List<Person> xx=new ArrayList<>();
//        xx.add(new Person(UUID.randomUUID(), "From POSTGRES DB"));
//        return xx;
    }

    @Override
    public Optional<Person> selectPersonByID(UUID id) {
        final String sql = "SELECT id, name FROM person where id = ?";
        Person person = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i)  -> {
            return new Person (UUID.fromString(resultSet.getString("id")), resultSet.getString("name"));
        });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonByID(UUID id) {
        return 0;
    }

    @Override
    public int updatePersonByID(UUID id, Person person) {
        return 0;
    }
}
