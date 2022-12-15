package com.example.springmysqlcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmysqlcrud.dao.TicketRepo;
import com.example.springmysqlcrud.model.Ticket;

@RestController
@CrossOrigin
public class TicketController {

    @Autowired
    TicketRepo repo;

    @PostMapping(path="/ticket", consumes = {"application/json"})
    public Ticket bookTicket(@RequestBody Ticket ticket) {
        repo.save(ticket);
        return ticket;
    }

    @GetMapping(path="/tickets", produces = {"application/json"})
    public List<Ticket> getTicket(){
        List<Ticket> ticketList = repo.findAll();
        return ticketList;
    }

    @GetMapping(path="/ticket/{id}", produces = {"application/json"})
    public Ticket getTicket(@PathVariable int id){
        Ticket ticket = repo.findById(id).orElse(new Ticket());
        return ticket;
    }

    @PutMapping(path="/ticket/{id}")
    public String updateTicket(@PathVariable int id, @RequestBody Ticket ticket){
        if(repo.findById(id).isPresent()){
            Ticket ticketUpdate = repo.findById(id).get();
            ticketUpdate.setAmount(ticket.getAmount());
            ticketUpdate.setCategory(ticket.getCategory());
            repo.save(ticketUpdate);
        }else{
            return "Invalid ticket id: "+ id;
        }
        return "Updated ticket id: "+ id;
    }

    @DeleteMapping(path="/ticket/{id}")
    public String deleteTicket(@PathVariable int id){
        if(repo.findById(id).isPresent()){
            Ticket ticket = repo.findById(id).get();
            repo.delete(ticket);
        }else{
            return "Invalid ticket id: "+ id;
        }
        return "Deleted ticket id: "+ id;
    }
}
