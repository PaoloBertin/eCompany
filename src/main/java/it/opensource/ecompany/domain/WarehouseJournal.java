package it.opensource.ecompany.domain;

import it.opensource.ecompany.domain.util.Causal;
import it.opensource.ecompany.domain.util.Document;
import it.opensource.ecompany.domain.util.UM;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "warehouse_journal")
@Entity
public class WarehouseJournal implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "warehouse_id", foreignKey = @ForeignKey(name = "warehouse_journal_fk_01"))
    @ManyToOne
    private Warehouse warehouse;

    @Column(name = "movement_date")
    private LocalDateTime movementDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private Causal causal;

    @JoinColumn(name = "product_id")
    @OneToOne
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", length = 25)
    private Document documentType;

    @Column(name = "document_number")
    private String documentNumber;

    @Transient
    private BigDecimal price;

    @Transient
    private Integer quantity;

    @Transient
    private UM um;

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

    public LocalDateTime getMovementDate() {

        return movementDate;
    }

    public void setMovementDate(LocalDateTime movementDate) {

        this.movementDate = movementDate;
    }

    public Causal getCausal() {

        return causal;
    }

    public void setCausal(Causal causal) {

        this.causal = causal;
    }

    public Product getProduct() {

        return product;
    }

    public void setProduct(Product product) {

        this.product = product;
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

    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }

    public Integer getQuantity() {

        return quantity;
    }

    public void setQuantity(Integer quantity) {

        this.quantity = quantity;
    }

    public UM getUm() {

        return um;
    }

    public void setUm(UM um) {

        this.um = um;
    }

    public Long getVersion() {

        return version;
    }

    public void setVersion(Long version) {

        this.version = version;
    }
}
