package com.service.Kirana.Services;

import com.service.Kirana.DAO.TransactionRepository;
import com.service.Kirana.Entity.Transaction;
import com.service.Kirana.Services.Impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {


    @Mock
    private RestTemplate restTemplate;
    @Mock
    TransactionRepository transactionRepository;
    @InjectMocks
    TransactionServiceImpl transactionService;

    //Input to be used in every test cases
    private final Transaction transaction= Transaction.builder() //Common input for every test cases
            .transactionID(1)
            .transactionType("credited")
            .amount(500.0)
            .currencyType("INR")
            .date("2024-02-01T11:24:24.396+00:00")
            .user("NameTest").build();

    @Test
    void testGetTransaction(){

        Transaction expected= Transaction.builder()
                .transactionID(1)
                .transactionType("credited")
                .amount(500.0)
                .currencyType("INR")
                .date("2024-02-01T11:24:24.396+00:00")
                .user("NameTest").build();
        Mockito.when(transactionRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(transaction));
        Transaction result=transactionService.getTransaction(1);
        assertEquals(expected.getTransactionID(),result.getTransactionID());
        assertEquals(expected.getTransactionType(),result.getTransactionType());
        assertEquals(expected.getAmount(),result.getAmount());
        assertEquals(expected.getDate(),result.getDate());
        assertEquals(expected.getCurrencyType(),result.getCurrencyType());
        assertEquals(expected.getUser(),result.getUser());

    }

    @Test
    void testGetDebitedTransaction(){

        List<Transaction> testInput= List.of(Transaction.builder()
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
        Mockito.when(transactionRepository.findByTransactionType(Mockito.any())).thenReturn(testInput);
        List<Transaction> result=transactionService.getDebitedTransaction();
        assertEquals(expected.size(),result.size());
        assertEquals(expected.get(0).getTransactionID(),result.get(0).getTransactionID());
        assertEquals(expected.get(0).getTransactionType(),result.get(0).getTransactionType());
        assertEquals(expected.get(0).getAmount(),result.get(0).getAmount());
        assertEquals(expected.get(0).getDate(),result.get(0).getDate());
        assertEquals(expected.get(0).getCurrencyType(),result.get(0).getCurrencyType());
        assertEquals(expected.get(0).getUser(),result.get(0).getUser());

    }

    @Test
    void testGetCreditedTransaction(){

        List<Transaction> expected= List.of(Transaction.builder()
                .transactionID(1)
                .transactionType("credited")
                .amount(500.0)
                .currencyType("INR")
                .date("2024-02-01T11:24:24.396+00:00")
                .user("NameTest").build());
        Mockito.when(transactionRepository.findByTransactionType(Mockito.any())).thenReturn(List.of(transaction));
        List<Transaction> result=transactionService.getCreditedTransaction();
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

        Transaction expected= Transaction.builder()
                .transactionID(1)
                .transactionType("credited")
                .amount(6.03)
                .currencyType("USD")
                .date("2024-02-01T11:24:24.396+00:00")
                .user("NameTest").build();

        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);
        Transaction result=transactionService.saveTransaction(transaction);
        assertEquals(expected.getTransactionID(),result.getTransactionID());
        assertEquals(expected.getTransactionType(),result.getTransactionType());
        assertEquals(expected.getDate(),result.getDate());
        assertEquals(expected.getCurrencyType(),result.getCurrencyType());
        assertEquals(expected.getUser(),result.getUser());

    }

    @Test
    void testUpdateTransaction(){

        Transaction expected= Transaction.builder()
                .transactionID(1)
                .transactionType("credited")
                .amount(6.03)
                .currencyType("USD")
                .date("2024-02-01T11:24:24.396+00:00")
                .user("NameTest").build();

        Mockito.when(transactionRepository.findById(1)).thenReturn(Optional.ofNullable(transaction));
        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);
        Transaction result=transactionService.updateTransaction(1,transaction);
        assertEquals(expected.getTransactionID(),result.getTransactionID());
        assertEquals(expected.getTransactionType(),result.getTransactionType());
        assertEquals(expected.getDate(),result.getDate());
        assertEquals(expected.getCurrencyType(),result.getCurrencyType());
        assertEquals(expected.getUser(),result.getUser());

    }

    @Test
    void testDeleteTransaction(){

        Mockito.when(transactionRepository.findById(1)).thenReturn(Optional.ofNullable(transaction));

        doNothing().when(transactionRepository).deleteById(1);
        String result=transactionService.deleteTransaction(1);
        assertEquals(result,"Deleted");

    }
}
