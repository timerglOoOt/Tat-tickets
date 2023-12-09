package tat_tickets.dao;

import tat_tickets.models.Ticket;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    List<Ticket> findByGameId(Integer gameId);
    List<Ticket> findByStatus(boolean status);
}

