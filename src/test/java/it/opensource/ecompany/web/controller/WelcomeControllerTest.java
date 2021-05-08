package it.opensource.ecompany.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.assertj.core.api.Assertions.assertThat;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WelcomeControllerTest {

    @Autowired
    private WelcomeController welcomeController;

//    @Autowired
//    private CartBean cartBean;

//    @Autowired
//    MockMvc mvc;

//    @Autowired
//    private FilterChainProxy springSecurityFilterChain;

//    @Autowired
//    private WebApplicationContext webApplicationContext;

//    @BeforeEach
//    public void setup() {

//        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                                  .addFilters(springSecurityFilterChain)
//                                  .build();
//    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void contextLoads() throws Exception {

        assertThat(welcomeController).isNotNull();
    }

//    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
//    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
//    @Test
//    public void welcomeTest() throws Exception {

//        mvc.perform(get("/"))
//           .andExpect(model().attribute("categories", IsCollectionWithSize.hasSize(6)))
//           .andExpect(view().name("welcome"))
//           .andExpect(status().isOk());
//    }

}
