package tat_tickets.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Calendar;

@Data
@Builder
public class GameDto {
    private Integer id;
    private Integer numberOfAvailableSeats;
    private String opposingTeamName;
    private Calendar date;
}
