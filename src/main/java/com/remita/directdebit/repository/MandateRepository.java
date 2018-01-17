package com.remita.directdebit.repository;

import com.remita.directdebit.model.Mandate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("MandateRepository")
public interface MandateRepository extends JpaRepository<Mandate, Long> {

    Mandate findByMandateIdContaining(String mandateId);
}
