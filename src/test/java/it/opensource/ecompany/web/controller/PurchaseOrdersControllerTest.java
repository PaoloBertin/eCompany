package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.Product;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@EnableWebMvc
@SpringBootTest
public class PurchaseOrdersControllerTest {

    @Autowired
    private CartBean cartBean;

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getAllPurchaseOrdersTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/purchaseorders/all").with(user("admin").password("admin")
                                                                       .roles("ADMIN")))
           .andExpect(model().attribute("categories", IsCollectionWithSize.hasSize(6)))
           .andExpect(model().attribute("categories", hasItem(hasProperty("name", is("Libri")))))
           .andExpect(model().attribute("purchaseOrders", IsCollectionWithSize.hasSize(15)))
           .andExpect(model().attribute("purchaseOrders", hasItem(hasProperty("totalAmount", equalTo(169.5)))))
           .andExpect(request().sessionAttribute("scopedTarget.cartBean", notNullValue()))
           .andExpect(request().sessionAttribute("scopedTarget.cartBean", hasProperty("totalCost", closeTo(3.0, 0.001))))
           .andExpect(view().name("purchaseorders/list"))
           .andExpect(status().isOk());
    }

    @Disabled
    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getPurchaseOrderByIdTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/purchaseorders/{purchaseordersId}", 2L).with(user("mario.rossi").password("user")
                                                                                          .roles("USER")))
           .andExpect(model().attribute("categories", IsCollectionWithSize.hasSize(6)))
           .andExpect(model().attribute("categories", hasItem(hasProperty("name", is("Libri")))))
           .andExpect(request().sessionAttribute("scopedTarget.cartBean", notNullValue()))
           .andExpect(view().name("purchaseorders/show"))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getPurchasePrdersByCustomerTest() {

    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void viewMovementsTest() {

    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void saveMovementTest(@Autowired MockMvc mvc) throws Exception {

        Category category = new Category(1L, "Books");
        Product product = new Product();
        product.setId(1L);
        product.setName("Da Visual Basic a Java");
        product.setProductCode("8883780450");
        product.setCategory(category);
        product.setPrice(new BigDecimal(29.90));

        CartBean cartBean = new CartBean();
        cartBean.addProductToCart(product);
        product.setId(3L);
        product.setName("Java Web Services");
        product.setProductCode("1449365116");
        product.setCategory(category);
        product.setPrice(new BigDecimal(39.90));

        cartBean.addProductToCart(product);

        mvc.perform(get("/purchaseorders/save").sessionAttr("cartBean", cartBean)
                                               .with(user("mario.rossi").password("user")
                                                                        .roles("USER")))
           .andExpect(status().isOk());
    }
}
