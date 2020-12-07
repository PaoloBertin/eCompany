package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "invoices")
@Entity
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_number")
    private Long invoiceNumber;

    @Column(name = "invoice_date_issue")
    private java.time.LocalDateTime invoiceDateIssue;

    @Column(name = "invoice_due_date")
    private java.time.LocalDateTime invoiceDueDate;

    @Column(name = "transferor_code")
    private String transferorCode;

    @Column(name = "transferee_code")
    private String transfereeCode;

}
