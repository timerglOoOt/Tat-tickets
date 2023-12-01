package tat_tickets.services.impl;

import tat_tickets.dao.UserRepository;
import tat_tickets.dto.SignInForm;
import tat_tickets.dto.UserDto;
import tat_tickets.models.User;
import tat_tickets.services.PasswordEncoder;
import tat_tickets.services.SignInService;
import tat_tickets.services.validation.ErrorEntity;
import tat_tickets.utils.exceptions.TicketsException;
import tat_tickets.utils.exceptions.ValidationException;
import tat_tickets.utils.mappers.UserMapper;
import tat_tickets.utils.mappers.impl.UserMapperImpl;

import java.util.Optional;

public class SignInServiceImpl implements SignInService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private UserMapper userMapper = new UserMapperImpl();

    public SignInServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto signIn(SignInForm form) throws TicketsException {
        if(form.getEmail() == null) {
            throw new TicketsException("Email cannot be null");
        }
        Optional<User> optionalUser = userRepository.findByEmail(form.getEmail());
        if(optionalUser.isEmpty()) {
            throw new TicketsException("User with email " + form.getEmail() + " not found.");
        }
        User user = optionalUser.get();
        if(!passwordEncoder.matches(form.getPassword(), user.getHashedPassword())) {
            throw new TicketsException("Wrong password");
        }
        return userMapper.toDto(user);
    }
}
