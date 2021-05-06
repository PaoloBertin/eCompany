package it.opensource.ecompany.domain;

import it.opensource.ecompany.domain.util.Causal;
import it.opensource.ecompany.domain.util.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "warehouse_cards")
@Entity
public class WarehouseCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movement_date")
    private LocalDateTime movementDate;

    @JoinColumn(name = "warehouse_id", foreignKey = @ForeignKey(name = "warehouse_cards_fk_01"))
    @ManyToOne
    private Warehouse warehouse;

    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "warehouse_cards_fk_02"))
    @OneToOne
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private Causal causal;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", length = 25)
    private Document documentType;

    @Column(name = "document_number")
    private String documentNumber;

    // private Integer stock; TODO

    // @Column(name = "inventory_value")
    // private BigDecimal inventoryValue; TODO

    @Version
    private Integer version;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public LocalDateTime getMovementDate() {

        return movementDate;
    }

    public void setMovementDate(LocalDateTime movementDate) {

        this.movementDate = movementDate;
    }

    public Warehouse getWarehouse() {

        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {

        this.warehouse = warehouse;
    }

    public Product getProduct() {

        return product;
    }

    public void setProduct(Product product) {

        this.product = product;
    }

    public Causal getCausal() {

        return causal;
    }

    public void setCausal(Causal causal) {

        this.causal = causal;
    }

    public Document getDocumentType() {

        return documentType;
    }

    public void setDocumentType(Document documentType) {

        this.documentType = documentType;
    }

    public String getDocumentNumber() {

        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {

        this.documentNumber = documentNumber;
    }

    public Integer getVersion() {

        return version;
    }

    public void setVersion(Integer version) {

        this.version = version;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WarehouseCard other = (WarehouseCard) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
