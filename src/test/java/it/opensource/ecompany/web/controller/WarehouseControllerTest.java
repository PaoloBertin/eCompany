package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.domain.Warehouse;
import it.opensource.ecompany.service.WarehouseService;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

@AutoConfigureMockMvc
@EnableWebMvc
@SpringBootTest
class WarehouseControllerTest {

    @Autowired
    private WarehouseService warehouseService;

    @Test
    public void viewAllItempByPageTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/warehouse").with(user("admin.ecompany").password("admin").roles("ADMIN")))
           .andExpect(view().name("warehouse/list"))
           .andExpect(status().isOk());
    }

    @Disabled
    @Test
    public void searchProductTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/warehouse/searchProduct").param("textToSearch", "Java")
                                                         .with(user("admin.ecompany").password("admin").roles("ADMIN")))
           .andExpect(view().name("warehouse/list"))
           .andExpect(status().isOk());
    }

    @Test
    public void getBySkuTest() {

        String actual = warehouseService.getWarehouseBySku("8883780450").getUnit();
        String expected = "pz";

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void getAllReduced() {

        List<Object[]> actual = warehouseService.getAllReduced();

        assertThat(actual.size(), equalTo(54));
    }
}