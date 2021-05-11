package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoice, Long> {

    Page<Invoice> findByTransferorCodeAndTransfereeCode(String transferorCode, String transfereeCode, Pageable pageable);

    Page<Invoice> findByTransferorCode(String transferorCode, Pageable pageable);

    Page<Invoice> findByTransfereeCode(String transfereeCode, Pageable pageable);
}
