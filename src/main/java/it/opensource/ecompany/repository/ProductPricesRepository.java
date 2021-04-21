package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPricesRepository extends JpaRepository<ProductPrice, Long> {

    ProductPrice findByProductCode(String productCode);
}
