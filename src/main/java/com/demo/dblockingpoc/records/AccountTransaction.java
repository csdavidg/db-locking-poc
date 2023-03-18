package com.demo.dblockingpoc.records;

public record AccountTransaction(String accountNumber, TransactionType type, Double amount) {
}
