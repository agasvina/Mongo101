package com.lucareto.db;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lucareto.model.Person;

import spark.utils.IOUtils;

public class PersonDAO {

    private static Map<String, Person> people = new HashMap<>();
    
    //SETTING UP THE DB:
    static {
        Gson gson = new GsonBuilder().serializeNulls().create();
        StringBuilder json = new StringBuilder();
        try {
            json.append(IOUtils.toString(PersonDAO.class.getResourceAsStream("/seed/people.json")));
            List<Person> peopleJson = gson.fromJson(json.toString(), new TypeToken<List<Person>>(){}.getType());
            peopleJson.stream().forEach(person -> {
                PersonDAO.people.put(person.getId(), person);
            });
        }
        catch (IOException e) {
          e.printStackTrace();
        }
    }
    
    public Person getById(final String personId) {
      return people.get(Optional.of(personId).orElse(""));
    }
    
    public void addJournalist(Person person) {
        Optional.of(person).ifPresent(j -> people.put(j.getId(), j));
    }
    
    public void fireJournalist(final String journalistId) {
        setHire(journalistId, false);
    }
    
    public void rehireJournalist(final String journalistId) {
        setHire(journalistId, true);
    }

    private void setHire(final String personId,final boolean hired) {
        Optional.of(people.get(personId)).ifPresent(journalist -> {
            Person person = (Person) journalist;
            person.setHired(hired);
        });
    }
    
    public boolean isJournalistExist(String id) {
        return people.containsKey(id);
    };
    
    public Stream<Person> getStream() {
        return people.values().stream();
    }
    
    public void updatePerson(final Person updatedPerson) {
        Optional.of(updatedPerson)
            .filter(person -> Objects.nonNull(person.getId()))
            .ifPresent(person -> {
                Person currentPerson = people.get(updatedPerson.getId());
                people.put(updatedPerson.getId(), updatedPerson.copyPropertyTo(currentPerson));
        });;
    }
}
