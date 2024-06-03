package com.example.practice.services;

import com.example.practice.models.Person;

public interface PersonService{
    public Person getById(String id);
    public String updateById(String id,Person person);
    public String deleteById(String id);
    public String addNewPerson(Person person);
}
