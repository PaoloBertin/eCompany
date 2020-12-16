package it.opensource.ecompany.bean;

import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;

@SpringBootTest
class CartBeanTest {

    private Product  product1;

    private Product  product2;

    private Category category;

    private CartBean cartBean;

    @BeforeEach
    void setUp() {

        category = new Category();
        category.setId(1L);
        category.setName("Books");

        product1 = new Product();
        product1.setId(1L);
        product1.setName("Da Visual Basic a Java");
        product1.setCategory(category);
        product1.setPrice(29.90F);

        product2 = new Product();
        product2.setId(3L);
        product2.setProductCode("1449365116");
        product2.setName("Java Web Services");
        product2.setCategory(category);
        product2.setPrice(39.90F);

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

        Double expected = 59.8; // subTotal
        Double actual = cartBean.getSubTotal();

        assertThat(expected, closeTo(actual, 0.001));
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void addProductToCart() {

        cartBean.addProductToCart(product1);
        cartBean.addProductToCart(product2);

        Double expected = 69.8; // subTotal
        Double actual = cartBean.getSubTotal();

        assertThat(expected, closeTo(actual, 0.001));
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void deleteProductToCart() {


        cartBean.addProductToCart(product1);
        cartBean.addProductToCart(product2);

        cartBean.deleteProductToCart(product1);

        Double expected = 39.9; // subTotal
        Double actual = cartBean.getSubTotal();

        assertThat(expected, closeTo(actual, 0.001));
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

        Double expected = 69.8; // totalCost
        Double actual = cartBean.getTotalCost();

        assertThat(expected, closeTo(actual, 0.001));

    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getSubTotal() {

        cartBean.addProductToCart(product1);

        Double expected = 29.90; // totalCost
        Double actual = cartBean.getTotalCost();

        assertThat(actual, closeTo(expected, 0.001));

    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getSubTotalWithExpressDelivery() {

        cartBean.addProductToCart(product1);
        cartBean.setExpressDelivery(true);

        Double expected = 34.90; // totalCost
        Double actual = cartBean.getTotalCost();

        assertThat(actual, closeTo(expected, 0.001));

    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getTotalCost() {

        cartBean.addProductToCart(product1);
        cartBean.addProductToCart(product2);
        cartBean.setExpressDelivery(true);

        Double expected = 74.8; // totalCost
        Double actual = cartBean.getTotalCost();

        assertThat(expected, closeTo(actual, 0.001));
    }
}
