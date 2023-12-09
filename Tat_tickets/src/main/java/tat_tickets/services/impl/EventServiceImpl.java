package tat_tickets.services.impl;

import tat_tickets.dao.*;
import tat_tickets.dto.SeatDto;
import tat_tickets.dto.SectionDto;
import tat_tickets.models.Booking;
import tat_tickets.models.Game;
import tat_tickets.models.Seat;
import tat_tickets.models.Ticket;
import tat_tickets.services.EventService;
import tat_tickets.utils.mappers.SeatMapper;
import tat_tickets.utils.mappers.SectionMapper;
import tat_tickets.utils.mappers.impl.SeatMapperImpl;
import tat_tickets.utils.mappers.impl.SectionMapperImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static tat_tickets.utils.Config.HOMECOMMAND_NAME;

public class EventServiceImpl implements EventService {
    private static final int DEFAULT_PRICE = 500;
    private final SectionRepository sectionRepository;
    private SectionMapper sectionMapper = new SectionMapperImpl();
    private final SeatRepository seatRepository;
    private SeatMapper seatMapper = new SeatMapperImpl();
    private final TicketRepository ticketRepository;
    private final BookingRepository bookingRepository;
    private final GameRepository gameRepository;


    public EventServiceImpl(SectionRepository sectionRepository, SeatRepository seatRepository, TicketRepository ticketRepository, BookingRepository bookingRepository, GameRepository gameRepository) {
        this.sectionRepository = sectionRepository;
        this.seatRepository = seatRepository;
        this.ticketRepository = ticketRepository;
        this.bookingRepository = bookingRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public List<SeatDto> getSeatsBySectionId(Integer id) {
        List<Seat> seats = seatRepository.findAllBySectionId(id);
        return seats.stream().map(seat -> seatMapper.toDto(seat)).collect(Collectors.toList());
    }

    @Override
    public List<SectionDto> getAllSections() {
        return sectionRepository.findAll().stream().map(section -> sectionMapper.toDto(section)).collect(Collectors.toList());
    }

    @Override
    public List<Integer> getAllReservedSeatsInGame(Integer gameId) {
        return ticketRepository.findByGameId(gameId).stream().map(Ticket::getSeatId).collect(Collectors.toList());
    }

    @Override
    public SeatDto getSeatById(Integer seatId) {
        return seatRepository.findById(seatId).map(value -> seatMapper.toDto(value)).orElse(null);
    }

    @Override
    public void reserveSeat(Integer sectionId, Integer seatId, Integer userId, Integer gameId) {
        Ticket ticket = ticketRepository.save(Ticket.builder()
                        .sectionId(sectionId)
                        .seatId(seatId)
                        .gameId(gameId)
                        .price(DEFAULT_PRICE)
                        .status(true)
                .build());
        Optional<Game> game = gameRepository.findById(ticket.getGameId());
        if (!game.isEmpty()){
            bookingRepository.save(Booking.builder()
                    .ticketId(ticket.getId())
                    .userId(userId)
                    .bookingDate(game.get().getDate())
                            .gameName(HOMECOMMAND_NAME + " vs " + game.get().getOpposingTeamName())
                    .build());
        }

    }
}
