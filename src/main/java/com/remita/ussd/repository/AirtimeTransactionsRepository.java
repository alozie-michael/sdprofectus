package com.remita.ussd.repository;

import com.remita.ussd.model.AirtimeTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("AirtimeTransactionsRepository")
public interface AirtimeTransactionsRepository extends JpaRepository<AirtimeTransactions, Long> {

}
