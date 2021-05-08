package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "transport_documents")
@Entity
public class TransportDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transferor_code")
    private String transferorCode;    // codice cedente

    @Column(name = "transferee_code")
    private String transfereeCode;    // codice cessionario

    @Column(name = "movement_date")
    private LocalDate movementDate;

    @JoinTable(name = "transport_documents_line_itemddts",
            joinColumns = @JoinColumn(name = "transport_document_id"),
            inverseJoinColumns = @JoinColumn(name = "line_itemddt_id")
    )
    @OneToMany
    private List<LineItemDdt> lineItemDdts = new ArrayList<>();

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

    public LocalDate getMovementDate() {

        return movementDate;
    }

    public void setMovementDate(LocalDate movementDate) {

        this.movementDate = movementDate;
    }

    public List<LineItemDdt> getLineItemDdts() {

        return lineItemDdts;
    }

    public void setLineItemDdts(List<LineItemDdt> lineItemDdts) {

        this.lineItemDdts = lineItemDdts;
    }

    public Long getVersion() {

        return version;
    }

    public void setVersion(Long version) {

        this.version = version;
    }
}