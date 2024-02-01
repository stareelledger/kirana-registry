package com.service.Kirana.Controllers;

import com.service.Kirana.Entity.Transaction;
import com.service.Kirana.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/{id}/")
    public Transaction getTransactions(@PathVariable String id){
        return transactionService.getTransaction(Integer.valueOf(id));
    }

    @GetMapping("/credited/")
    public List<Transaction> getCreditedTransactions(){
        return transactionService.getCreditedTransaction();
    }

    @GetMapping("/debited/")
    public List<Transaction> getDebitedTransactions(){
        return transactionService.getDebitedTransaction();
    }

    @PostMapping("/")
    public Transaction postTransactions(@RequestBody Transaction transaction){
        return  transactionService.saveTransaction(transaction);
    }
    @DeleteMapping("/{id}/")
    public String deleteTransactions(@PathVariable String id){
        return transactionService.deleteTransaction(Integer.valueOf(id));
    }
    @PutMapping("/{id}/")
    public Transaction updateTransactions(@PathVariable String id, @RequestBody Transaction transaction){
        return transactionService.updateTransaction(Integer.valueOf(id),transaction);
    }
}
