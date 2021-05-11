package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.domain.TransportDocument;
import it.opensource.ecompany.service.TransportDocumentService;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.web.form.TransportDocumentHomeForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Profile("html")
@RequestMapping("/admin/transportdocuments")
@Controller
public class TransportDocumentController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserContext userContext;

    private final TransportDocumentService transportDocumentService;

    public TransportDocumentController(UserContext userContext, TransportDocumentService transportDocumentService) {

        this.userContext = userContext;
        this.transportDocumentService = transportDocumentService;
    }

    @GetMapping("/number")
    public String getNumberTransportDocuments(Model uiModel) {

        log.debug("retrieves the number of transport documents");

        long numberDocuments = transportDocumentService.getNumberTransportDocuments();

        uiModel.addAttribute("numberDocuments", numberDocuments);

        return "transportDocuments/transportDocument";
    }

    @GetMapping("/all/home")
    public String getTransportDocumentsHome(Model uiModel) {

        log.debug("retrieves home page of transport documents");

        Customer customer = userContext.getCurrentCustomer();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("transportDocumentHomeForm", new TransportDocumentHomeForm());

        return "transportDocuments/transportDocumentsHome";
    }

    /**
     * @param pageable
     * @param transferorCode codice cedente
     * @param transfereeCode codice cessionario
     * @param uiModel
     * @return
     */
    @GetMapping
    public String getAllTransportDocumentByTransfereeCodeByPage(@RequestParam String transferorCode,
                                                                @RequestParam String transfereeCode,
                                                                @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable,
                                                                Model uiModel) {
        Customer customer = userContext.getCurrentCustomer();

        log.debug("search all transport documents by transferor=" + transferorCode + " and transfereeCode=" + transfereeCode);

        Page<TransportDocument> transportDocuments = null;
        if ((!transferorCode.toLowerCase().equals("all")) && (!transfereeCode.toLowerCase().equals("all"))) {
            transportDocuments = transportDocumentService.getAllTransportDocumentByTransferorCodeAndTransfereeCodeByPage(transferorCode, transfereeCode, pageable);
            log.debug("found the transport documents by transferor and transferee");
        }
        if ((!transferorCode.toLowerCase().equals("all")) && (transfereeCode.toLowerCase().equals("all"))) {
            transportDocuments = transportDocumentService.getAllTransportDocumentByTransferorCodeByPage(transferorCode, pageable);
            log.debug("found the transport documents by transferor");
        }
        if ((transferorCode.toLowerCase().equals("all")) && (!transfereeCode.toLowerCase().equals("all"))) {
            transportDocuments = transportDocumentService.getAllTransportDocumentByTransfereeCodeByPage(transfereeCode, pageable);
            log.debug("found the transport documents by transferee");
        }
        if ((transferorCode.toLowerCase().equals("all")) && (transfereeCode.toLowerCase().equals("all"))) {
            transportDocuments = transportDocumentService.getAllTransportDocumentByPage(pageable);
            log.debug("found the transport documents by transferee");
        }

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("transportDocuments", transportDocuments);

        return "transportDocuments/transportDocumentsList";
    }
}
