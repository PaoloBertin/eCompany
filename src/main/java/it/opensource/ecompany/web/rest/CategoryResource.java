package it.opensource.ecompany.web.rest;

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
@RequestMapping("/api/categories")
@RestController
public class CategoryResource {

    @Autowired
    private CategoriesService categoriesService;

    /**
     * Ritorna elenco di tutte le categorie
     * 
     * @return entityResponse
     */
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {

        ResponseEntity<List<Category>> entityResponse = new ResponseEntity<>(categoriesService.getAll(), HttpStatus.OK);

        log.trace("ritorna lista di tutte le categorie");

        return entityResponse;
    }

    /**
     * Ritorna categoria con id dato
     * 
     * @param id identificatore categoria da recuperare dal db
     * @return responseEntity
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") Long id) {

        ResponseEntity<Category> responseEntity = new ResponseEntity<Category>(categoriesService.getCategoryById(id),
                                                                               HttpStatus.OK);

        log.trace("restituisce Category con id=" + id);

        return responseEntity;
    }

    /**
     * Rende persistente nuova/modificata categoria
     * 
     * @param category categoria da rendere persistente
     * @return responseEntity
     */
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {

        category = categoriesService.saveCategory(category);
        ResponseEntity<Category> responseEntity = new ResponseEntity<Category>(category, HttpStatus.OK);

        return responseEntity;
    }

    /**
     * Cancella categoria dal db
     * 
     * @param category categoria da eliminare
     */
    @DeleteMapping
    public void deleteCategory(@RequestBody Category category) {

        categoriesService.deleteCategory(category);
    }
}
