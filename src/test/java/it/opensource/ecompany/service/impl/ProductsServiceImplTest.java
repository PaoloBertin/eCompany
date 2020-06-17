package it.opensource.ecompany.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.opensource.ecompany.service.ProductsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsServiceImplTest {

    @Autowired
    private ProductsService productsService;

    @Test
    public void getAllProductsTest() {

        int expected = 54;
        int actual = productsService.getAll().size();

        assertThat(actual, is(expected));
    }

    @Test
    public void getProductsByCategoryId() {

        int expected = 21;
        int actual   = productsService.getProductsByCategory(1L).size();

        assertThat(actual, is(expected));

    }
}
