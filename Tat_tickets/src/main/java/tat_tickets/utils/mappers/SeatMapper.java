package tat_tickets.utils.mappers;

import tat_tickets.dto.SeatDto;
import tat_tickets.models.Seat;

public interface SeatMapper {
    SeatDto toDto(Seat seat);
    Seat toSeat(SeatDto dto);
}

