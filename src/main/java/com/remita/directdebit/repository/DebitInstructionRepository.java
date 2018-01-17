package com.remita.directdebit.repository;

import com.remita.directdebit.model.DebitInstruction;
import com.remita.directdebit.model.Mandate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("DebitInstructionRepository")
public interface DebitInstructionRepository extends JpaRepository<DebitInstruction, Long> {

    DebitInstruction findByMandateIdContaining(String mandateId);
    DebitInstruction findByRequestIdContaining(String requestId);

}
