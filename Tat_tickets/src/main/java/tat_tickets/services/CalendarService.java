package tat_tickets.services;

import tat_tickets.dto.GameDto;
import tat_tickets.dto.UserDto;
import tat_tickets.models.Game;

import java.util.List;

public interface CalendarService {
    GameDto getGameById(Integer id);
    List<GameDto> getAllGames();
}
