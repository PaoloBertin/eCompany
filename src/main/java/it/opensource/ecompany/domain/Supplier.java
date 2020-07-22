package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "suppliers")
@Entity
public class Supplier implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productid;

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

}
