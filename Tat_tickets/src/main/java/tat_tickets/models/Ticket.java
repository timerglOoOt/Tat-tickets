package tat_tickets.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ticket {
    private Integer id;
    private String sectorName;
    private int seatNumber;
    private double price;
    private boolean status;
    private Integer gameId;
}
