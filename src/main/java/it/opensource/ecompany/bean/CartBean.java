package it.opensource.ecompany.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import it.opensource.ecompany.domain.Product;
import lombok.extern.slf4j.Slf4j;

/**
 * Shopping cart
 *
 * @author Paolo Bertin
 */
@Slf4j
public class CartBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double totalCost = 0.0;

    private Map<Product, Integer> products = new HashMap<Product, Integer>();

    private Double subTotal = 0.0;

    private Double shippingCosts;

    private boolean expressDelivery = false;

    private Integer numberProducts = 0;

    public Map<Product, Integer> getProducts() {

        return this.products;
    }

    public void addProductToCart(Product product) {

        log.debug("aggiunge prodotto al carrello con id=" + product.getProductid());

        if (this.products.containsKey(product)) {
            int quantity = this.products.get(product);
            quantity++;
            this.products.put(product, quantity);
        } else {
            this.products.put(product, 1);
        }

        setSubTotal();

        log.debug("nuovo prodotti in carrello=" + getNumberProducts());
        log.debug("importo subTotale acquisto=" + subTotal);

    }

    public void deleteProductToCart(Product product) {

        log.debug("elimina prodotto dal carrello con id=" + product.getProductid());

        if (this.products.containsKey(product)) {
            products.remove(product);
            setSubTotal();
        }
    }

    public Integer getNumberProducts() {

        setSubTotal();

        return numberProducts;
    }

    public void emptyCart() {

        products.clear();
    }

    public boolean isExpressDelivery() {

        return expressDelivery;
    }

    public void setExpressDelivery(Boolean expressDelivery) {

        this.expressDelivery = expressDelivery;
    }

    public Double getShippingCosts() {

        setShippingCosts();

        return shippingCosts;
    }

    private void setShippingCosts() {

        if (expressDelivery) {
            shippingCosts = 5.0;
        } else {
            if (subTotal >= 25) {
                shippingCosts = 0.0;
            }
            else {
                shippingCosts = 3.0;
            }
        }
    }

    public Double getTotalCost() {

        setSubTotal();
        totalCost = subTotal;
        totalCost += getShippingCosts();

        return this.totalCost;
    }

    public Double getSubTotal() {

        return subTotal;
    }

    private void setSubTotal() {

        numberProducts = 0;
        subTotal = 0.0;

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product key = entry.getKey();
            int quantity = entry.getValue();
            double price = key.getPrice();
            double unitCost = price * quantity;

            numberProducts += quantity;

            subTotal += unitCost;
        }
    }
}
