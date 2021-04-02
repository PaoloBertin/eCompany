package it.opensource.ecompany.bean;

import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class CartBeanTest {

    private Product  product1;

    private Product  product2;

    private Category category;

    private CartBean cartBean;

    private BigDecimal error = new BigDecimal("0.001");

    @BeforeEach
    void setUp() {

        category = new Category();
        category.setId(1L);
        category.setName("Books");

        product1 = new Product();
        product1.setId(1L);
        product1.setName("Da Visual Basic a Java");
        product1.setCategory(category);
        product1.setPrice(new BigDecimal(29.90));

        product2 = new Product();
        product2.setId(3L);
        product2.setProductCode("1449365116");
        product2.setName("Java Web Services");
        product2.setCategory(category);
        product2.setPrice(new BigDecimal(39.90));

        cartBean = new CartBean();
    }

    @AfterEach
    void tearDown() {

    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void addedTheSameProducts() {

        cartBean.addProductToCart(product1);
        cartBean.addProductToCart(product1);

        BigDecimal expected = new BigDecimal(59.8); // subTotal
        BigDecimal actual = cartBean.getSubTotal();

        assertThat(expected, is(closeTo(actual, error)));
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void addProductToCart() {

        cartBean.addProductToCart(product1);
        cartBean.addProductToCart(product2);

        BigDecimal expected = new BigDecimal(69.8); // subTotal
        BigDecimal actual = cartBean.getSubTotal();

        assertThat(expected, closeTo(actual, error));
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void deleteProductToCart() {


        cartBean.addProductToCart(product1);
        cartBean.addProductToCart(product2);

        cartBean.deleteProductToCart(product1);

        BigDecimal expected = new BigDecimal(39.9); // subTotal
        BigDecimal actual = cartBean.getSubTotal();

        assertThat(expected, closeTo(actual, error));
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberProducts() {

        cartBean.addProductToCart(product1);
        cartBean.addProductToCart(product2);

        int expected = 2;
        int actual = cartBean.getNumberProducts();

        assertThat(expected, is(equalTo(actual)));

    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getShippingCosts() {

        cartBean.addProductToCart(product1);
        cartBean.addProductToCart(product2);
        cartBean.setExpressDelivery(false);

        BigDecimal expected = new BigDecimal(69.8); // totalCost
        BigDecimal actual = cartBean.getTotalCost();

        assertThat(expected, closeTo(actual, error));

    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getSubTotal() {

        cartBean.addProductToCart(product1);

        BigDecimal expected = new BigDecimal(29.90); // totalCost
        BigDecimal actual = cartBean.getTotalCost();

        assertThat(actual, closeTo(expected, error));

    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getSubTotalWithExpressDelivery() {

        cartBean.addProductToCart(product1);
        cartBean.setExpressDelivery(true);

        BigDecimal expected = new BigDecimal(34.90); // totalCost
        BigDecimal actual = cartBean.getTotalCost();

        assertThat(actual, closeTo(expected, error));

    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getTotalCost() {

        cartBean.addProductToCart(product1);
        cartBean.addProductToCart(product2);
        cartBean.setExpressDelivery(true);

        BigDecimal expected = new BigDecimal(74.8); // totalCost
        BigDecimal actual = cartBean.getTotalCost();

        assertThat(expected, closeTo(actual, error));
    }
}
