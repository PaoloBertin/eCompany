package it.opensource.ecompany.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.service.ProductsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Paolo Bertin
 */
@Slf4j
public class CartBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ProductsService productsService;

    private Map<Product, Integer> products = new HashMap<Product, Integer>();

    private Float totalAmount = 0.0F;

    private Integer numberOfProducts = 0;

    public Map<Product, Integer> getProducts() {

        return this.products;
    }

    public Float getTotalAmount() {

        return this.totalAmount;
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

        updateCart();

        log.debug("nuovo totale prodotti in carrello=" + getNumberOfProducts());
        log.debug("importo totale acquisto=" + getTotalAmount());

    }

    public void deleteProductToCart(Product product) {

        log.debug("elimina prodotto dal carrello con id=" + product.getProductid());

        if (products.containsKey(product)) {
            products.remove(product);
            updateCart();
        }
    }

    public Integer getNumberOfProducts() {

        updateCart();

        return this.numberOfProducts;
    }

    public void emptyCart() {

        products.clear();
    }

    private void updateCart() {

        numberOfProducts = 0;
        totalAmount = 0F;

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product key = entry.getKey();
            int quantity = entry.getValue();
            float price = key.getPrice();
            float unitCost = price * quantity;

            numberOfProducts += quantity;

            totalAmount += unitCost;
        }
    }
}
