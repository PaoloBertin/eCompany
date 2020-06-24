package it.opensource.ecompany.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.repository.CategoriesRepository;
import it.opensource.ecompany.service.CategoriesService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("categoriesService")
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Category> getAll() {

        return categoriesRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {

        return categoriesRepository.findById(id).orElse(new Category());
    }

    @Override
    public Category saveCategory(Category category) {

        return categoriesRepository.save(category);
    }

    @Override
    public void deleteCategory(Category category) {

        categoriesRepository.delete(category);
    }

}
