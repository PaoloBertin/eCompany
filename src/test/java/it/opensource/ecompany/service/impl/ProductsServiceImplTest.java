package it.opensource.ecompany.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.context.jdbc.Sql;

import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.domain.Warehouse;
import it.opensource.ecompany.service.ProductsService;
import it.opensource.ecompany.service.WarehouseService;

@SpringBootTest
public class ProductsServiceImplTest {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private ProductsService productsService;

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        ,"/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Test
    public void equality_products(){

        Product product1 = productsService.getProductById(1L);
        Product product2 = productsService.getProductById(1L);

        assertThat(product2, equalTo(product1));
    }

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        ,"/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Test
    public void getAllProductsTest() {

        int expected = 54;
        int actual = productsService.getAll().size();

        assertThat(actual, is(expected));
    }

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        ,"/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Test
    public void getAllProductsByPageTest() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Order.asc("name")));

        Page<Product> page = productsService.getAllByPage(pageable);

        int expected = 10;
        int actual = page.getContent().size();

        assertThat(actual, is(expected));
    }

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        ,"/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Test
    public void getProductsByCategoryIdTest() {

        int expected = 21;
        int actual = productsService.getProductsByCategory(1L).size();

        assertThat(actual, is(expected));
    }

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        ,"/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Test
    public void getProductsByCategoryByPage() {

        Long categoryId = 1L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Order.asc("name")));
        Page<Product> page = productsService.getProductsByCategoryByPage(categoryId, pageable);

        int expected = 10;
        int actual = page.getContent().size();

        assertThat(actual, is(expected));
    }

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        ,"/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Test
    public void getProductById() {

        Product product = productsService.getProductById(1L);

        String expected = "Da Visual Basic a Java";
        String actual = product.getName();

        assertThat(actual, equalTo(expected));
    }

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        ,"/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Test
    public void getProductsByNameTest() {

        String expected = "Da Visual Basic a Java";

        List<Product> products = productsService.getProductsByName(expected);

        assertThat(products.size(), equalTo(1));
        assertThat(products.get(0).getName(), equalTo("Da Visual Basic a Java"));
    }

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        ,"/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Test
    public void getProductsByNameContainingTest() {

        String searchText = "Java";

        int expected = 6;
        int actual = productsService.getProductsByNameContaining(searchText).size();

        assertThat(actual, equalTo(expected));
    }

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        ,"/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Test
    public void getProductsByNameContainingByPageTest() {

        String searchText = "Java";
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Order.asc("name")));

        int expected = 6;
        int actual = productsService.getProductsByNameContainingByPage(searchText, pageable).getContent().size();

        assertThat(actual, equalTo(expected));
    }

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        ,"/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Test
    public void saveProductTest() {

        Category category = new Category();
        category.setCategoryid(1L);
        category.setName("Libri");
        Product product = new Product();
        product.setName("Alice");
        product.setIsbn("00000");
        product.setCategory(category);

        productsService.saveProduct(product);

        int expected = 55;
        int actual = productsService.getAll().size();

        assertThat(actual, equalTo(expected));
    }

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        ,"/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Test
    public void updateProductTest() {

        Product product = productsService.getProductById(1L);
        product.setName("Alice");

        productsService.saveProduct(product);

        int expected = 54;
        int actual = productsService.getAll().size();

        // verifica non sia stato aggiunto prodotto
        assertThat(actual, equalTo(expected));
        // verifica che il nuovo titolo sia stato salvato correttamente
        assertThat(productsService.getProductById(1L).getName(), equalTo("Alice"));
    }

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        ,"/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Disabled
    @Test
    public void deleteProductTest() {

        Product product = productsService.getProductById(1L);
        Warehouse warehouse = warehouseService.getByProduct(product);
        warehouseService.deleteWarehose(warehouse);
        Long expected = 53L;
        Long actual = warehouseService.getNumberWarehouse();

        assertThat(actual, equalTo(expected));

        productsService.deleteProduct(product);
    }
}
