package it.opensource.ecompany.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.service.CategoriesService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoriesServiceImplTest {

    @Autowired
    CategoriesService categoriesService;

    @Test
    public void getAllCategoriesTest() {

        List<Category> categories = categoriesService.getAll();

        assertThat(categories.size()).isEqualTo(6);
    }

}
