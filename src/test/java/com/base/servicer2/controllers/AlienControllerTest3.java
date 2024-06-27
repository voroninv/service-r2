package com.base.servicer2.controllers;

import com.base.servicer2.entities.Alien;
import com.base.servicer2.services.IAlienService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
   JUnit 4.
   Instantiates only web layer rather than whole context.
   Need to mock all components.
*/

@RunWith(MockitoJUnitRunner.class)
public class AlienControllerTest3 {

    private MockMvc mockMvc;

    @InjectMocks
    private AlienController alienController;

    @Mock
    private IAlienService alienService;

    @Before
    public void setUp() {
//        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(alienController).build();
    }

    @Test
    public void findAlienByNameTest() throws Exception {
        when(alienService.findAlienByName("Mike"))
                .thenReturn(Collections.singletonList(getAlien()));

        this.mockMvc.perform(get("/api/alien/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("search", "Mike")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("\"name\":\"Mike\",\"tech\":\"java\"")))
                .andExpect(jsonPath("$.[0].name").isNotEmpty())
                .andExpect(jsonPath("$.[0].tech").isNotEmpty())
                .andExpect(jsonPath("$.[0].name", is("Mike")))
                .andExpect(jsonPath("$.[0].tech", is("java")));
    }

    private Alien getAlien() {
        Alien alien = new Alien();
        alien.setId(1);
        alien.setName("Mike");
        alien.setTech("java");
        return alien;
    }

}

