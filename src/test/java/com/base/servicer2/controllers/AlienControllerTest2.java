package com.base.servicer2.controllers;

import com.base.servicer2.entities.Alien;
import com.base.servicer2.services.AlienService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
   JUnit Jupiter (JUnit 5).
   Instantiates only web layer rather than whole context.
   Need to mock all components.
   Add @AutoConfigureMockMvc(addFilters = false) to disable security.
*/

@WebMvcTest(controllers = {AlienController.class})
@AutoConfigureMockMvc(addFilters = false)
public class AlienControllerTest2 {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlienService alienService;

    @BeforeEach
    void setUp() {
        Assertions.assertThat(contextPath).isNotBlank();
        ((MockServletContext) mockMvc.getDispatcherServlet().getServletContext()).setContextPath(contextPath);
    }

    @Test
    public void listAliensTest() throws Exception {
        when(alienService.listAliens())
                .thenReturn(Collections.singletonList(getAlien()));

        this.mockMvc.perform(get("/api/alien/list")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Mike")))
                .andExpect(jsonPath("$", not(empty())))
                .andExpect(jsonPath("$.[0].name").value("Mike"))
                .andExpect(jsonPath("$.[0].tech").value("java"));
    }

    @Test
    public void findAlienByNameTest() throws Exception {
        when(alienService.findAlienByName(eq("Mike")))
                .thenReturn(Collections.singletonList(getAlien()));

        this.mockMvc.perform(get("/api/alien")
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

    @Test
    public void saveAlienTest() throws Exception {
        when(alienService.saveAlien(any(Alien.class)))
                .thenReturn(getAlien());

        this.mockMvc.perform(post("/api/alien")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"TestName\",\n" +
                                "    \"tech\": \"TestTech\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.tech").isNotEmpty())
                .andExpect(jsonPath("$.name", is("Mike")))
                .andExpect(jsonPath("$.tech", is("java")));
    }

    private Alien getAlien() {
        Alien alien = new Alien();
        alien.setId(1);
        alien.setName("Mike");
        alien.setTech("java");
        return alien;
    }
}

