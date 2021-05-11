package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.domain.Invoice;
import it.opensource.ecompany.service.InvoicesService;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.web.form.InvoiceHomeForm;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/admin/invoices")
@Controller
public class InvoiceController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserContext userContext;

    private final InvoicesService invoicesService;

    @GetMapping("/number")
    public String getNumberInvoices(Model uiModel) {

        log.debug("retrieves the number of invoices");

        long numberInvoices = invoicesService.getNumberInvoices();

        uiModel.addAttribute("numberInvoices", numberInvoices);

        return "invoices/invoice";
    }

    @GetMapping("/all/home")
    public String getInvoicesHome(Model uiModel) {

        log.debug("retrieves home page of invices");

        Customer customer = userContext.getCurrentCustomer();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("invoiceHomeForm", new InvoiceHomeForm());

        return "invoices/invoicesHome";
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

        log.debug("search all invoices by transferor=" + transferorCode + " and transfereeCode=" + transfereeCode);

        Page<Invoice> invoices = null;
        if ((!transferorCode.toLowerCase()
                            .equals("all")) && (!transfereeCode.toLowerCase()
                                                               .equals("all"))) {
            invoices = invoicesService.getAllInvoicesByTransferorCodeAndTransfereeCodeByPage(transferorCode, transfereeCode, pageable);
            log.debug("found the invoices by transferor and transferee");
        }
        if ((!transferorCode.toLowerCase()
                            .equals("all")) && (transfereeCode.toLowerCase()
                                                              .equals("all"))) {
            invoices = invoicesService.getAllInvoicesByTransferorCodeByPage(transferorCode, pageable);
            log.debug("found the invoices by transferor");
        }
        if ((transferorCode.toLowerCase()
                           .equals("all")) && (!transfereeCode.toLowerCase()
                                                              .equals("all"))) {
            invoices = invoicesService.getAllInvoicesByTransfereeCodeByPage(transfereeCode, pageable);
            log.debug("found the invoices by transferee");
        }
        if ((transferorCode.toLowerCase()
                           .equals("all")) && (transfereeCode.toLowerCase()
                                                             .equals("all"))) {
            invoices = invoicesService.getAllInvoicesByPage(pageable);
            log.debug("found the invoices by transferee");
        }

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("invoices", invoices);

        return "invoices/invoicesList";
    }
}
