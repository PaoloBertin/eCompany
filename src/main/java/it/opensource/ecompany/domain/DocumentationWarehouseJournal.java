package it.opensource.ecompany.domain;

import it.opensource.ecompany.domain.util.Causal;
import it.opensource.ecompany.domain.util.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Table(name = "documentations_warehouse_journal")
@Entity
public class DocumentationWarehouseJournal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "warehouse_id", foreignKey = @ForeignKey(name = "documentations_warehouse_journal_fk_01"))
    @ManyToOne
    private Warehouse warehouse;

    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private Causal causal;

    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private Document document;

    @Column(name = "document_date")
    private LocalDate documentDate;

    @Column(name = "document_number")
    private Long documentNumber;

    @JoinColumn(name = "line_item_warehouse_id", foreignKey = @ForeignKey(name = "documentations_warehouse_journal_fk_02"))
    @OneToOne
    private LineItemWarehouse lineItemWarehouse;

    @Version
    private Long version;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Warehouse getWarehouse() {

        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {

        this.warehouse = warehouse;
    }

    public Causal getCausal() {

        return causal;
    }

    public void setCausal(Causal causal) {

        this.causal = causal;
    }

    public Document getDocument() {

        return document;
    }

    public void setDocument(Document document) {

        this.document = document;
    }

    public LocalDate getDocumentDate() {

        return documentDate;
    }

    public void setDocumentDate(LocalDate documentDate) {

        this.documentDate = documentDate;
    }

    public Long getDocumentNumber() {

        return documentNumber;
    }

    public void setDocumentNumber(Long documentNumber) {

        this.documentNumber = documentNumber;
    }

    public LineItemWarehouse getLineItemWarehouse() {

        return lineItemWarehouse;
    }

    public void setLineItemWarehouse(LineItemWarehouse lineItemWarehouse) {

        this.lineItemWarehouse = lineItemWarehouse;
    }

    public Long getVersion() {

        return version;
    }

    public void setVersion(Long version) {

        this.version = version;
    }

}
