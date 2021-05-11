package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoicesService {

    long getNumberInvoices();

    Page<Invoice> getAllInvoicesByTransferorCodeAndTransfereeCodeByPage(String transferorCode, String transfereeCode,
                                                                        Pageable pageable);

    Page<Invoice> getAllInvoicesByTransferorCodeByPage(String transferorCode, Pageable pageable);

    Page<Invoice> getAllInvoicesByTransfereeCodeByPage(String transfereeCode, Pageable pageable);

    Page<Invoice> getAllInvoicesByPage(Pageable pageable);
}
