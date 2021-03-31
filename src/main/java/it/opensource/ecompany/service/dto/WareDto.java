package it.opensource.ecompany.service.dto;

import it.opensource.ecompany.domain.Warehouse;

import java.math.BigDecimal;

public class WareDto {

    private Long id;

    private String sku;

    private BigDecimal cost;

    private String unit;

    private Integer quantity;

    private Integer reorderQuantity;

    private BigDecimal inventoryValue;

    private Boolean reorder;

    private String container;

    private String location;

    private Warehouse warehouse;

    private String name;

    private String isbn;

    public void setId(Long id) {

        this.id = id;
    }

    public void setSku(String sku) {

        this.sku = sku;
    }

    public void setCost(BigDecimal cost) {

        this.cost = cost;
    }

    public void setUnit(String unit) {

        this.unit = unit;
    }

    public void setQuantity(Integer quantity) {

        this.quantity = quantity;
    }

    public void setReorderQuantity(Integer reorderQuantity) {

        this.reorderQuantity = reorderQuantity;
    }

    public void setInventoryValue(BigDecimal inventoryValue) {

        this.inventoryValue = inventoryValue;
    }

    public void setReorder(Boolean reorder) {

        this.reorder = reorder;
    }

    public void setContainer(String container) {

        this.container = container;
    }

    public void setLocation(String location) {

        this.location = location;
    }

    public void setWarehouse(Warehouse warehouse) {

        this.warehouse = warehouse;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setIsbn(String isbn) {

        this.isbn = isbn;
    }

    public Long getId() {

        return id;
    }

    public String getSku() {

        return sku;
    }

    public BigDecimal getCost() {

        return cost;
    }

    public String getUnit() {

        return unit;
    }

    public Integer getQuantity() {

        return quantity;
    }

    public Integer getReorderQuantity() {

        return reorderQuantity;
    }

    public BigDecimal getInventoryValue() {

        return inventoryValue;
    }

    public Boolean getReorder() {

        return reorder;
    }

    public String getContainer() {

        return container;
    }

    public String getLocation() {

        return location;
    }

    public Warehouse getWarehouse() {

        return warehouse;
    }

    public String getName() {

        return name;
    }

    public String getIsbn() {

        return isbn;
    }
}
