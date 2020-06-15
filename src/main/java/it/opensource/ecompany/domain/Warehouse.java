package it.opensource.ecompany.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Table(name = "warehouse")
@Entity
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseid;

    @JoinColumn(name = "product_id")
    @OneToOne
    private Product product;

    @Column(name = "sku")
    private String sku;

    @Column(name = "cost")
    private Float cost;

    @Column(name = "unit")
    private String unit;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "reorder_quantity")
    private Integer reorderQuantity;

    @Column(name = "inventory_value")
    private Float inventoryValue;

    @Column(name = "reorder")
    private Boolean reorder;

    @Column(name = "container")
    private String container;

    @Column(name = "position")
    private String position;
}
