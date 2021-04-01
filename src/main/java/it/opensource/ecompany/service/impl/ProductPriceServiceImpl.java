package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.ProductPrice;
import it.opensource.ecompany.repository.ProductPricesRepository;
import it.opensource.ecompany.service.ProductPriceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("productPriceService")
public class ProductPriceServiceImpl implements ProductPriceService {

    public final ProductPricesRepository productPricesRepository;

    public ProductPriceServiceImpl(ProductPricesRepository productPricesRepository) {

        this.productPricesRepository = productPricesRepository;
    }

    @Override
    public Page<ProductPrice> getProductPriceByPriceListName(String priceListName, Pageable pageable) {

        return productPricesRepository.findByPriceListPriceListName(priceListName, pageable);
    }

    @Override
    public ProductPrice getProductPriceByPriceListNameAndProductCode(String priceListName, String productCode) {

        return productPricesRepository.findByPriceListPriceListNameAndProductCode(priceListName, productCode);
    }
}
