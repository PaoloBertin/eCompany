package it.opensource.ecompany.service;

import java.util.List;

import it.opensource.ecompany.domain.Customer;

public interface CustomersService {

    public List<Customer> getAll();

    public Customer getCustomerById(Long customerId);

    public Customer getCustomerByEmail(String email);

    public Customer getCustomerByUsername(String username);

    public Customer getCustomerByUsernameAndPassword(String username, String password);

    public boolean verifyCustomer(String username, String password);
    
//    public Customer createCustomer(final Customer customer);
    public Long createCustomer(final Customer customer);

    public Customer getCurrentCustomer();

    public void setCurrentCustomer(Customer customer);
}
