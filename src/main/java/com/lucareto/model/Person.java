package com.lucareto.model;

import static com.lucareto.db.utils.Utils.generateUrn;

import java.util.Objects;
import java.util.Optional;

public class Person implements BeanConverter<Person> {

    private static final String NID = "person";
    private String id = generateUrn(NID);
        
    private String username;
    private String password;
    
    private Boolean hired;
    private Optional<String> section;
    private Role role;
    
    private BankAccount account = new BankAccount();

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getHired() {
        return hired;
    }

    public void setHired(Boolean hired) {
        this.hired = hired;
    }

    public Optional<String> getSection() {
        return section;
    }

    public void setSection(Optional<String> section) {
        this.section = section;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }


    @Override
    public Person copyPropertyTo(Person other) {
        if(this.id.equals(other.id)) {
            if(this.section.isPresent())
                other.setSection(section);
            if(Objects.nonNull(username))
                other.username = username;
            //TODO: implement better password mechanism
            if(Objects.nonNull(password)) 
                other.password = password;
            if(Objects.nonNull(hired))
                other.setHired(hired);
            if(Objects.nonNull(role)) 
                other.setRole(role);
        }
        return other;
    }    
}

