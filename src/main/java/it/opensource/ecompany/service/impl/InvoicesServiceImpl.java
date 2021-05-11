package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Invoice;
import it.opensource.ecompany.repository.InvoicesRepository;
import it.opensource.ecompany.service.InvoicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("invoicesService")
public class InvoicesServiceImpl implements InvoicesService {

    private final InvoicesRepository invoicesRepository;

    @Override
    public long getNumberInvoices() {

        return invoicesRepository.count();
    }

    @Override
    public Page<Invoice> getAllInvoicesByTransferorCodeAndTransfereeCodeByPage(String transferorCode, String transfereeCode,
                                                                               Pageable pageable) {

        return invoicesRepository.findByTransferorCodeAndTransfereeCode(transferorCode, transfereeCode, pageable);
    }

    @Override
    public Page<Invoice> getAllInvoicesByTransferorCodeByPage(String transferorCode, Pageable pageable) {

        return invoicesRepository.findByTransferorCode(transferorCode, pageable);
    }

    @Override
    public Page<Invoice> getAllInvoicesByTransfereeCodeByPage(String transfereeCode, Pageable pageable) {

        return invoicesRepository.findByTransfereeCode(transfereeCode, pageable);
    }

    @Override
    public Page<Invoice> getAllInvoicesByPage(Pageable pageable) {

        return invoicesRepository.findAll(pageable);
    }
}
