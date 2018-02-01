package com.solutionsdelivery.RPG.repository;

import com.solutionsdelivery.RPG.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PaymentRepository")
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
