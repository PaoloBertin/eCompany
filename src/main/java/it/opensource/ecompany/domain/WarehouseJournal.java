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
    private Documentation documentation;

    @Version
    private Long version;

    public WarehouseJournal() {

    }

    public WarehouseJournal(Documentation documentation) {

        this.documentation = documentation;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Documentation getDocumentation() {

        return documentation;
    }

    public void setDocumentation(Documentation documentation) {

        this.documentation = documentation;
    }

    public Long getVersion() {

        return version;
    }

    public void setVersion(Long version) {

        this.version = version;
    }

}
