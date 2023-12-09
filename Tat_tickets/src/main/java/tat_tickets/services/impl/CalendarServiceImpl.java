package tat_tickets.services.impl;

import tat_tickets.dao.FileInfoRepository;
import tat_tickets.dao.GameRepository;
import tat_tickets.dao.UserRepository;
import tat_tickets.dto.GameDto;
import tat_tickets.dto.UserDto;
import tat_tickets.models.FileInfo;
import tat_tickets.models.Game;
import tat_tickets.models.User;
import tat_tickets.services.CalendarService;
import tat_tickets.services.ProfileService;
import tat_tickets.utils.mappers.GameMapper;
import tat_tickets.utils.mappers.UserMapper;
import tat_tickets.utils.mappers.impl.GameMapperImpl;
import tat_tickets.utils.mappers.impl.UserMapperImpl;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class CalendarServiceImpl implements CalendarService {
    private final GameRepository gameRepository;
    private GameMapper gameMapper = new GameMapperImpl();

    public CalendarServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public GameDto getGameById(Integer id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.map(value -> gameMapper.toDto(value)).orElse(null);
    }

    @Override
    public List<GameDto> getAllGames() {
        return gameRepository.findAll().stream().map(game -> gameMapper.toDto(game)).collect(Collectors.toList());
    }
}
