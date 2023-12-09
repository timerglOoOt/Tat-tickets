<<<<<<< HEAD
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
=======
package tat_tickets.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
@Builder
public class GameDto {
    private Integer id;
    private Integer numberOfAvailableSeats;
    private String opposingTeamName;
    private Date date;
}
>>>>>>> d23f224 (feat: add done project)
