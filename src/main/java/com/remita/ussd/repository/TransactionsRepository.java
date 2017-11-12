package com.remita.ussd.repository;

import com.remita.ussd.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("TransactionsRepository")
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {


}
