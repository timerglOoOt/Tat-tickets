<<<<<<< HEAD
package tat_tickets.dto;

import lombok.Builder;
import lombok.Data;
import tat_tickets.models.TicketStatus;


@Builder
@Data
public class TicketDto {
    private Integer ticketId;
    private String sectorName;
    private int seatNumber;
    private int price;
    private TicketStatus status;
    private Integer gameId;
}
=======
package tat_tickets.dto;

import lombok.Builder;
import lombok.Data;
import tat_tickets.models.TicketStatus;


@Builder
@Data
public class TicketDto {
    private Integer ticketId;
    private Integer sectionId;
    private Integer seatId;
    private int price;
    private TicketStatus status;
    private Integer gameId;
}
>>>>>>> d23f224 (feat: add done project)
