package com.solutionsdelivery.directdebit.repository;

import com.solutionsdelivery.directdebit.model.DebitInstruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("DebitInstructionRepository")
public interface DebitInstructionRepository extends JpaRepository<DebitInstruction, Long> {

    DebitInstruction findByMandateIdContaining(String mandateId);
    DebitInstruction findByRequestIdContaining(String requestId);

}
