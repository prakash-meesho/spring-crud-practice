package com.example.practice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Person {
    public Person(){}
    private String firstName;

    public Person(String lastName, String id, String firstName) {
        this.lastName = lastName;
        this.id = id;
        this.firstName = firstName;
    }

    private String lastName;
    @Id
    private String id;
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }




}
