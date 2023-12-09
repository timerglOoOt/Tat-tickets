package tat_tickets.dao;

import tat_tickets.models.Seat;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends CrudRepository<Seat, Integer> {
    Optional<Seat> findById(Integer id);
    List<Seat> findAll();
    List<Seat> findAllBySectionId(Integer id);
    Seat save(Seat item);
    void update(Seat item);
    void delete(Integer id);
}
