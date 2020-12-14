package it.opensource.ecompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository<T, ID> extends JpaRepository<T, ID> {

}
