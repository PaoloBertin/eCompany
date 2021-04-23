package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.service.EnterprisesService;
import it.opensource.ecompany.service.ProductsService;
import it.opensource.ecompany.service.WarehouseService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductsServiceImplTest {

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private WarehouseService warehouseService;

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberProductsTest() {

        long expected = 54;
        long actual = productsService.getNumberProducts();

        assertEquals(expected, actual);
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @ParameterizedTest
    @CsvSource({
        "1, 21",
        "2, 11",
        "3, 12",
        "4,  7",
        "5,  1",
        "6,  2"
    })
    void getNumberProductsByCategory(long categoryId, long expected) {

        long actual = productsService.getNumberProductsByCategory(categoryId);

        assertEquals(expected, actual);
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void equality_productsTest() {

        Product product1 = productsService.getProductById(1L);
        Product product2 = productsService.getProductById(1L);

        assertThat(product2, equalTo(product1));
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getAllProductsTest() {

        List<Product> products = productsService.getAllProducts();

        int expected = 54;
        int actual = products.size();

        assertThat(actual, is(expected));
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getAllProductsByPageTest() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Order.asc("name")));

        Page<Product> page = productsService.getAllProductsByPage(pageable);

        int expected = 10;
        int actual = page.getContent()
                         .size();

        assertThat(actual, is(expected));
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getProductsByCategoryIdTest() {

        int expected = 21;
        int actual = productsService.getProductsByCategory(1L)
                                    .size();

        assertThat(actual, is(expected));
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getProductsByCategoryByPage() {

        Long categoryId = 1L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Order.asc("name")));
        Page<Product> page = productsService.getProductsByCategoryByPage(categoryId, pageable);

        int expected = 10;
        int actual = page.getContent()
                         .size();

        assertThat(actual, is(expected));
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getProductById() {

        Product product = productsService.getProductById(1L);

        String expectedName = "Da Visual Basic a Java";
        String actual = product.getName();
        assertThat(actual, equalTo(expectedName));

        String expectedCode = "8883780450";
        actual = product.getProductCode();
        assertThat(actual, equalTo(expectedCode));

        long expectedImageId = 1;
        long actualImageId = product.getImageProduct().getId();
        assertThat(actualImageId, equalTo(actualImageId));
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getProductByProductCodeTest() {

        String expected = "Da Visual Basic a Java";
        String actual = productsService.getProductByProductCode("8883780450")
                                       .getName();
        assertEquals(expected, actual);
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getProductsByNameTest() {

        String expected = "Da Visual Basic a Java";

        List<Product> products = productsService.getProductsByName(expected);

        assertThat(products.size(), equalTo(1));
        assertThat(products.get(0)
                           .getName(), equalTo("Da Visual Basic a Java"));
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getProductsByNameContainingTest() {

        String searchText = "Java";

        int expected = 5;
        int actual = productsService.getProductsByNameContaining(searchText)
                                    .size();

        assertThat(actual, equalTo(expected));
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getProductsByNameContainingByPageTest() {

        String searchText = "Java";
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Order.asc("name")));

        int expected = 5;
        int actual = productsService.getProductsByNameContainingByPage(searchText, pageable)
                                    .getContent()
                                    .size();

        assertThat(actual, equalTo(expected));
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void saveProductTest() {

        Category category = categoriesService.getCategoryByName("Libri");
        Product product = new Product();
        product.setName("Alice");
        product.setProductCode("00000");
        product.setCategory(category);

        productsService.saveProduct(product);

        int expected = 55;
        int actual = productsService.getAllProducts()
                                    .size();

        assertEquals(expected, actual);
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void updateProductTest() {

        Product product = productsService.getProductById(1L);
        product.setName("Alice");

        productsService.saveProduct(product);

        int expected = 54;
        int actual = productsService.getAllProducts()
                                    .size();
        // verifica non sia stato aggiunto prodotto
        assertThat(actual, equalTo(expected));

        // verifica che il titolo sia stato aggiornato
        assertThat(productsService.getProductById(1L)
                                  .getName(), equalTo("Alice"));
    }

    @Disabled
    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void deleteProductTest() {

        //        Product product = productsService.getProductById(1L);
        //        Warehouse warehouse = warehouseService.getByProduct(product);
        //        warehouseService.deleteWarehose(warehouse);
        //        Long expected = 53L;
        //        Long actual = warehouseService.getNumberWarehouse();

        //        assertThat(actual, equalTo(expected));

        //        productsService.deleteProduct(product);
    }

}
