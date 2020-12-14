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

    @JoinColumn(name = "documentation_id", foreignKey = @ForeignKey(name = "warehouse_journal_fk_01"))
    @OneToOne
    private DocumentationWarehouseJournal documentationWarehouseJournal;

    @Version
    private Long version;

    public WarehouseJournal() {

    }

    public WarehouseJournal(DocumentationWarehouseJournal documentationWarehouseJournal) {

        this.documentationWarehouseJournal = documentationWarehouseJournal;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public DocumentationWarehouseJournal getDocumentationWarehouseJournal() {

        return documentationWarehouseJournal;
    }

    public void setDocumentationWarehouseJournal(DocumentationWarehouseJournal documentationWarehouseJournal) {

        this.documentationWarehouseJournal = documentationWarehouseJournal;
    }

    public Long getVersion() {

        return version;
    }

    public void setVersion(Long version) {

        this.version = version;
    }

}
