package tat_tickets.models;

import lombok.Builder;
import lombok.Data;

import java.util.Calendar;

@Data
@Builder
public class Game {
    private Integer id;
    private Integer numberOfAvailableSeats;
    private String opposingTeamName;
    private Calendar date;
}
//TODO добавить эмблему каждой команды противника по ее названию