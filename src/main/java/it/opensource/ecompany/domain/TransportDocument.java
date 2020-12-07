package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "transport_documents")
@Entity
public class TransportDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_date")
    private LocalDateTime transportDocumentDate;

    @Column(name = "document_number")
    private Long transportDocumentNumber;

    @Column(name = "transport_document_registration_date")
    private LocalDateTime transportDocumentRegistrationDate;

    @Column(name = "transferor_code")
    private String transferorCode;

    @Column(name = "transferee_code")
    private String transfereeCode;

}