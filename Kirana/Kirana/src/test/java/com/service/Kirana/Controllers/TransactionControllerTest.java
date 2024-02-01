package com.service.Kirana.Controllers;

import com.service.Kirana.Entity.Transaction;
import com.service.Kirana.Services.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @Mock
    TransactionService transactionService;
    @InjectMocks
    TransactionController transactionController;

    //Input to be used in every test cases
    private final Transaction transaction= Transaction.builder() //Common input for every test cases
            .transactionID(1)
            .transactionType("credited")
            .amount(500.0)
            .currencyType("INR")
            .date("2024-02-01T11:24:24.396+00:00")
            .user("NameTest").build();

    @Test
    void getTransactionTest(){
        Transaction expected= Transaction.builder()
                .transactionID(1)
                .transactionType("credited")
                .amount(500.0)
                .currencyType("USD")
                .date("2024-02-01T11:24:24.396+00:00")
                .user("NameTest").build();
        Mockito.when(transactionService.getTransaction(Mockito.any())).thenReturn(transaction);
        Transaction result=transactionController.getTransactions("1");
        assertEquals(expected.getTransactionID(),result.getTransactionID());
        assertEquals(expected.getTransactionType(),result.getTransactionType());
        assertEquals(expected.getAmount(),result.getAmount());
        assertEquals(expected.getDate(),result.getDate());
        assertEquals(expected.getUser(),result.getUser());
    }

    @Test
    void testGetCreditedTransaction(){

        List<Transaction> mockReturnValue= List.of(Transaction.builder()
                .transactionID(1)
                .transactionType("credited")
                .amount(500.0)
                .currencyType("INR")
                .date("2024-02-01T11:24:24.396+00:00")
                .user("NameTest").build());

        List<Transaction> expected= List.of(Transaction.builder()
                .transactionID(1)
                .transactionType("credited")
                .amount(500.0)
                .currencyType("INR")
                .date("2024-02-01T11:24:24.396+00:00")
                .user("NameTest").build());
        Mockito.when(transactionService.getCreditedTransaction()).thenReturn(mockReturnValue);
        List<Transaction> result=transactionController.getCreditedTransactions();
        assertEquals(expected.size(),result.size());
        assertEquals(expected.get(0).getTransactionID(),result.get(0).getTransactionID());
        assertEquals(expected.get(0).getTransactionType(),result.get(0).getTransactionType());
        assertEquals(expected.get(0).getAmount(),result.get(0).getAmount());
        assertEquals(expected.get(0).getDate(),result.get(0).getDate());
        assertEquals(expected.get(0).getCurrencyType(),result.get(0).getCurrencyType());
        assertEquals(expected.get(0).getUser(),result.get(0).getUser());

    }

    @Test
    void testGetDebitedTransaction(){

        List<Transaction> mockReturnValue= List.of(Transaction.builder()
                .transactionID(1)
                .transactionType("debited")
                .amount(500.0)
                .currencyType("INR")
                .date("2024-02-01T11:24:24.396+00:00")
                .user("NameTest").build());

        List<Transaction> expected= List.of(Transaction.builder()
                .transactionID(1)
                .transactionType("debited")
                .amount(500.0)
                .currencyType("INR")
                .date("2024-02-01T11:24:24.396+00:00")
                .user("NameTest").build());
        Mockito.when(transactionService.getDebitedTransaction()).thenReturn(mockReturnValue);
        List<Transaction> result=transactionController.getDebitedTransactions();
        assertEquals(expected.size(),result.size());
        assertEquals(expected.get(0).getTransactionID(),result.get(0).getTransactionID());
        assertEquals(expected.get(0).getTransactionType(),result.get(0).getTransactionType());
        assertEquals(expected.get(0).getAmount(),result.get(0).getAmount());
        assertEquals(expected.get(0).getDate(),result.get(0).getDate());
        assertEquals(expected.get(0).getCurrencyType(),result.get(0).getCurrencyType());
        assertEquals(expected.get(0).getUser(),result.get(0).getUser());

    }

    @Test
    void testSaveTransaction(){

        Transaction mockReturnValue= Transaction.builder()
                .transactionID(1)
                .transactionType("credited")
                .amount(6.03)
                .currencyType("USD")
                .date("2024-02-01T11:24:24.396+00:00")
                .user("NameTest").build();

        Transaction expected= Transaction.builder()
                .transactionID(1)
                .transactionType("credited")
                .amount(6.03)
                .currencyType("USD")
                .date("2024-02-01T11:24:24.396+00:00")
                .user("NameTest").build();
        Mockito.when(transactionService.saveTransaction(Mockito.any())).thenReturn(mockReturnValue);
        Transaction result=transactionController.postTransactions(transaction);
        assertEquals(expected.getTransactionID(),result.getTransactionID());
        assertEquals(expected.getTransactionType(),result.getTransactionType());
        assertEquals(expected.getAmount(),result.getAmount());
        assertEquals(expected.getDate(),result.getDate());
        assertEquals(expected.getCurrencyType(),result.getCurrencyType());
        assertEquals(expected.getUser(),result.getUser());

    }

    @Test
    void testUpdateTransaction(){

        Transaction mockReturnValue= Transaction.builder()
                .transactionID(1)
                .transactionType("credited")
                .amount(6.03)
                .currencyType("USD")
                .date("2024-02-01T11:24:24.396+00:00")
                .user("NameTest").build();

        Transaction expected= Transaction.builder()
                .transactionID(1)
                .transactionType("credited")
                .amount(6.03)
                .currencyType("USD")
                .date("2024-02-01T11:24:24.396+00:00")
                .user("NameTest").build();

        Mockito.when(transactionService.updateTransaction(Mockito.any(),Mockito.any())).thenReturn(mockReturnValue);
        Transaction result=transactionController.updateTransactions("1",transaction);
        assertEquals(expected.getTransactionID(),result.getTransactionID());
        assertEquals(expected.getTransactionType(),result.getTransactionType());
        assertEquals(expected.getAmount(),result.getAmount());
        assertEquals(expected.getDate(),result.getDate());
        assertEquals(expected.getCurrencyType(),result.getCurrencyType());
        assertEquals(expected.getUser(),result.getUser());

    }

    @Test
    void testDeleteTransaction(){

        Mockito.when(transactionService.deleteTransaction(1)).thenReturn("Deleted");
        String result=transactionController.deleteTransactions("1");
        assertEquals(result,"Deleted");

    }




}
