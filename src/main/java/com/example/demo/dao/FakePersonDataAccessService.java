package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
    private static List<Person> DB = new ArrayList<>();
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 0;
    }

    @Override
    public List<Person> SelectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonByID(UUID id) {
        return DB.stream()
                .filter(person->person.getId().equals("id"))
                .findFirst();
    }

    @Override
    public int deletePersonByID(UUID id) {
        Optional<Person> personMaybe = selectPersonByID(id);
        if(personMaybe.isPresent()){
            DB.remove(personMaybe.get());
            return 1;
        }

        return 0;
    }

    @Override
    public int updatePersonByID(UUID id, Person person) {
        return 0;
/*        return selectPersonByID(id)
                .map(p->{
                    int indexOfPersonToDelete = DB.indexOf(p);
                    if(indexOfPersonToDelete>=0){
                        DB.set(indexOfPersonToDelete,person);
                        return 1;
                    }
                })
                .orElse(0);
*/    }
}
