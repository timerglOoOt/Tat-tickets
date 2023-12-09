package tat_tickets.utils.mappers.impl;

import tat_tickets.dto.BookingDto;
import tat_tickets.models.Booking;
import tat_tickets.utils.mappers.BookingMapper;

public class BookingMapperImpl implements BookingMapper {
    @Override
    public BookingDto toDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .bookingDate(booking.getBookingDate())
                .userId(booking.getUserId())
                .ticketId(booking.getTicketId())
                .gameName(booking.getGameName())
                .build();
    }

    @Override
    public Booking toBooking(BookingDto dto) {
        return Booking.builder()
                .id(dto.getId())
                .bookingDate(dto.getBookingDate())
                .userId(dto.getUserId())
                .ticketId(dto.getTicketId())
                .gameName(dto.getGameName())
                .build();
    }
}
