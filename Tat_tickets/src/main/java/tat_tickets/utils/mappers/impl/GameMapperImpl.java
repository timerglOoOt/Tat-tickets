package tat_tickets.utils.mappers.impl;

import tat_tickets.dto.GameDto;
import tat_tickets.models.Game;
import tat_tickets.utils.mappers.GameMapper;

public class GameMapperImpl implements GameMapper {
    @Override
    public GameDto toDto(Game game) {
        return GameDto.builder()
                .id(game.getId())
                .numberOfAvailableSeats(game.getNumberOfAvailableSeats())
                .opposingTeamName(game.getOpposingTeamName())
                .date(game.getDate())
                .build();
    }

    @Override
    public Game toGame(GameDto dto) {
        return Game.builder()
                .id(dto.getId())
                .numberOfAvailableSeats(dto.getNumberOfAvailableSeats())
                .opposingTeamName(dto.getOpposingTeamName())
                .date(dto.getDate())
                .build();
    }
}
