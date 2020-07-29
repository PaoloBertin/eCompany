package it.opensource.ecompany.web.form;

import it.opensource.ecompany.domain.Address;
import it.opensource.ecompany.domain.Contact;
import it.opensource.ecompany.domain.Customer;

import javax.validation.constraints.NotNull;

public class CustomerForm {

    private String firstname;

    private String lastname;

    @NotNull
    private String username;

    private String password;

    private String password2;

    private String email;

    private String cellular;

    private String landlinePhone;

    private String city;

    private String street;

    private String houseNumber;

    private String zipCode;

    private String country;

    private String state;

    private Customer customer = new Customer();

    private Address address = new Address();

    private Contact contact = new Contact();

    public String getFirstname() {

        return firstname;
    }

    public void setFirstname(String firstname) {

        this.firstname = firstname;
    }

    public String getLastname() {

        return lastname;
    }

    public void setLastname(String lastname) {

        this.lastname = lastname;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getPassword2() {

        return password2;
    }

    public void setPassword2(String password2) {

        this.password2 = password2;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getCellular() {

        return cellular;
    }

    public void setCellular(String cellular) {

        this.cellular = cellular;
    }

    public String getLandlinePhone() {

        return landlinePhone;
    }

    public void setLandlinePhone(String landlinePhone) {

        this.landlinePhone = landlinePhone;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getStreet() {

        return street;
    }

    public void setStreet(String street) {

        this.street = street;
    }

    public String getHouseNumber() {

        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {

        this.houseNumber = houseNumber;
    }

    public String getZipCode() {

        return zipCode;
    }

    public void setZipCode(String zipCode) {

        this.zipCode = zipCode;
    }

    public String getCountry() {

        return country;
    }

    public void setCountry(String country) {

        this.country = country;
    }

    public String getState() {

        return state;
    }

    public void setState(String state) {

        this.state = state;
    }

    private void setAddress() {

        address.setCity(getCity());
        address.setStreet(getStreet());
        address.setHouseNumber(getHouseNumber());
        address.setZipCode(getZipCode());
        address.setCountry(getCountry());
        address.setState(getState());
    }

    private void setContact() {

        contact.setEmail(getEmail());
        contact.setCellular(getCellular());
        contact.setLandlinePhone(getLandlinePhone());
    }

    public Customer getCustomer() {

        setAddress();
        setContact();
        customer.setAddress(address);
        customer.setContact(contact);
        customer.setFirstname(getFirstname());
        customer.setLastname(getLastname());
        customer.setUsername(getUsername());
        customer.setPassword(getPassword());

        return customer;
    }
}
