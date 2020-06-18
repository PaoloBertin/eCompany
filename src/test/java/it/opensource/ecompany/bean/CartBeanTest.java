package it.opensource.ecompany.bean;

import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;

class CartBeanTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void addedTheSameProducts() {

        Category category = new Category(1L, "Books");
        Product product = new Product(1L, "Da Visual Basic a Java", "8883780450", category, 29.90F);
        CartBean cartBean = new CartBean();
        cartBean.addProductToCart(product);
        cartBean.addProductToCart(product);

        Double expected = 59.8; // subTotal
        Double actual = cartBean.getSubTotal();

        assertThat(expected, closeTo(actual, 0.001));
    }

    @Test
    void addProductToCart() {

        Category category = new Category(1L, "Books");
        Product product = new Product(1L, "Da Visual Basic a Java", "8883780450", category, 29.90F);
        CartBean cartBean = new CartBean();
        cartBean.addProductToCart(product);
        product = new Product(3L, "Java Web Services", "1449365116", category, 39.90F);
        cartBean.addProductToCart(product);

        Double expected = 69.8; // subTotal
        Double actual = cartBean.getSubTotal();

        assertThat(expected, closeTo(actual, 0.001));
    }

    @Test
    void deleteProductToCart() {

        Category category = new Category(1L, "Books");
        Product product1 = new Product(1L, "Da Visual Basic a Java", "8883780450", category, 29.90F);
        CartBean cartBean = new CartBean();
        cartBean.addProductToCart(product1);
        Product product2 = new Product(3L, "Java Web Services", "1449365116", category, 39.90F);
        cartBean.addProductToCart(product2);

        cartBean.deleteProductToCart(product1);

        Double expected = 39.9; // subTotal
        Double actual = cartBean.getSubTotal();

        assertThat(expected, closeTo(actual, 0.001));
    }

    @Test
    void getNumberProducts() {

        Category category = new Category(1L, "Books");
        Product product1 = new Product(1L, "Da Visual Basic a Java", "8883780450", category, 29.90F);
        CartBean cartBean = new CartBean();
        cartBean.addProductToCart(product1);
        Product product2 = new Product(3L, "Java Web Services", "1449365116", category, 39.90F);
        cartBean.addProductToCart(product2);

        int expected = 2;
        int actual = cartBean.getNumberProducts();

        assertThat(expected, is(equalTo(actual)));

    }

    @Test
    public void getShippingCosts() {

        Category category = new Category(1L, "Books");
        Product product = new Product(1L, "Da Visual Basic a Java", "8883780450", category, 29.90F);
        CartBean cartBean = new CartBean();
        cartBean.addProductToCart(product);
        product = new Product(3L, "Java Web Services", "1449365116", category, 39.90F);
        cartBean.addProductToCart(product);
        cartBean.setExpressDelivery(false);

        Double expected = 69.8; // totalCost
        Double actual = cartBean.getTotalCost();

        assertThat(expected, closeTo(actual, 0.001));

    }

    @Test
    public void getSubTotal() {

        Category category = new Category(1L, "Books");
        Product product = new Product(1L, "La matematica degli dèi e gli algoritmi degli uomini", "8883780450", category, 7.99F);
        CartBean cartBean = new CartBean();
        cartBean.addProductToCart(product);

        Double expected = 10.99; // totalCost
        Double actual = cartBean.getTotalCost();

        assertThat(actual, closeTo(expected, 0.001));

    }

    @Test
    public void getSubTotalWithExpressDelivery() {

        Category category = new Category(1L, "Books");
        Product product = new Product(1L, "La matematica degli dèi e gli algoritmi degli uomini", "8883780450", category, 7.99F);
        CartBean cartBean = new CartBean();
        cartBean.addProductToCart(product);
        cartBean.setExpressDelivery(true);

        Double expected = 12.99; // totalCost
        Double actual = cartBean.getTotalCost();

        assertThat(actual, closeTo(expected, 0.001));

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