package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.ProductPrice;
import it.opensource.ecompany.service.ProductPriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductPriceServiceImplTest {

    @Autowired
    private ProductPriceService productPriceService;

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getProductPriceByPriceListName() {

        String priceList = "base";
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("productCode")));

        long expected = 54;
        long actual = productPriceService.getProductPriceByPriceListName(priceList, pageable).getTotalElements();

        assertEquals(expected, actual);
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getProductPriceByPriceListNameAndProductCode() {

        String priceListName = "base";
        String productCode = "8883780451";
        ProductPrice productPrice = productPriceService.getProductPriceByPriceListNameAndProductCode(priceListName, productCode);

        assertNotNull(productPrice);
    }
}
