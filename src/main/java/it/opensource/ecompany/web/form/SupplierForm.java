package it.opensource.ecompany.web.form;

import it.opensource.ecompany.domain.Address;
import it.opensource.ecompany.domain.Contact;
import it.opensource.ecompany.domain.Supplier;

public class SupplierForm {

    private Long supplierId;

    private String name;

    private String vat;

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

    public Long getSupplierId() {

        return supplierId;
    }

    public void setSupplierId(Long supplierId) {

        this.supplierId = supplierId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getVat() {

        return vat;
    }

    public void setVat(String vat) {

        this.vat = vat;
    }

    public Long getContactId() {

        return contactId;
    }

    public void setContactId(Long contactId) {

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

    public void setAddressId(Long addressId) {

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

    public Supplier getSupplier() {

        Supplier supplier = new Supplier();
        supplier.setId(getSupplierId());
        supplier.setName(getName());
        supplier.setVat(getVat());
        supplier.setAddress(getAddress());
        supplier.setContact(getContact());

        return supplier;
    }

    public void setSupplier(Supplier supplier) {

        this.supplierId = supplier.getId();
        this.name = supplier.getName();
        this.vat = supplier.getVat();
        this.addressId = supplier.getAddress()
                                 .getId();
        this.city = supplier.getAddress()
                            .getCity();
        this.street = supplier.getAddress()
                              .getCity();
        this.houseNumber = supplier.getAddress()
                                   .getHouseNumber();
        this.zipCode = supplier.getAddress()
                               .getZipCode();
        this.country = supplier.getAddress()
                               .getCountry();
        this.state = supplier.getAddress()
                             .getState();
        this.contactId = supplier.getContact()
                                 .getId();
        this.email = supplier.getContact()
                             .getEmail();
        this.cellular = supplier.getContact()
                                .getCellular();
        this.landlinePhone = supplier.getContact()
                                     .getLandlinePhone();
    }
}
