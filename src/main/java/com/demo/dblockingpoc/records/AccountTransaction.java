package com.demo.dblockingpoc.records;

public record AccountTransaction(String accountNumber, String name, TransactionType type, Double amount) {
}
