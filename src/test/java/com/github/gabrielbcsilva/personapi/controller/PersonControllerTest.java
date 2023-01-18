package com.github.gabrielbcsilva.personapi.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gabrielbcsilva.personapi.model.Person;
import com.github.gabrielbcsilva.personapi.repository.PersonRepository;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
public class PersonControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    
    @MockBean
    PersonRepository personRepository;

    Person record1 = new Person( Long.valueOf("1"),"Gabriel",LocalDate.parse("2022-01-01"),null);
    Person record2 = new Person( Long.valueOf("2"),"Joao",LocalDate.parse("2022-02-01"),null);
    Person record3 = new Person( Long.valueOf("3"),"Jose",LocalDate.parse("2022-03-01"),null);

    @Test
    public void getAllPeopleSuccess() throws Exception {
    List<Person> records = new ArrayList<>(Arrays.asList(record1, record2,record3));
    
    Mockito.when(personRepository.findAll()).thenReturn(records);
    
    mockMvc.perform(MockMvcRequestBuilders
            .get("/person")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[2].name", is("Jose")));
}
@Test
public void getPersonByIdsuccess() throws Exception {
    Mockito.when(personRepository.findById(record1.getId())).thenReturn(java.util.Optional.of(record1));

    mockMvc.perform(MockMvcRequestBuilders
            .get("/person/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.name", is("Gabriel")));
}

@Test
public void createPersonSuccess() throws Exception {
    Person person = new Person( Long.valueOf("1"),"Gabriel",LocalDate.parse("2022-01-01"),null);

    Mockito.when(personRepository.save(person)).thenReturn(person);

    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/person")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(person));

    mockMvc.perform(mockRequest)
            .andExpect(status().isCreated());
      
    }

    @Test
public void deletePatientByIdSuccess() throws Exception {
    Mockito.when(personRepository.findById(record2.getId())).thenReturn(Optional.of(record2));

    mockMvc.perform(MockMvcRequestBuilders
            .delete("/person/2")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful());
}


}
