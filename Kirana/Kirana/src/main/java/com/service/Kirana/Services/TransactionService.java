package com.service.Kirana.Services;

import com.service.Kirana.Entity.Transaction;

import java.util.List;

public interface TransactionService
{
    Transaction saveTransaction(Transaction transaction);
    Transaction getTransaction(Integer id);
    List<Transaction> getDebitedTransaction();
    List<Transaction> getCreditedTransaction();
    Transaction updateTransaction(Integer id,Transaction transaction);
    String deleteTransaction(Integer id);

}
