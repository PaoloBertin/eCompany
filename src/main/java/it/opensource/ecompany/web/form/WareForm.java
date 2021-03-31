package it.opensource.ecompany.web.form;

public class WareForm {

    private Long wareId;

    private String warehouseId;

    private Long productId;

    private String sku;

    private Float cost;

    private String unit;

    private Integer quantity;

    private Integer reorderQuantity;

    private Float inventoryValue;

    private Boolean reorder;

    private String container;

    private String location;

    public Long getWareId() {

        return wareId;
    }

    public void setWareId(Long wareId) {

        this.wareId = wareId;
    }

    public String getWarehouseId() {

        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {

        this.warehouseId = warehouseId;
    }

    public Long getProductId() {

        return productId;
    }

    public void setProductId(Long productId) {

        this.productId = productId;
    }

    public String getSku() {

        return sku;
    }

    public void setSku(String sku) {

        this.sku = sku;
    }

    public Float getCost() {

        return cost;
    }

    public void setCost(Float cost) {

        this.cost = cost;
    }

    public String getUnit() {

        return unit;
    }

    public void setUnit(String unit) {

        this.unit = unit;
    }

    public Integer getQuantity() {

        return quantity;
    }

    public void setQuantity(Integer quantity) {

        this.quantity = quantity;
    }

    public Integer getReorderQuantity() {

        return reorderQuantity;
    }

    public void setReorderQuantity(Integer reorderQuantity) {

        this.reorderQuantity = reorderQuantity;
    }

    public Float getInventoryValue() {

        return inventoryValue;
    }

    public void setInventoryValue(Float inventoryValue) {

        this.inventoryValue = inventoryValue;
    }

    public Boolean getReorder() {

        return reorder;
    }

    public void setReorder(Boolean reorder) {

        this.reorder = reorder;
    }

    public String getContainer() {

        return container;
    }

    public void setContainer(String container) {

        this.container = container;
    }

    public String getLocation() {

        return location;
    }

    public void setLocation(String location) {

        this.location = location;
    }
}
