package org.zapovednik.groupservice.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zapovednik.groupservice.model.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {

}