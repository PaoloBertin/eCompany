package it.opensource.ecompany.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.repository.ProductsRepository;
import it.opensource.ecompany.service.ProductsService;

@Service("productsService")
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsRepository;
    
    @Override
    public List<Product> getAll() {

        return productsRepository.findAll();
    }

    @Override
    public Page<Product> getAllByPage(Pageable pageable) {

        return productsRepository.findAll(pageable);
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryid) {

        return productsRepository.findByCategoryCategoryid(categoryid);
    }

    @Override
    public Page<Product> getProductsByCategoryByPage(Long categoryId, Pageable pageable) {

        return productsRepository.findByCategoryCategoryid(categoryId, pageable);
    }

    public Product getProductById(Long id) {

        return productsRepository.findById(id).get();
    }

    @Override
    public List<Product> getProductsByName(String searchText) {

        return productsRepository.findByName(searchText);
    }

    @Override
    public List<Product> getProductsByNameContaining(String searchText) {

        return productsRepository.findByNameContaining(searchText);
    }

    @Override
    public Page<Product> getProductsByNameContainingByPage(String searchText, Pageable pageable) {

        return productsRepository.findByNameContaining(searchText, pageable);
    }

    @Override
    public Product save(Product product) {

        return productsRepository.save(product);
    }
}
