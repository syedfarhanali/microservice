package com.learning.service.impl;

import com.learning.entity.Invoice;
import com.learning.repository.InvoiceRepository;
import com.learning.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ArindamN on 29/09/2015.
 */
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Invoice createInvoice(Invoice invoice){
        return invoiceRepository.save(invoice);
    }

}
