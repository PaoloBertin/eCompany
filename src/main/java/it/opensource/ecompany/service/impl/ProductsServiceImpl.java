package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.domain.ProductPrice;
import it.opensource.ecompany.repository.ProductsRepository;
import it.opensource.ecompany.service.ProductPriceService;
import it.opensource.ecompany.service.ProductsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service("productsService")
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;

    private final ProductPriceService productPriceService;

    public ProductsServiceImpl(ProductsRepository productsRepository, ProductPriceService productPriceService) {

        this.productsRepository = productsRepository;
        this.productPriceService = productPriceService;
    }

    @Override
    public Long getNumberProducts() {

        return productsRepository.count();
    }

    @Override
    public Long getNumberProductsByCategory(Long categoryId) {

        return productsRepository.countByCategoryId(categoryId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllProducts() {

        List<Product> products = productsRepository.findAll();

        return setPrices(products);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Product> getAllProductsByPage(Pageable pageable) {

        Page<Product> pageProducts = productsRepository.findAll(pageable);
        setPrices(pageProducts.getContent());

        return pageProducts;
    }

    // TODO cambiare il nome del metodo in getAllProductsByCategory
    @Transactional(readOnly = true)
    @Override
    public List<Product> getProductsByCategory(Long categoryId) {

        List<Product> products = productsRepository.findByCategoryId(categoryId);

        return setPrices(products);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Product> getProductsByCategoryByPage(Long categoryId, Pageable pageable) {

        Page<Product> pageProducts = productsRepository.findByCategoryId(categoryId, pageable);
        setPrices(pageProducts.getContent());

        return pageProducts;
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProductById(Long id) {

        Product product = productsRepository.findById(id).get();
        setPrice(product);

        return product;
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProductByProductCode(String productCode) {

        Product product = productsRepository.findByProductCode(productCode);
        setPrice(product);

        return product;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getProductsByName(String searchText) {

        List<Product> products = productsRepository.findByName(searchText);
        setPrices(products);

        return products;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getProductsByNameContaining(String searchText) {

        List<Product> products = productsRepository.findByNameContaining(searchText);
        setPrices(products);

        return products;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Product> getProductsByNameContainingByPage(String searchText, Pageable pageable) {

        Page<Product> pageProducts = productsRepository.findByNameContaining(searchText, pageable);
        setPrices(pageProducts.getContent());

        return pageProducts;
    }

    // TODO da modificare
    @Override
    public Product saveProduct(Product product) {

        return productsRepository.save(product);
    }

    // TODO da modificare altrimenti incongruenza tabelle!!
    @Override
    public void deleteProduct(Product product) {

        productsRepository.delete(product);
    }

    private Product setPrice(Product product) {

        ProductPrice productPrice = productPriceService.getProductPriceByProductCode(product.getProductCode());
        BigDecimal price = productPrice.getPrice();
        product.setPrice(price);

        return product;
    }

    private List<Product> setPrices(List<Product> products) {

        for (Product product : products) {
            ProductPrice productPrice = productPriceService.getProductPriceByProductCode(product.getProductCode());
            BigDecimal price = productPrice.getPrice();
            product.setPrice(price);
        }

        return products;
    }
}
