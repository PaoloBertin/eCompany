package it.opensource.ecompany.web.restcontroller;

import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.service.CategoriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile("rest")
@Slf4j
@RequestMapping("/categories")
@RestController
public class CategoryResource {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {

        ResponseEntity<List<Category>> entityResponse = new ResponseEntity<>(categoriesService.getAll(), HttpStatus.OK);

        return entityResponse;
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") Long id) {

        ResponseEntity responseEntity = new ResponseEntity<Category>(categoriesService.getCategoryById(id), HttpStatus.OK);

        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {

        category = categoriesService.saveCategory(category);

        return new ResponseEntity(category, HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteCategory(@RequestBody Category category) {

        categoriesService.deleteCategory(category);
    }
}
