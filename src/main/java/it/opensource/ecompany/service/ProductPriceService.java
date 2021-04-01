package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.ProductPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductPriceService {

    Page<ProductPrice> getProductPriceByPriceListName(String priceListName, Pageable pageable);

    ProductPrice getProductPriceByPriceListNameAndProductCode(String priceList, String productCode);
}
