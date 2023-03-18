package com.demo.dblockingpoc.records;

public record BankAccountRecord(Integer id, String accountNumber, String name, Double balance) {

    public BankAccountRecord(String accountNumber, String name) {
        this(null, accountNumber, name, 0D);
    }

}
