package com.demo.dblockingpoc.repositories;

import com.demo.dblockingpoc.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    Optional<BankAccount> findByAccountNumber(String accountNumber);

    Optional<BankAccount> findByAccountNumberAndName(String accountNumber, String name);

}
