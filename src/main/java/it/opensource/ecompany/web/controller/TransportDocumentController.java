package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.domain.TransportDocument;
import it.opensource.ecompany.service.TransportDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Profile("html")
@RequestMapping("/admin/transportdocuments")
@Controller
public class TransportDocumentController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private final TransportDocumentService transportDocumentService;

    public TransportDocumentController(TransportDocumentService transportDocumentService) {

        this.transportDocumentService = transportDocumentService;
    }

    @GetMapping("/number")
    public String getNumberTransportDocuments(Model uiModel) {

        log.debug("retrieves the number of transport documents");

        long numberDocuments = transportDocumentService.getNumberTransportDocuments();

        uiModel.addAttribute("numberDocuments", numberDocuments);

        return "transportDocuments/transportDocumentsList";
    }

    @GetMapping("/{transfereeCode}")
    public String getAllTransportDocumentByTransfereeCodeByPage(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable,
                                                                @PathVariable("transfereeCode") String transfereeCode, Model uiModel) {

        log.debug("search all transport documents by transfereeCode");

        Page<TransportDocument> transportDocuments = transportDocumentService.getAllTransportDocumentByTransfereeCodeByPage(transfereeCode, pageable);

        uiModel.addAttribute("transportDocuments", transportDocuments);

        return "transportDocuments/transportDocumentsList";
    }
}
