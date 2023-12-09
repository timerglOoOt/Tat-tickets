package tat_tickets.utils.mappers.impl;

import tat_tickets.dto.SeatDto;
import tat_tickets.models.Seat;
import tat_tickets.utils.mappers.SeatMapper;

public class SeatMapperImpl implements SeatMapper {
    @Override
    public SeatDto toDto(Seat seat) {
        return SeatDto.builder()
                .id(seat.getId())
                .sectionId(seat.getSectionId())
                .name(seat.getName())
                .build();
    }

    @Override
    public Seat toSeat(SeatDto dto) {
        return Seat.builder()
                .id(dto.getId())
                .sectionId(dto.getSectionId())
                .name(dto.getName())
                .build();
    }
}
