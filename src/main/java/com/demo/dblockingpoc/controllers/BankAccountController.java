package com.demo.dblockingpoc.controllers;

import com.demo.dblockingpoc.records.AccountTransaction;
import com.demo.dblockingpoc.records.BankAccountRecord;
import com.demo.dblockingpoc.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class BankAccountController {

    private final BankAccountService service;

    public BankAccountController(BankAccountService service) {
        this.service = service;
    }

    @GetMapping("/name/{name}/number/{accountNumber}")
    public BankAccountRecord checkBalance(@PathVariable("name") String name,
                                          @PathVariable("accountNumber") String accountNumber) {
        System.out.println("CHECK BALANCE");
        return service.checkBalance(new BankAccountRecord(accountNumber, name));
    }

    @PostMapping
    public BankAccountRecord create(@RequestBody BankAccountRecord account) {
        System.out.println("CREATING BANK ACCOUNT");
        return service.create(account);
    }

    @PutMapping("/transaction")
    public AccountTransaction withdrawal(@RequestBody AccountTransaction transaction) {
        System.out.println("RUNNING TRANSACTION " + transaction);
        return service.accountTransaction(transaction);
    }

}
