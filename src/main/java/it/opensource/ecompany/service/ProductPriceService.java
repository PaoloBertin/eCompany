package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.ProductPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface ProductPriceService {

    Page<ProductPrice> getProductPriceByPriceListName(String priceListName, Pageable pageable);

    BigDecimal getProductPriceByPriceListNameAndProductCode(String productCode);

    ProductPrice getProductPriceByPriceListNameAndProductCode(String priceList, String productCode);
}
