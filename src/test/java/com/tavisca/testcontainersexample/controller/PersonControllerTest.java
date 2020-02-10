package com.tavisca.testcontainersexample.controller;

import com.tavisca.testcontainersexample.model.Person;
import com.tavisca.testcontainersexample.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class PersonControllerTest extends AbstractIntegrationTest {

    @Autowired
    private WebApplicationContext ctx;
//    @Mock
//    private PersonService personService;
//    @InjectMocks
//    private PersonController personController;


    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
    }

    @Test
    public void testSpringBootContext() throws Exception {
        Person person = new Person();
        person.setName("Shivam");

        System.out.print(person.toString());

        this.mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                        .andExpect(status().isOk()).andExpect(content().string("ok"));

        this.mockMvc.perform(
                get("/person")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
            ).andExpect(status().isOk());

    }

}