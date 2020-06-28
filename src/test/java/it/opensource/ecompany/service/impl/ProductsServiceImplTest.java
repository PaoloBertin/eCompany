package it.opensource.ecompany.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.context.jdbc.Sql;

import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.service.ProductsService;

@SpringBootTest
public class ProductsServiceImplTest {

    @Autowired
    private ProductsService productsService;

    @Sql({ "/schema-h2.sql", "/data-h2.sql" })
    @Test
    public void getAllProductsTest() {

        int expected = 54;
        int actual = productsService.getAll().size();

        assertThat(actual, is(expected));
    }

    @Sql({ "/schema-h2.sql", "/data-h2.sql" })
    @Test
    public void getAllProductsByPageTest(){

       Pageable pageable = PageRequest.of(0, 10, Sort.by(Order.asc("name")));
       
       Page<Product> page= productsService.getAllByPage(pageable);

        int expected = 10;
        int actual =page.getContent().size();
        assertThat(actual, is(expected));
    }

    @Sql({ "/schema-h2.sql", "/data-h2.sql" })
    @Test
    public void getProductsByCategoryIdTest() {

        int expected = 21;
        int actual   = productsService.getProductsByCategory(1L).size();

        assertThat(actual, is(expected));
    }

    @Sql({ "/schema-h2.sql", "/data-h2.sql" })
    @Test
    public void getProductsByCategoryByPage() {

        Long categoryId = 1L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Order.asc("name")));
        Page<Product> page = productsService.getProductsByCategoryByPage(categoryId, pageable);
        
        int expected = 10;
        int actual = page.getContent().size();
        assertThat(actual, is(expected));        
    }

    @Sql({ "/schema-h2.sql", "/data-h2.sql" })
    @Test
    public void getProductById(){

        Product product = productsService.getProductById(1L);

        String expected = "Da Visual Basic a Java";
        String actual = product.getName();
        assertThat(actual, equalTo(expected));
    }

    @Sql({ "/schema-h2.sql", "/data-h2.sql" })
    @Test
    public void getProductsByName(){

        String expected = "Da Visual Basic a Java";

        List<Product> products = productsService.getProductsByName(expected);
        
        assertThat(products.size(), equalTo(1));
        assertThat(products.get(0).getName(), equalTo("Da Visual Basic a Java"));
    }
}
