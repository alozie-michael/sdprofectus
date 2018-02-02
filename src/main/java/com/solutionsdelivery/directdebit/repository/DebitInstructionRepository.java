package com.solutionsdelivery.directdebit.repository;

import com.solutionsdelivery.directdebit.model.DebitInstruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("DebitInstructionRepository")
public interface DebitInstructionRepository extends JpaRepository<DebitInstruction, Long> {

    @Query("select d from DebitInstruction d where d.mandateId = ?1 OR d.requestId = ?1")
    DebitInstruction findByMandateIdContaining(String mandateIdOrRequestId);

}
