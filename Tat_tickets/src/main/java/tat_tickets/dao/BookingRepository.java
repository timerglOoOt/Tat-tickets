package tat_tickets.dao;

import tat_tickets.models.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
    List<Booking> findByUserId(Integer userId);
    Booking save(Booking item);
}
