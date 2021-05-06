package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

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

    @Column(name = "movement_date")
    private LocalDate movementDate;

    @JoinColumn(name = "line_item_warehouse_id")
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

}