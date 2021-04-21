package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.ProductPrice;
import it.opensource.ecompany.service.ProductPriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    void getProductPriceByProductCode() {

        String productCode = "8883780451";
        ProductPrice productPrice = productPriceService.getProductPriceByProductCode(productCode);

        assertNotNull(productPrice);
    }
}
