package it.opensource.ecompany.web.form;

import it.opensource.ecompany.domain.Address;
import it.opensource.ecompany.domain.Contact;
import it.opensource.ecompany.domain.Customer;

public class CustomerForm {

    private Long customerid;

    private String firstname;

    private String lastname;

    private String username;

    private String password;

    private String password2;

    private Long contactId;

    private String email;

    private String cellular;

    private String landlinePhone;

    private Long addressId;

    private String city;

    private String street;

    private String houseNumber;

    private String zipCode;

    private String country;

    private String state;

    public Long getCustomerid() {

        return customerid;
    }

    public void setCustomerid(Long customerid) {

        this.customerid = customerid;
    }

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

    public Long getContactId(){

        return contactId;
    }

    public void setContactId(Long contactId){

        this.contactId = contactId;
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

    public Long getAddressId() {

        return addressId;
    }

    public void setAddressId(Long addressId){

        this.addressId = addressId;
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

    public Address getAddress() {

        Address address = new Address();
        address.setId(getAddressId());
        address.setCity(getCity());
        address.setStreet(getStreet());
        address.setHouseNumber(getHouseNumber());
        address.setZipCode(getZipCode());
        address.setCountry(getCountry());
        address.setState(getState());

        return address;
    }

    public void setAddress(Address address) {

        this.addressId = address.getId();
        this.city = address.getCity();
        this.street = address.getCity();
        this.houseNumber = address.getHouseNumber();
        this.zipCode = address.getZipCode();
        this.country = address.getCountry();
        this.state = address.getState();
    }

    public Contact getContact() {

        Contact contact = new Contact();
        contact.setId(getContactId());
        contact.setEmail(getEmail());
        contact.setCellular(getCellular());
        contact.setLandlinePhone(getLandlinePhone());

        return contact;
    }

    public void setContact(Contact contact) {

        this.contactId = contact.getId();
        this.email = contact.getEmail();
        this.cellular = contact.getCellular();
        this.landlinePhone = contact.getLandlinePhone();
    }

    public Customer getCustomer() {

        Customer customer = new Customer();
        customer.setCustomerid(getCustomerid());
        customer.setFirstname(getFirstname());
        customer.setLastname(getLastname());
        customer.setUsername(getUsername());
        customer.setPassword(getPassword());
        customer.setAddress(getAddress());
        customer.setContact(getContact());

        return customer;
    }

    public void setCustomer(Customer customer) {

        this.customerid = customer.getCustomerid();
        this.firstname = customer.getFirstname();
        this.lastname = customer.getLastname();
        this.username = customer.getUsername();
        this.password = customer.getPassword();
        this.addressId = customer.getAddress().getId();
        this.city = customer.getAddress().getCity();
        this.street = customer.getAddress().getCity();
        this.houseNumber = customer.getAddress().getHouseNumber();
        this.zipCode = customer.getAddress().getZipCode();
        this.country = customer.getAddress().getCountry();
        this.state = customer.getAddress().getState();
        this.contactId = customer.getContact().getId();
        this.email = customer.getContact().getEmail();
        this.cellular = customer.getContact().getCellular();
        this.landlinePhone = customer.getContact().getLandlinePhone();
    }
}
