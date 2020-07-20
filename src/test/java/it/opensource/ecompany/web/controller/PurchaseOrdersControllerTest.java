package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.Product;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("html")
@AutoConfigureMockMvc
@SpringBootTest
public class PurchaseOrdersControllerTest {

    @Autowired
    private CartBean cartBean;

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        , "/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Test
    public void getAllPurchaseordersTest(@Autowired MockMvc mvc) throws Exception {

        mvc
            .perform(get("/purchaseorders/all").with(user("mario.rossi")
                                                         .password("user")
                                                         .roles("USER")))
            .andExpect(model().attribute("categories", IsCollectionWithSize.hasSize(6)))
            .andExpect(model().attribute("categories", hasItem(hasProperty("name", is("Libri")))))
            .andExpect(model().attribute("purchaseOrders", IsCollectionWithSize.hasSize(10)))
            .andExpect(model().attribute("purchaseOrders", hasItem(hasProperty("totalAmount", equalTo(169.5)))))
            .andExpect(model().attributeExists("cartBean"))
            .andExpect(model().attribute("cartBean", hasProperty("totalCost", closeTo(3.0, 0.001))))
            .andExpect(view().name("purchaseorders/list"))
            .andExpect(status().isOk());
    }

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        , "/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
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
            .andExpect(model().attribute("purchaseOrder", hasProperty("totalAmount", equalTo(49.90))))
            .andExpect(view().name("purchaseorders/show"))
            .andExpect(status().isOk());
    }

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        , "/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Test
    public void getpurchaseordersByCustomerTest() {

    }

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        , "/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Test
    public void viewMovementsTest() {

    }

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        , "/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Test
    public void saveMovementTest(@Autowired MockMvc mvc) throws Exception {

        Category category = new Category(1L, "Books");
        Product product = new Product(1L, "Da Visual Basic a Java", "8883780450", category, 29.90F);
        CartBean cartBean = new CartBean();
        cartBean.addProductToCart(product);
        product = new Product(3L, "Java Web Services", "1449365116", category, 39.90F);
        cartBean.addProductToCart(product);

        mvc
            .perform(get("/purchaseorders/save").sessionAttr("cartBean", cartBean)
                                                .with(user("mario.rossi").password("user").roles("USER")))
            .andExpect(status().isOk());
    }
}