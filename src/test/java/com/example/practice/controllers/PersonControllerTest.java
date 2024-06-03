package com.example.practice.controllers;


import com.example.practice.logger.Logger;
import com.example.practice.models.Person;
import com.example.practice.services.PersonService;
import com.example.practice.services.PersonServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.print.attribute.standard.Media;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

//WebMVC test is used to isolate the test and target testing only the given controller class
//MockBean is used to mock a particular component as it can mock that service internally without throwing null pointer
//mockMVC is used to create the request pattern for get and post methods,that is building that entire request
//jsonPath can be used to read the json elements by using dollar sign we can access the root of json

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    Logger logger;
    @MockBean
    private PersonServiceImpl personService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getByIdNotFound() throws Exception {
        given(personService.getById("2")).willThrow(ExceptionHandler.class);

        mockMvc.perform(get("/getById/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status()
                .isOk());
    }
    @Test
    public void getPersonById() throws Exception{
        Person person=new Person("Devnani","1","Prakash");
        given(personService.getById("1")).willReturn(person);
        mockMvc.perform(get("/getById/1")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",is("1")));

    }
    @Test
    public void addNewPerson() throws Exception{
        Person person=new Person("Devnani","1","Prakash");
        given(personService.addNewPerson(any(Person.class))).willReturn("Person added Successfully");

        mockMvc.perform(post("/addNewPerson").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                        .andExpect(status().isOk());
    }
    @Test
    public void deleteById() throws Exception{
        given(personService.deleteById("1")).willReturn("The person has been removed");

        mockMvc.perform(delete("/deleteById/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    @Test
    public void updateById() throws Exception{
        Person person=new Person("Devnani","1","Prakash");
        given(personService.updateById("1",person)).willReturn("Person update successful");
        mockMvc.perform(patch("/updateById/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk());

    }

}