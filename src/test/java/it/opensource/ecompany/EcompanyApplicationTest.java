package it.opensource.ecompany;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.EnabledIf;

@SpringBootTest
public class EcompanyApplicationTest {

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Test
    public void contextLoadsH2() throws Exception {

    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Test
    public void contextLoadsMySQL() throws Exception {

    }

}