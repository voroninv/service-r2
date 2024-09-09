package com.base.servicer2.controllers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
   JUnit Jupiter (JUnit 5).
   Spring application context is started but without the server.
   All beans instantiated.
   Integration tests.
*/

@SpringBootTest
@AutoConfigureMockMvc
public class AlienControllerTest {

    @Value("${auth.token}")
    private String token;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        Assertions.assertThat(contextPath).isNotBlank();
        ((MockServletContext) mockMvc.getDispatcherServlet().getServletContext()).setContextPath(contextPath);
    }

    @Test
    public void listAliensTest() throws Exception {
        this.mockMvc.perform(get("/api/aliens")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-KEY", token)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Mike")))
                .andExpect(jsonPath("$", not(empty())))
                .andExpect(jsonPath("$", hasSize(greaterThan(6))))
                .andExpect(jsonPath("$.[0].name").value("Mike"))
                .andExpect(jsonPath("$.[0].tech").value("java"));
    }

    @Test
    public void findAlienByNameTest() throws Exception {
        this.mockMvc.perform(get("/api/aliens")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-KEY", token)
                        .param("name", "Mike")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Mike")));
    }

    @Test
    public void saveAndDeleteAlienTest() throws Exception {
        String result = this.mockMvc.perform(post("/api/aliens")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-KEY", token)
                        .content("{\n" +
                                "    \"name\": \"TestName\",\n" +
                                "    \"tech\": \"TestTech\"\n" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        String id = getId(result);
        this.mockMvc.perform(delete("/api/aliens/" + id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-KEY", token)
                        .param("id", id))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(id));
    }

    private static String getId(String response) {
        String[] couple = response.split(",");
        String[] items = couple[0].split(":");
        return items[1];
    }
}

