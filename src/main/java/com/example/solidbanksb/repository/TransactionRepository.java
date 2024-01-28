package com.example.solidbanksb.repository;

import com.example.solidbanksb.model.Transaction.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> findByClientId(String client_id);
}
