package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.service.CategoriesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoriesServiceImplTest {

    @Autowired
    CategoriesService categoriesService;

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        ,"/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Test
    public void getAllCategoriesTest() {

        List<Category> categories = categoriesService.getAll();

        assertThat(categories.size(), equalTo(6));
    }

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        ,"/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Test
    public void getCategoryByIdTest() {

        Category category = categoriesService.getCategoryById(1L);

        assertThat("Libri", equalTo(category.getName()));
    }

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        ,"/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Test
    public void getCategoryByNameTest() {

        Long expected = 3L;
        Long actual = categoriesService.getCategoryByName("DVD").getCategoryid();

        assertThat(expected, equalTo(actual));
    }

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        ,"/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
    @Test
    public void saveCategoryTest() {

        Category category = new Category();
        category.setName("eBooks");
        categoriesService.saveCategory(category);

        int expected = 7;
        int actual = categoriesService.getAll().size();

        assertThat(actual, equalTo(expected));
    }

    @Sql({"/db/init.sql", "/db/schema-ecompany.sql", "/db/schema-users.sql", "/db/schema-groups.sql", "/db/data-groups.sql"
        ,"/db/data-ecompany.sql", "/db/data-users.sql", "/db/data-authorities.sql", "/db/data-groups.sql"})
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
