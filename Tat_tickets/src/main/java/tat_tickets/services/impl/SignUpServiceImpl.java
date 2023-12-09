<<<<<<< HEAD
package tat_tickets.services.impl;

import tat_tickets.dao.UserRepository;
import tat_tickets.dto.SignUpForm;
import tat_tickets.dto.UserDto;
import tat_tickets.models.User;
import tat_tickets.services.PasswordEncoder;
import tat_tickets.services.SignUpService;
import tat_tickets.services.validation.ErrorEntity;
import tat_tickets.services.validation.Validator;
import tat_tickets.utils.exceptions.TicketsException;
import tat_tickets.utils.exceptions.ValidationException;
import tat_tickets.utils.mappers.UserMapper;
import tat_tickets.utils.mappers.impl.UserMapperImpl;

import java.util.Optional;

public class SignUpServiceImpl implements SignUpService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;
    private UserMapper userMapper = new UserMapperImpl();

    public SignUpServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, Validator validator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }
    @Override
    public UserDto signUp(SignUpForm form) throws TicketsException {
        if (form.getEmail() == null) {
            throw new TicketsException("Email cannot be null");
        }
        Optional<User> optionalUser = userRepository.findByEmail(form.getEmail());
        if (optionalUser.isPresent()) {
            throw new TicketsException("User with email " + form.getEmail() + " already exist");
        }
        form.setPassword(passwordEncoder.hash(form.getPassword()));
        System.out.println(form);
        User user = userMapper.toUser(form);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
=======
package tat_tickets.services.impl;

import tat_tickets.dao.UserRepository;
import tat_tickets.dto.SignUpForm;
import tat_tickets.dto.UserDto;
import tat_tickets.models.User;
import tat_tickets.services.PasswordEncoder;
import tat_tickets.services.SignUpService;
import tat_tickets.services.validation.ErrorEntity;
import tat_tickets.services.validation.Validator;
import tat_tickets.utils.exceptions.TicketsException;
import tat_tickets.utils.exceptions.ValidationException;
import tat_tickets.utils.mappers.UserMapper;
import tat_tickets.utils.mappers.impl.UserMapperImpl;

import java.util.Optional;

public class SignUpServiceImpl implements SignUpService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;
    private UserMapper userMapper = new UserMapperImpl();

    public SignUpServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, Validator validator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }
    @Override
    public UserDto signUp(SignUpForm form) throws TicketsException {
        if (form.getEmail() == null) {
            throw new TicketsException("Email cannot be null");
        }
        Optional<User> optionalUser = userRepository.findByEmail(form.getEmail());
        if (optionalUser.isPresent()) {
            throw new TicketsException("User with email " + form.getEmail() + " already exist");
        }
        form.setPassword(passwordEncoder.hash(form.getPassword()));
        User user = userMapper.toUser(form);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
>>>>>>> d23f224 (feat: add done project)
