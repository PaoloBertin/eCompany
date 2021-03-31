package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "warehouse_journal")
@Entity
public class WarehouseJournal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "documentation_warehouse_id", foreignKey = @ForeignKey(name = "warehouse_journal_fk_01"))
    @OneToOne
    private DocumentationWarehouse documentationWarehouse;

    @Version
    private Long version;

    public WarehouseJournal() {

    }

    public WarehouseJournal(DocumentationWarehouse documentationWarehouse) {

        this.documentationWarehouse = documentationWarehouse;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public DocumentationWarehouse getDocumentationWarehouse() {

        return documentationWarehouse;
    }

    public void setDocumentationWarehouse(DocumentationWarehouse documentationWarehouse) {

        this.documentationWarehouse = documentationWarehouse;
    }

    public Long getVersion() {

        return version;
    }

    public void setVersion(Long version) {

        this.version = version;
    }

}
