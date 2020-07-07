package it.opensource.ecompany.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.repository.ProductsRepository;
import it.opensource.ecompany.service.ProductsService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("productsService")
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAll() {

        return productsRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Product> getAllByPage(Pageable pageable) {

        return productsRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getProductsByCategory(Long categoryid) {

        return productsRepository.findByCategoryCategoryid(categoryid);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Product> getProductsByCategoryByPage(Long categoryId, Pageable pageable) {

        return productsRepository.findByCategoryCategoryid(categoryId, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProductById(Long id) {

        return productsRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getProductsByName(String searchText) {

        return productsRepository.findByName(searchText);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getProductsByNameContaining(String searchText) {

        return productsRepository.findByNameContaining(searchText);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Product> getProductsByNameContainingByPage(String searchText, Pageable pageable) {

        return productsRepository.findByNameContaining(searchText, pageable);
    }

    @Override
    public Product saveProduct(Product product) {

        return productsRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {

        productsRepository.delete(product);
    }
}
