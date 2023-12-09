<<<<<<< HEAD
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
=======
package tat_tickets.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
@Builder
public class BookingDto {
    private Integer id;
    private Date bookingDate;
    private Integer userId;
    private Integer ticketId;
    private String gameName;
}
>>>>>>> d23f224 (feat: add done project)
