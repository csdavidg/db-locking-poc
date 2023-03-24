package com.demo.dblockingpoc.services;

import com.demo.dblockingpoc.entities.BankAccount;
import com.demo.dblockingpoc.records.AccountTransaction;
import com.demo.dblockingpoc.records.BankAccountRecord;
import com.demo.dblockingpoc.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankAccountService {

    private final BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AccountTransaction accountTransaction(AccountTransaction transaction) {

        BankAccount e;
        double newBalance;

        switch (transaction.type()) {
            case DEBIT -> {
                e = repository.findByAccountNumberAndName(transaction.accountNumber(), transaction.name())
                        .orElseThrow();
                newBalance = e.getBalance() - transaction.amount();
                if (newBalance < 0) {
                    throw new IllegalArgumentException("Insufficient funds");
                }
            }
            case CREDIT -> {
                e = repository.findByAccountNumber(transaction.accountNumber())
                        .orElseThrow();
                newBalance = e.getBalance() + transaction.amount();
            }
            default -> throw new IllegalArgumentException("Invalid transaction type");
        }

        e.setBalance(newBalance);
        repository.save(e);
        return new AccountTransaction(transaction.accountNumber(), transaction.name(), transaction.type(), newBalance);
    }

    public BankAccountRecord create(BankAccountRecord account) {
        BankAccount e = repository.save(BankAccount.builder()
                .accountNumber(account.accountNumber())
                .name(account.name())
                .balance(account.balance())
                .build());
        return new BankAccountRecord(e.getId(), e.getAccountNumber(), e.getName(), e.getBalance());
    }

    public BankAccountRecord checkBalance(BankAccountRecord account) {
        return repository.findByName(account.name())
                .map(ba -> new BankAccountRecord(ba.getId(), ba.getAccountNumber(), ba.getName(), ba.getBalance()))
                .orElseThrow();
    }
}
