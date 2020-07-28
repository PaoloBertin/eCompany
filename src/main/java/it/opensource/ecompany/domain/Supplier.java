package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "suppliers")
@Entity
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "vat")
    private String vat;

    @Column(name = "legal_form")
    private String legalForm;

    @Column(name = "registered_office")
    private Address registeredOffice;

    @JoinColumn(name = "contact_id")
    @OneToOne
    private Contact contact;

    @JoinColumn(name = "address_id")
    @OneToOne
    private Address address;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
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

    public String getLegalForm() {

        return legalForm;
    }

    public void setLegalForm(String legalForm) {

        this.legalForm = legalForm;
    }

    public Address getRegisteredOffice() {

        return registeredOffice;
    }

    public void setRegisteredOffice(Address registeredOffice) {

        this.registeredOffice = registeredOffice;
    }

    public Contact getContact() {

        return contact;
    }

    public void setContact(Contact contact) {

        this.contact = contact;
    }

    public Address getAddress() {

        return address;
    }

    public void setAddress(Address address) {

        this.address = address;
    }

}
