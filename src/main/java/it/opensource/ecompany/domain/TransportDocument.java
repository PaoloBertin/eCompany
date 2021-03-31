package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "transport_documents")
@Entity
public class TransportDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transferor_code")
    private String transferorCode;

    @Column(name = "transferee_code")
    private String transfereeCode;

    @JoinColumn(name = "documentation_warehouse_id", foreignKey = @ForeignKey(name = "transport_documents_fk_01"))
    @OneToOne
    private DocumentationWarehouse documentationWarehouse;

    @Version
    private Long version;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getTransferorCode() {

        return transferorCode;
    }

    public void setTransferorCode(String transferorCode) {

        this.transferorCode = transferorCode;
    }

    public String getTransfereeCode() {

        return transfereeCode;
    }

    public void setTransfereeCode(String transfereeCode) {

        this.transfereeCode = transfereeCode;
    }

    public DocumentationWarehouse getDocumentationWarehouse() {

        return documentationWarehouse;
    }

    public void setDocumentationWarehouse(DocumentationWarehouse documentationWarehouse) {

        this.documentationWarehouse = documentationWarehouse;
    }

}