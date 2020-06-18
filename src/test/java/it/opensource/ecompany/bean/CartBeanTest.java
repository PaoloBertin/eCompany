package it.opensource.ecompany.bean;

import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

class CartBeanTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void addProductToCart() {

        Category category = new Category(1L, "Books");
        Product product = new Product(1L, "Da Visual Basic a Java", "8883780450", category, 29.90F);
        CartBean cartBean = new CartBean();
        cartBean.addProductToCart(product);
        product = new Product(3L, "Java Web Services", "1449365116", category, 39.90F);
        cartBean.addProductToCart(product);
        cartBean.setExpressDelivery(true);

        Double expected = 74.8; // totalCost
        Double actual = cartBean.getTotalCost();

        assertThat(expected, closeTo(actual, 0.001));
    }

    @Test
    void deleteProductToCart() {

    }

    @Test
    void getNumberProducts() {

    }

    @Test
    public void getShippingCosts() {

    }

    @Test
    public void getSubTotal() {

    }

    @Test
    public void getTotalCost() {

        Category category = new Category(1L, "Books");
        Product product = new Product(1L, "Da Visual Basic a Java", "8883780450", category, 29.90F);
        CartBean cartBean = new CartBean();
        cartBean.addProductToCart(product);
        product = new Product(3L, "Java Web Services", "1449365116", category, 39.90F);
        cartBean.addProductToCart(product);
        cartBean.setExpressDelivery(true);

        Double expected = 74.8; // totalCost
        Double actual = cartBean.getTotalCost();

        assertThat(expected, closeTo(actual, 0.001));
    }
}