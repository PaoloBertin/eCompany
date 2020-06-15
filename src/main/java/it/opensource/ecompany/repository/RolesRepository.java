package it.opensource.ecompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.opensource.ecompany.domain.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {

}
