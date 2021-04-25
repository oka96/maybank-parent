package com.maybank.controller;

import com.maybank.model.TxnRecordBO;
import com.maybank.service.TxnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final int pageSize = 5;

    @Autowired
    private TxnRecordService txnRecordService;

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("totalPages", 0);
        model.addAttribute("currentPage", 0);
        return "home";
    }

    @GetMapping("/filter")
    public String getFilter(Model model, String customerId, String accountNumber, String description, String pageNumber) {
        int currentPage = 0;
        if (pageNumber != null) {
            currentPage = Integer.valueOf(pageNumber);
        }
        List<TxnRecordBO> txnRecords = txnRecordService.getTransaction(customerId, accountNumber, description, currentPage, pageSize);
        int txnCount = txnRecordService.getTransactionCount(customerId, accountNumber, description);
        int totalPages = txnCount / pageSize;

        if (txnCount % pageSize != 0) {
            totalPages++;
        }

        model.addAttribute("filterCustomerId", customerId);
        model.addAttribute("filterAccountNumber", accountNumber);
        model.addAttribute("filterDescription", description);
        model.addAttribute("transactions", txnRecords);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        return "home";
    }

}
