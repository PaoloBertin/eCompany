package it.opensource.ecompany.bean;

import it.opensource.ecompany.domain.Customer;

/**
 * 
 * @author Paolo Bertin
 */
public class CustomerBean {

    private Customer connectedCustomer;

    public Customer getConnectedCustomer() {

        return connectedCustomer;
    }

    public void setConnectedCustomer(Customer connectedCustomer) {

        this.connectedCustomer = connectedCustomer;
    }

}
