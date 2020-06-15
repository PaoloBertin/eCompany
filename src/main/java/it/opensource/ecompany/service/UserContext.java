package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.Customer;

public interface UserContext {

    public Customer getCurrentCustomer();

    public void setCurrentCustomer(Customer customer);
}
