package it.opensource.ecompany.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// @ActiveProfiles("html")
@EnableWebMvc
@AutoConfigureMockMvc
@SpringBootTest
public class WelcomeControllerTest {

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void teardown() {

    }

    @Test
    public void welcomeTest() throws Exception {

        mvc
            .perform(get("/"))
            .andExpect(model().attribute("categories", IsCollectionWithSize.hasSize(6)))
            .andExpect(view().name("welcome"))
            .andExpect(status().isOk());
    }

}
