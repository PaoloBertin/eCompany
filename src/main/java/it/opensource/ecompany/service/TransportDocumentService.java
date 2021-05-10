package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.TransportDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransportDocumentService {

    long getNumberTransportDocuments();

    Page<TransportDocument> getAllTransportDocumentByTransferorCodeAndTransfereeCodeByPage(String transferorCode, String transfereeCode,
                                                                                           Pageable pageable);

    Page<TransportDocument> getAllTransportDocumentByTransferorCodeByPage(String transferorCode, Pageable pageable);

    Page<TransportDocument> getAllTransportDocumentByTransfereeCodeByPage(String transfereeCode, Pageable pageable);
}
