package com.example.practice.services;

import com.example.practice.controllers.ExceptionHandler;
import com.example.practice.models.Person;
import com.example.practice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Primary
public class PersonServiceImpl implements PersonService{
    @Autowired
    PersonRepository personRepository;
    public Person getById(String id) throws ExceptionHandler {
        Optional<Person> person=personRepository.findById(id);
        if(person.isEmpty())throw new ExceptionHandler();
        return person.get();

    }
    public String updateById(String id,Person updatePerson) throws ExceptionHandler{
        if(personRepository.findById(id).isEmpty())throw new ExceptionHandler();
        Person person=personRepository.findById(id).get();
        person.setLastName(updatePerson.getLastName());
        person.setFirstName(updatePerson.getFirstName());
        personRepository.save(person);
        return "Person update Successful";

    }
    public String deleteById(String id) throws ExceptionHandler{
        if(personRepository.findById(id).isEmpty()){
            throw new ExceptionHandler();
        }
        personRepository.deleteById(id);
        return "The person has been removed";
    }
    public String addNewPerson(Person person){
        if(personRepository.findById(person.getId()).isPresent()){
            return "The person with this id already exists";
        }
        personRepository.save(person);
        return "Person added successfully";
    }
}
