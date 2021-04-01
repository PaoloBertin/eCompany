package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.ProductPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPricesRepository extends JpaRepository<ProductPrice, Long> {

    Page<ProductPrice> findByPriceListPriceListName(String priceListName, Pageable pageable);

    ProductPrice findByPriceListPriceListNameAndProductCode(String priceListName, String productCode);
}
