package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.Product;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class PurchaseOrdersControllerTest {

    @Autowired
    private CartBean cartBean;

    @Autowired
    MockMvc mvc;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private WebApplicationContext webApplicationContext;

//    protected MockHttpServletRequest request;

    @BeforeEach
    public void setup() {

        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                                  .addFilters(springSecurityFilterChain)
                                  .build();
    }

    // TODO
    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
//    @WithMockUser(username = "admin", password = "admin", authorities = {"USER", "ADMIN"})
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getAllPurchaseOrdersTest() throws Exception {

        mvc.perform(get("/admin/purchaseorders/all").with(user("admin").password("admin")
                                                                       .roles("ADMIN")))
           .andExpect(model().attribute("categories", IsCollectionWithSize.hasSize(6)))
           .andExpect(model().attribute("categories", hasItem(hasProperty("name", is("Libri")))))
           .andExpect(model().attribute("purchaseOrders", IsCollectionWithSize.hasSize(15)))
           .andExpect(model().attribute("cartBean", notNullValue()))
           .andExpect(view().name("purchaseorders/list"))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getPurchaseOrderByIdTest() throws Exception {

        mvc.perform(get("/admin/purchaseorders/{purchaseordersId}", 1L).with(user("mario.rossi").password("user")
                                                                                                .roles("USER")))
           .andExpect(model().attribute("categories", IsCollectionWithSize.hasSize(6)))
           .andExpect(model().attribute("categories", hasItem(hasProperty("name", is("Libri")))))
           .andExpect(model().attribute("cartBean", notNullValue()))
           .andExpect(model().attribute("purchaseOrder", hasProperty("id", is(1L))))
           .andExpect(model().attribute("purchaseOrder", hasProperty("lineItemPurchaseOrders")))
           .andExpect(model().attribute("purchaseOrder", hasProperty("lineItemPurchaseOrders", IsCollectionWithSize.hasSize(3))))
           .andExpect(model().attribute("purchaseOrder", hasProperty("lineItemPurchaseOrders", hasItem(hasProperty("price", is(new BigDecimal("20.0000")))))))
//           .andExpect(model().attribute("purchaseOrder", hasProperty("datePurchase", is("2018-10-10 00:00:00.0"))))
           .andExpect(view().name("purchaseorders/show"))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getPurchasePrdersByCustomerIdTest() throws Exception {

        mvc.perform(get("/admin/purchaseorders/all/customers/{customerId}", 2L).with(user("mario.rossi").password("user")
                                                                                                        .roles("USER")))
           .andExpect(model().attribute("categories", IsCollectionWithSize.hasSize(6)))
           .andExpect(model().attribute("cartBean", notNullValue()))
           .andExpect(model().attribute("purchaseOrders", IsCollectionWithSize.hasSize(4)))
           .andExpect(view().name("purchaseorders/list"))
           .andExpect(status().isOk());
    }

    @Disabled
    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void viewPurchaseOrdersCheckout() {

        fail(); // TODO
    }

    @Disabled
    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void savePurchaseOrderTest(@Autowired MockMvc mvc) throws Exception {

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

//        mvc.perform(get("/admin/purchaseorders/save").sessionAttr("cartBean", cartBean)
        mvc.perform(get("/admin/purchaseorders/save").with(user("mario.rossi").password("user")
                                                                              .roles("USER")))
           .andExpect(status().isOk());
    }
}
