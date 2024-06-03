package com.example.practice.controllers;

import com.example.practice.logger.Logger;
import com.example.practice.models.Person;
import com.example.practice.services.PersonService;
import com.example.practice.services.PersonServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@ResponseBody
@Slf4j
public class PersonController {


    @Autowired
    PersonServiceImpl personService;
    @Autowired
    Logger logger;
    @GetMapping("/hello-world")
    ResponseEntity<String> getHelloWorld(){
        HttpHeaders header=new HttpHeaders();
        header.add("foo","bar");
        return new ResponseEntity<String>("Hello World",header, HttpStatus.OK);
    }
    @GetMapping("/getById/{id}")
    public Person getPersonById(@PathVariable String id){
        try{
            return personService.getById(id);
        }
        catch(Exception e){
            logger.createNotFoundLog();
            return null;
        }
    }
    @PostMapping("/addNewPerson")
    public String addNewPerson(@RequestBody Person person) {
        return personService.addNewPerson(person);
    }
    @DeleteMapping("/deleteById/{id}")
    public String deletePersonById(@PathVariable String id){
        try{
            return personService.deleteById(id);
        }
        catch(Exception e){
            logger.createNotFoundLog();
            return "User with this id not found";
        }

    }
    @PatchMapping("/updateById/{id}")
    public String updatePersonById(@PathVariable String id,@RequestBody Person person){
        try {
            return personService.updateById(id,person);
        }
        catch (Exception e){
            logger.createNotFoundLog();
            return "The user with this id not found";
        }

    }
}
