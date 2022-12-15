package com.example.springmysqlcrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springmysqlcrud.model.Ticket;

public interface TicketRepo  extends JpaRepository<Ticket, Integer> {
    
}
