package com.service.Kirana.Services.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.Kirana.DAO.TransactionRepository;
import com.service.Kirana.Entity.Transaction;
import com.service.Kirana.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {


    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction getTransaction(Integer id) {
        return transactionRepository.findById(id).get();
    }

    @Override
    public List<Transaction> getDebitedTransaction() {
        return transactionRepository.findByTransactionType("debited");
    }

    @Override
    public List<Transaction> getCreditedTransaction() {
        return transactionRepository.findByTransactionType("credited");
    }

    //Adding Transactional annotation in only below functions as above function don't have any queries to rollback.

    @Override
    @Transactional
    public Transaction saveTransaction(Transaction transaction) {
        if(!transaction.getTransactionType().toLowerCase().equals("usd")){
            transaction.setAmount(currencyConverter(transaction.getCurrencyType(),transaction.getAmount()));
            transaction.setCurrencyType("USD");
        }
        transaction.setTransactionType(transaction.getTransactionType().toLowerCase());
        return transactionRepository.save(transaction);
    }
    @Override
    @Transactional
    public Transaction updateTransaction(Integer id, Transaction transaction) {
        if(id==transaction.getTransactionID()){
        if(!transaction.getTransactionType().toLowerCase().equals("usd") && transaction.getAmount()!=null){
            transaction.setAmount(currencyConverter(transaction.getCurrencyType(),transaction.getAmount()));
            transaction.setCurrencyType("USD");
        }
        Transaction oldTransaction= transactionRepository.findById(id).get();
        if(oldTransaction!=null) {
            if(transaction.getUser()==null)transaction.setUser(oldTransaction.getUser());
            if(transaction.getAmount()==null)
            {   transaction.setAmount(oldTransaction.getAmount());
                transaction.setCurrencyType(oldTransaction.getCurrencyType());
            }
            if(transaction.getDate()==null)transaction.setDate(oldTransaction.getDate());
            if(transaction.getTransactionType()==null) transaction.setTransactionType(oldTransaction.getTransactionType());
            if(transaction.getCurrencyType()==null)transaction.setCurrencyType(oldTransaction.getCurrencyType());
            transaction.setTransactionType(transaction.getTransactionType().toLowerCase());
            return transactionRepository.save(transaction);
        }}
        return null;
    }

    @Override
    @Transactional
    public String deleteTransaction(Integer id) {

        String status="Not Deleted";
        if(transactionRepository.findById(id).isPresent()) {
            transactionRepository.deleteById(id);
            status="Deleted";
        }
        return status;
    }

    private Double currencyConverter(String currency,Double amount){
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity<Map> currencies=restTemplate.getForEntity("https://api.fxratesapi.com/latest",Map.class);
        ObjectMapper oMapper = new ObjectMapper();

        // object -> Map
        Map<String, Object> currenciesValue = oMapper.convertValue(currencies.getBody().get("rates"), Map.class);

        DecimalFormat decimalFromat = new DecimalFormat("####0.00");
        Double updatedAmount=amount/Double.valueOf(currenciesValue.get(currency.toUpperCase()).toString());
        return Double.valueOf(decimalFromat.format(updatedAmount));
    }

}
