package tat_tickets.services.impl;

import tat_tickets.dao.BookingRepository;
import tat_tickets.dao.FileInfoRepository;
import tat_tickets.dao.UserRepository;
import tat_tickets.dto.BookingDto;
import tat_tickets.dto.UserDto;
import tat_tickets.models.FileInfo;
import tat_tickets.models.User;
import tat_tickets.services.ProfileService;
import tat_tickets.utils.mappers.BookingMapper;
import tat_tickets.utils.mappers.UserMapper;
import tat_tickets.utils.mappers.impl.BookingMapperImpl;
import tat_tickets.utils.mappers.impl.UserMapperImpl;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository;
    private final FileInfoRepository fileInfoRepository;
    private final BookingRepository bookingRepository;
    private UserMapper userMapper = new UserMapperImpl();
    private BookingMapper bookingMapper = new BookingMapperImpl();

    public ProfileServiceImpl(UserRepository userRepository, FileInfoRepository fileInfoRepository, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.fileInfoRepository = fileInfoRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public UserDto updateProfile(UserDto userDto) {
        User user = userMapper.toUser(userDto);

        if (user.getAvatarId() == 0){
            user.setAvatarId(null);
        }
        userRepository.update(user);

        return userMapper.toDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(value -> userMapper.toDto(value)).orElse(null);
    }

    @Override
    public List<BookingDto> getBookingsByUserId(Integer userId) {
        return bookingRepository.findByUserId(userId).stream().map(booking -> bookingMapper.toDto(booking)).collect(Collectors.toList());
    }
}
