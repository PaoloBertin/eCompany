package it.opensource.ecompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.opensource.ecompany.domain.Category;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Long>{

}
