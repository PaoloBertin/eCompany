package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.repository.ProductsRepository;
import it.opensource.ecompany.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("productsService")
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllProducts() {

        return productsRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Product> getAllProductsByPage(Pageable pageable) {

        return productsRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getProductsByCategory(Long categoryId) {

        return productsRepository.findByCategoryId(categoryId);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Product> getProductsByCategoryByPage(Long categoryId, Pageable pageable) {

        return productsRepository.findByCategoryId(categoryId, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProductById(Long id) {

        return productsRepository.findById(id)
                                 .get();
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProductByProductCode(String productCode) {

        return productsRepository.findByProductCode(productCode);
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
