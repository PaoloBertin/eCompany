package it.opensource.ecompany.service;

import java.util.List;

import it.opensource.ecompany.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductsService {

    public List<Product> getAll();

    public Page<Product> getAllByPage(Pageable pageable);

    public List<Product> getProductsByCategory(Long id);

    public Page<Product> getProductsByCategoryByPage(Long categoryId, Pageable pageable);

    public Product getProductById(Long id);

    public List<Product> getProductsByName(String name);

    public List<Product> getProductsByNameContaining(String searchText);

    public Page<Product> getProductsByNameContainingByPage(String searchText, Pageable pageable);

    public Product save(Product product);

    public void deleteProduct(Product product);
}
