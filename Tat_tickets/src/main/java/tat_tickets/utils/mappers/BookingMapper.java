package tat_tickets.utils.mappers;

import tat_tickets.dto.BookingDto;
import tat_tickets.models.Booking;

public interface BookingMapper {
    BookingDto toDto(Booking booking);
    Booking toBooking(BookingDto dto);
}
