package it.opensource.ecompany.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Table(name = "invoices")
@Entity
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transferor_code")
    private String transferorCode;

    @Column(name = "transferee_code")
    private String transfereeCode;

    @Column(name = "issuing_date")
    private LocalDate issuingDate;

    @JoinTable(name = "invoices_line_items_invoice",
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "line_item_invoice_id")
    )
    @OneToMany
    private List<Invoice> lineItemsInvoice = new ArrayList<>();

    @Version
    private Long version;
}
