package com.demo.dblockingpoc.repositories;

import com.demo.dblockingpoc.entities.BankAccount;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    @Lock(LockModeType.OPTIMISTIC)
    Optional<BankAccount> findByAccountNumber(String accountNumber);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<BankAccount> findByAccountNumberAndName(String accountNumber, String name);

    Optional<BankAccount> findByName(String name);

}
