package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.Customer;
import org.springframework.security.core.userdetails.User;

public interface UserContext {

    public Customer getCurrentCustomer();

    public void setCurrentCustomer(Customer customer);
}
