<<<<<<< HEAD
package tat_tickets.models;

import java.util.Calendar;

public class Booking {
    private Integer id;
    private Calendar bookingDate;
    private Integer userId;
    private Integer ticketId;
}
=======
package tat_tickets.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private Integer id;
    private Date bookingDate;
    private Integer userId;
    private Integer ticketId;
    private String gameName;
}
>>>>>>> d23f224 (feat: add done project)
