package tat_tickets.servlets;

import com.google.gson.Gson;
import tat_tickets.dto.SeatDto;
import tat_tickets.dto.UserDto;
import tat_tickets.services.EventService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static tat_tickets.utils.Config.SEAT_SEPARATION;

@WebServlet("/seatLayout")
public class GetSeatLayoutServlet extends HttpServlet {
    private EventService eventService;
    private Map<Integer, List<Integer>> reservedSeats;
    private Integer currentGameId;


    @Override
    public void init(ServletConfig config) throws ServletException {
        eventService = (EventService) config.getServletContext().getAttribute("eventService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] myJsonData = req.getParameterValues("json[]");
        String section = myJsonData[0];
        Integer id = Integer.parseInt(myJsonData[1]);
        if (reservedSeats == null){
            reservedSeats = new HashMap<>();
            reservedSeats.put(id, eventService.getAllReservedSeatsInGame(id));
            currentGameId = id;
        } else if (!reservedSeats.containsKey(id)) {
            reservedSeats.put(id, eventService.getAllReservedSeatsInGame(id));
        } else {
            if (!id.equals(currentGameId)){
                currentGameId = Integer.parseInt(myJsonData[1]);
                reservedSeats.put(currentGameId, eventService.getAllReservedSeatsInGame(id));
            }
        }
        String[][] seatLayout = getSeatLayoutFromDatabase(section);
        resp.setContentType("application/json");
        try (PrintWriter out = resp.getWriter()) {
            out.print(new Gson().toJson(seatLayout));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        String[] myJsonDataSeat = req.getParameterValues("jsonSeat[]");
        String[] myJsonDataGame = req.getParameterValues("jsonGame[]");

        Integer gameId = Integer.parseInt(myJsonDataGame[0]);

        for (String seatIdString : myJsonDataSeat) {
            Integer seatId = Integer.parseInt(seatIdString.trim());
            SeatDto seat = eventService.getSeatById(seatId);

            List<Integer> listToSave = reservedSeats.get(gameId);
            listToSave.add(seatId);
            reservedSeats.put(gameId, listToSave);
            eventService.reserveSeat(seat.getSectionId(), seat.getId(), user.getId(), gameId);
        }


        resp.setContentType("application/json");
        resp.getWriter().write("{\"success\": true}");
    }


    private String[][] getSeatLayoutFromDatabase(String section) {
        String[] sectionData = section.split(" ");
        Integer sectionId = Integer.parseInt(sectionData[1]);

        List<SeatDto> seatDtos = eventService.getSeatsBySectionId(sectionId);
        return transformSeatDtosToArrays(seatDtos);
    }

    private String[][] transformSeatDtosToArrays(List<SeatDto> seatDtos) {
        int numRows = (int) Math.ceil((double) seatDtos.size() / SEAT_SEPARATION);

        int lastRowSeats = seatDtos.size() % SEAT_SEPARATION;
        if (lastRowSeats == 0) {
            lastRowSeats = SEAT_SEPARATION;
        }

        String[][] seatArrays = new String[numRows][];

        int index = 0;
        for (int i = 0; i < numRows; i++) {
            int seatsInCurrentRow = (i == numRows - 1) ? lastRowSeats : SEAT_SEPARATION;
            seatArrays[i] = new String[seatsInCurrentRow];
            for (int j = 0; j < seatsInCurrentRow; j++) {
                if (index < seatDtos.size()) {
                    SeatDto seat = seatDtos.get(index);
                    if (seat != null) {
                        if (reservedSeats.get(currentGameId).contains(seat.getId())) {
                            seatArrays[i][j] = seat.getName() + "R";
                        } else {
                            seatArrays[i][j] = seat.getName();
                        }
                        seatArrays[i][j] += " " + seat.getId();
                        index++;
                    }
                }
            }
        }

        return seatArrays;
    }



}
