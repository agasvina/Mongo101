package com.lucareto.db.utils;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import com.lucareto.model.Article;
import com.lucareto.model.Person;
import com.lucareto.model.Role;
import com.lucareto.model.Status;

public class Utils {
    
    public static String generateUrn(final String NID) {
        return NID + ":" + UUID.randomUUID().toString();
    }
    
    public static Article generateRandomArticle(String sectionName, String authorId) {
        Article article = new Article.Builder(authorId, sectionName)
                              .content(generateUrn("content"))
                              .published(false)
                              .status(Status.INQUESTTION)
                              .updatedDate(new Date())
                              .build();
        return article;
    }
    
    public static String getRandomSectionName() {
        String [] sections = { "A1", "A2", "A3", "A4", "A5" };
        return sections[new Random().ints(1, 0, 4).findFirst().getAsInt()];
    }
    
    public static Person generateRandomPerson(final Role role, final String sectionName) {
        Person person = new Person();
        if(Objects.nonNull(sectionName))
            person.setSection(Optional.of(sectionName));
        else person.setSection(Optional.of(getRandomSectionName()));
        person.setUsername(generateUrn("usename"));
        person.setPassword("password");
        person.setRole(role);
        return person;
    }

}
