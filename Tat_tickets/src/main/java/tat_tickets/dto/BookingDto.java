package tat_tickets.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Calendar;

@Data
@Builder
public class BookingDto {
    private Integer id;
    private Calendar bookingDate;
    private Integer userId;
    private Integer ticketId;
}
