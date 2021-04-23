package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.ProductPrice;
import it.opensource.ecompany.repository.ProductPricesRepository;
import it.opensource.ecompany.service.ProductPriceService;
import org.springframework.stereotype.Service;

@Service("productPriceService")
public class ProductPriceServiceImpl implements ProductPriceService {

    public final ProductPricesRepository productPricesRepository;

    public ProductPriceServiceImpl(ProductPricesRepository productPricesRepository) {

        this.productPricesRepository = productPricesRepository;
    }

    @Override
    public ProductPrice getProductPriceByProductCode(String productCode) {

        return productPricesRepository.findByProductCode(productCode);
    }

    @Override
    public ProductPrice saveProductPrice(ProductPrice productPrice) {

        return productPricesRepository.save(productPrice);
    }
}
