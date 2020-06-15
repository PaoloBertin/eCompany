package it.opensource.ecompany.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.opensource.ecompany.domain.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

    public List<Product> findByCategoryCategoryid(Long id);

    public Page<Product> findByCategoryCategoryid(Long id, Pageable pageable);

    public List<Product> findByName(String name);

    public List<Product> findByNameContaining(String searchText);

    public Page<Product> findByNameContaining(String searchText, Pageable pageable);

}
