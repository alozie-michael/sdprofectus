package com.remita.ussd.repository;

import com.remita.ussd.model.TransferTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("TransferTransactionsRepository")
public interface TransferTransactionsRepository extends JpaRepository<TransferTransactions, Long> {

}
