package tat_tickets.services.impl;

import tat_tickets.dao.UserRepository;
import tat_tickets.dto.SignUpForm;
import tat_tickets.dto.UserDto;
import tat_tickets.models.User;
import tat_tickets.services.PasswordEncoder;
import tat_tickets.services.SignUpService;
import tat_tickets.services.validation.ErrorEntity;
import tat_tickets.services.validation.Validator;
import tat_tickets.utils.exceptions.ValidationException;
import tat_tickets.utils.mappers.UserMapper;

import java.util.Optional;

public class SignUpServiceImpl implements SignUpService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;
    private UserMapper userMapper;

    public SignUpServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, Validator validator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }
    @Override
    public UserDto signUp(SignUpForm signUpForm) throws ValidationException {
        Optional<ErrorEntity> optionalError = validator.validateSignUp(signUpForm);
        if(optionalError.isPresent()) {
            throw new ValidationException(optionalError.get());
        }
        User user = User.builder()
                .email(signUpForm.getEmail())
                .firstName(signUpForm.getFirstName())
                .lastName(signUpForm.getLastName())
                .hashedPassword(passwordEncoder.hash(signUpForm.getPassword()))
                .build();
        userRepository.save(user);
        return userMapper.toDto(user);
    }
}
