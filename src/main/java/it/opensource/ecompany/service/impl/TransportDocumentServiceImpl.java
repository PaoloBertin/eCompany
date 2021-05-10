package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.TransportDocument;
import it.opensource.ecompany.repository.TransportDocumentRepository;
import it.opensource.ecompany.service.TransportDocumentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("transportDocumentService")
public class TransportDocumentServiceImpl implements TransportDocumentService {

    private final TransportDocumentRepository transportDocumentRepository;

    public TransportDocumentServiceImpl(TransportDocumentRepository transportDocumentRepository) {

        this.transportDocumentRepository = transportDocumentRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public long getNumberTransportDocuments() {

        return transportDocumentRepository.count();
    }

    @Override
    public Page<TransportDocument> getAllTransportDocumentByTransferorCodeAndTransfereeCodeByPage(String transferorCode,
                                                                                                  String transfereeCode,
                                                                                                  Pageable pageable) {

        return transportDocumentRepository.findByTransferorCodeAndTransfereeCode(transferorCode, transfereeCode, pageable);
    }

    @Override
    public Page<TransportDocument> getAllTransportDocumentByTransferorCodeByPage(String transferorCode, Pageable pageable) {

        return transportDocumentRepository.findByTransferorCode(transferorCode, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<TransportDocument> getAllTransportDocumentByTransfereeCodeByPage(String transfereeCode, Pageable pageable) {

        return transportDocumentRepository.findByTransfereeCode(transfereeCode, pageable);
    }
}
