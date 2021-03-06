package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.service.CategoriesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class CategoriesServiceImplTest {

    @Autowired
    CategoriesService categoriesService;

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getAllCategoriesTest() {

        List<Category> categories = categoriesService.getAll();

        assertThat(categories.size(), equalTo(6));
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getCategoryByIdTest() {

        Category category = categoriesService.getCategoryById(1L);

        assertThat("Libri", equalTo(category.getName()));
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getCategoryByNameTest() {

        Long expected = 3L;
        Long actual = categoriesService.getCategoryByName("DVD").getId();

        assertThat(expected, equalTo(actual));
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void saveCategoryTest() {

        Category category = new Category();
        category.setName("eBooks");
        categoriesService.saveCategory(category);

        int expected = 7;
        int actual = categoriesService.getAll().size();

        assertThat(actual, equalTo(expected));
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void updateCategory() {

        Category category = categoriesService.getCategoryById(1L);
        category.setName("Books");

        categoriesService.saveCategory(category);

        int expected = 6;
        int actual = categoriesService.getAll().size();

        assertThat(expected, equalTo(actual));
    }
}
