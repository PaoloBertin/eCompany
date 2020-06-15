package it.opensource.ecompany.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.repository.CategoriesRepository;
import it.opensource.ecompany.service.CategoriesService;

@Service("categoriesService")
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;
    
    @Override
    public List<Category> getAll() {

        return categoriesRepository.findAll();
    }

}
