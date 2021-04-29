package it.opensource.ecompany.bean;

import it.opensource.ecompany.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Shopping cart
 *
 * @author Paolo Bertin
 */
public class CartBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(CartBean.class);

    private BigDecimal totalCost = BigDecimal.ZERO;

    private Map<Product, Integer> products = new HashMap<>();

    private BigDecimal subTotal = BigDecimal.ZERO;

    private BigDecimal shippingCosts;

    private boolean expressDelivery = false;

    private Integer numberProducts = 0;

    public Map<Product, Integer> getProducts() {

        return this.products;
    }

    public void addProductToCart(Product product) {

        log.debug("aggiunge prodotto al carrello con id=" + product.getId());

        if (this.products.containsKey(product)) {
            int quantity = this.products.get(product);
            quantity++;
            this.products.put(product, quantity);
        } else {
            this.products.put(product, 1);
        }

        updateSubTotal();

        log.debug("nuovo prodotti in carrello=" + getNumberProducts());
        log.debug("importo subTotale acquisto=" + subTotal);

    }

    public void deleteProductToCart(Product product) {

        log.debug("elimina prodotto dal carrello con id=" + product.getId());

        if (this.products.containsKey(product)) {
            products.remove(product);
            updateSubTotal();
        }
    }

    public Integer getNumberProducts() {

        updateSubTotal();

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

    public BigDecimal getShippingCosts() {

        updateShippingCosts();

        return shippingCosts;
    }

    private void updateShippingCosts() {

        if (expressDelivery) {
            shippingCosts = new BigDecimal(5.0);
        } else {
            int result = subTotal.compareTo(new BigDecimal(25));
            if (result >= 0) {
                shippingCosts = new BigDecimal(0.0);
            } else {
                shippingCosts = new BigDecimal(3.0);
            }
        }
    }

    public BigDecimal getTotalCost() {

        updateSubTotal();
        totalCost = subTotal;

        totalCost.add(getShippingCosts());

        return this.totalCost;
    }

    public BigDecimal getSubTotal() {

        return subTotal;
    }

    private void updateSubTotal() {

        numberProducts = 0;
        subTotal = BigDecimal.ZERO;

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product key = entry.getKey();
            int quantity = entry.getValue();
            BigDecimal price = key.getPrice();
            BigDecimal unitCost = price.multiply(BigDecimal.valueOf(quantity));

            numberProducts += quantity;

            subTotal = subTotal.add(unitCost);
        }
    }

}
