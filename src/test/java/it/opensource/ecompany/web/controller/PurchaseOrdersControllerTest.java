package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import it.opensource.ecompany.bean.CartBean;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.hamcrest.collection.IsCollectionWithSize;

@ActiveProfiles("html")
@AutoConfigureMockMvc
@SpringBootTest
public class PurchaseOrdersControllerTest {

    @Autowired
    private CartBean cartBean;

    @Test
    public void getAllPurchaseordersTest(@Autowired MockMvc mvc) throws Exception {

        mvc
            .perform(get("/movements/all").with(user("mario.rossi")
                                                    .password("user")
                                                    .roles("USER")))
            .andExpect(model().attribute("categories", IsCollectionWithSize.hasSize(6)))
            .andExpect(model().attribute("categories", hasItem(hasProperty("name", is("Libri")))))
            .andExpect(model().attribute("movements", IsCollectionWithSize.hasSize(10)))
            .andExpect(model().attribute("movements", hasItem(hasProperty("totalamount", equalTo(169.5)))))
            .andExpect(model().attributeExists("cartBean"))
            .andExpect(model().attribute("cartBean", hasProperty("totalCost", closeTo(3.0, 0.001))))
            .andExpect(view().name("movements/list"))
            .andExpect(status().isOk());
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void getPurchaseOrderByIdTest(@Autowired MockMvc mvc) throws Exception {

        mvc
            .perform(get("/purchaseorders/{purchaseordersId}", 2L)
                         .sessionAttr("cartBean", cartBean)
                         .with(user("mario.rossi")
                                   .password("user")
                                   .roles("USER")))
            .andExpect(model().attributeExists("cartBean"))
            .andExpect(model().attribute("categories", IsCollectionWithSize.hasSize(6)))
            .andExpect(model().attribute("categories", hasItem(hasProperty("name", is("Libri")))))
            .andExpect(model().attribute("cartBean", hasProperty("totalCost", closeTo(3.0, 0.001))))
            .andExpect(model().attribute("purchaseorder", hasProperty("totalAmount", equalTo(49.90))))
            .andExpect(view().name("purchaseorders/show"))
            .andExpect(status().isOk());
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void getpurchaseordersByCustomerTest() {

    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void viewMovementsTest() {

    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void saveMovementTest(@Autowired MockMvc mvc) throws Exception {

        Category category = new Category(1L, "Books");
        Product product = new Product(1L, "Da Visual Basic a Java", "8883780450", category, 29.90F);
        CartBean cartBean = new CartBean();
        cartBean.addProductToCart(product);
        product = new Product(3L, "Java Web Services", "1449365116", category, 39.90F);
        cartBean.addProductToCart(product);

        mvc
            .perform(get("/purchaseorders/save")
                         .sessionAttr("cartBean", cartBean)
                         .with(user("mario.rossi")
                                   .password("user")
                                   .roles("USER")))
            .andExpect(status().isOk());
    }
}