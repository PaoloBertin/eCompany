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

    @Transactional(readOnly = true)
    @Override
    public Page<TransportDocument> getAllTransportDocumentByTransfereeCodeByPage(String transfereeCode, Pageable pageable) {

        return transportDocumentRepository.findByTransfereeCode(transfereeCode, pageable);
    }
}
