package com.demo.dblockingpoc.controllers;

import com.demo.dblockingpoc.records.TicketRecord;
import com.demo.dblockingpoc.services.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public TicketRecord getById(@PathVariable("id") Integer id) {
        System.out.println("GET ID " + id);
        return service.getById(id);
    }

    @GetMapping
    public List<TicketRecord> getAll() {
        System.out.println("GET ALL REQUEST ");
        return service.getAll();
    }

    @PostMapping
    public TicketRecord save(@RequestBody TicketRecord ticketR) {
        System.out.println("POST REQUEST " + ticketR);
        return service.save(ticketR);
    }

    @PutMapping
    public TicketRecord update(@RequestBody TicketRecord ticketR) {
        System.out.println("UPDATE REQUEST " + ticketR);
        return service.update(ticketR);
    }

}
