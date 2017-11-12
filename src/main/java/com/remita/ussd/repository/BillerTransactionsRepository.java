package com.remita.ussd.repository;

import com.remita.ussd.model.BillerTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("BillerTransactionsRepository")
public interface BillerTransactionsRepository extends JpaRepository<BillerTransactions, Long> {

}
