package com.demo.dblockingpoc.services;

import com.demo.dblockingpoc.entities.BankAccount;
import com.demo.dblockingpoc.records.AccountTransaction;
import com.demo.dblockingpoc.records.BankAccountRecord;
import com.demo.dblockingpoc.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    private final BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository) {
        this.repository = repository;
    }

    public AccountTransaction accountTransaction(AccountTransaction transaction) {

        BankAccount e = repository.findByAccountNumber(transaction.accountNumber()).orElseThrow();
        double newBalance;

        switch (transaction.type()) {
            case DEBIT -> {
                newBalance = e.getBalance() - transaction.amount();
            }
            case CREDIT -> {
                System.out.println("Old value " + e.getBalance());
                newBalance = e.getBalance() + transaction.amount();
                if (newBalance < 0) {
                    throw new IllegalArgumentException("Not enough funds to withdraw");
                }
            }
            default -> throw new IllegalArgumentException("Invalid transaction type");
        }
        System.out.println("New value " + newBalance);
        e.setBalance(newBalance);
        repository.save(e);
        return new AccountTransaction(transaction.accountNumber(), transaction.type(), newBalance);
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
        return repository.findByAccountNumberAndName(account.accountNumber(), account.name())
                .map(ba -> new BankAccountRecord(ba.getId(), ba.getAccountNumber(), ba.getName(), ba.getBalance()))
                .orElseThrow();
    }
}
