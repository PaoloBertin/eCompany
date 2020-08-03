package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.service.WaresService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@EnableWebMvc
@SpringBootTest
class WarehouseControllerTest {

    @Autowired
    private WaresService waresService;

    @Test
    public void viewAllWareInWarehouseByPageTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/warehouse/wares").param("page", "0")
                                                 .param("size", "10")
                                                 .param("warehouseId", "1")
                                                 .with(user("admin").password("admin")
                                                                    .roles("ADMIN")))
           .andExpect(view().name("warehouse/list"))
           .andExpect(status().isOk())
        ;
    }

    @Test
    public void searchProductTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/warehouse/{warehouseId}/wares/searchProduct", 1L).param("page", "0")
                                                                                 .param("size", "10")
                                                                                 .param("textToSearch", "Java")
                                                                                 .with(user("admin").password("admin")
                                                                                                    .roles("ADMIN")))
           .andExpect(model().attribute("productForm", hasProperty("name")))
           .andExpect(view().name("warehouse/list"))
           .andExpect(status().isOk())
        ;
    }

    @Test
    public void getBySkuTest() {

        String actual = waresService.getWareBySku("8883780450")
                                    .getUnit();
        String expected = "pz";

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void getAllReduced() {

    }
}