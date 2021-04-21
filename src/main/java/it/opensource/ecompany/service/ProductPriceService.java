package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.ProductPrice;

public interface ProductPriceService {

    ProductPrice getProductPriceByProductCode(String productCode);
}
