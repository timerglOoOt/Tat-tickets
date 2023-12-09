package tat_tickets.utils.mappers;

import tat_tickets.dto.GameDto;
import tat_tickets.dto.SignUpForm;
import tat_tickets.models.Game;

public interface GameMapper {
    GameDto toDto(Game game);
    Game toGame(GameDto dto);
}

