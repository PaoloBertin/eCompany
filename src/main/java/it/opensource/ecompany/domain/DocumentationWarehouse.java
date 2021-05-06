package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "documentations_warehouse")
@Entity
public class DocumentationWarehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Long getVersion() {

        return version;
    }

    public void setVersion(Long version) {

        this.version = version;
    }

}
