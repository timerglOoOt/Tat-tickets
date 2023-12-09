<<<<<<< HEAD
package tat_tickets.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ticket {
    private Integer id;
    private String sectorName;
    private int seatNumber;
    private int price;
    private boolean status;
    private Integer gameId;
}
=======
package tat_tickets.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ticket {
    private Integer id;
    private Integer sectionId;
    private Integer seatId;
    private int price;
    private boolean status;
    private Integer gameId;
}
>>>>>>> d23f224 (feat: add done project)
