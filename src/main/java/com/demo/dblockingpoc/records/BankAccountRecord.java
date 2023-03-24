package com.demo.dblockingpoc.records;

public record BankAccountRecord(Integer id, String accountNumber, String name, Double balance) {

    public BankAccountRecord(String name) {
        this(null, null, name, 0D);
    }

}
