package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.TransportDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportDocumentRepository extends JpaRepository<TransportDocument, Long> {

    Page<TransportDocument> findByTransferorCodeAndTransfereeCode(String transferorCode, String transfereeCode, Pageable pageable);

    Page<TransportDocument> findByTransferorCode(String transferorCode, Pageable pageable);

    Page<TransportDocument> findByTransfereeCode(String transfereeCode, Pageable pageable);
}
