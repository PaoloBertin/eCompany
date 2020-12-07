package it.opensource.ecompany.service;

import java.util.List;

import it.opensource.ecompany.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductsService {

    List<Product> getAll();

    Page<Product> getAllByPage(Pageable pageable);

    List<Product> getProductsByCategory(Long id);

    Page<Product> getProductsByCategoryByPage(Long categoryId, Pageable pageable);

    Product getProductById(Long id);

    List<Product> getProductsByName(String name);

    List<Product> getProductsByNameContaining(String searchText);

    Page<Product> getProductsByNameContainingByPage(String searchText, Pageable pageable);

    Product saveProduct(Product product);

    void deleteProduct(Product product);
}
