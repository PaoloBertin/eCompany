package it.opensource.ecompany.service;

import java.util.List;

import it.opensource.ecompany.domain.Category;

public interface CategoriesService {
    
    public List<Category> getAll();

    public Category getCategoryById(Long id);

    public Category getCategoryByName(String name);

    public Category saveCategory(Category category);

    public void deleteCategory(Category category);
}
