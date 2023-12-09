package tat_tickets.services;

import tat_tickets.dto.BookingDto;
import tat_tickets.dto.UserDto;
import tat_tickets.models.FileInfo;
import tat_tickets.models.User;

import java.io.InputStream;
import java.util.List;

public interface ProfileService {
    UserDto updateProfile(UserDto userDto);
    UserDto getUserByEmail(String email);
    List<BookingDto> getBookingsByUserId(Integer userId);
}
