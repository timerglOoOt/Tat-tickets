package tat_tickets.services;

import tat_tickets.dto.GameDto;
import tat_tickets.dto.SeatDto;
import tat_tickets.dto.SectionDto;

import java.util.List;

public interface EventService {
    List<SeatDto> getSeatsBySectionId(Integer id);
    List<SectionDto> getAllSections();
    List<Integer> getAllReservedSeatsInGame(Integer gameId);
    SeatDto getSeatById(Integer seatId);
    void reserveSeat(Integer sectionId, Integer seatId, Integer userId, Integer gameId);
}
