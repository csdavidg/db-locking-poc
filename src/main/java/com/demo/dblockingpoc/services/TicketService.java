package com.demo.dblockingpoc.services;

import com.demo.dblockingpoc.entities.Ticket;
import com.demo.dblockingpoc.records.TicketRecord;
import com.demo.dblockingpoc.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public TicketRecord getById(Integer id) {
        return repository.findById(id)
                .map(e -> new TicketRecord(e.getId(), e.getPosition(), e.getName()))
                .orElseThrow();
    }

    public List<TicketRecord> getAll() {
        return repository.findAll().stream()
                .map(e -> new TicketRecord(e.getId(), e.getPosition(), e.getName()))
                .collect(Collectors.toList());
    }

    public TicketRecord save(TicketRecord ticketR) {
        Ticket ticketE = repository.save(Ticket.builder()
                .position(ticketR.position())
                .name(ticketR.name())
                .build());
        return new TicketRecord(ticketE.getId(), ticketE.getPosition(), ticketE.getName());
    }

    public TicketRecord update(TicketRecord ticketR) {
        Ticket e = repository.findById(ticketR.id())
                .orElseThrow();

        e.setName(ticketR.name());
        e.setPosition(ticketR.position());
        repository.save(e);
        return ticketR;
    }
}
