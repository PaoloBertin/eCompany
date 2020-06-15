package it.opensource.ecompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.opensource.ecompany.domain.Customer;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Long> {

	public Customer findByUsernameAndPassword(String username, String password);

	public Customer findByUsername(String username);

	public Customer findByEmail(String email);
}
