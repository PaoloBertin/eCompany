package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long categoryId);

    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    List<Product> findByName(String name);

    List<Product> findByNameContaining(String searchText);

    Page<Product> findByNameContaining(String searchText, Pageable pageable);

}
