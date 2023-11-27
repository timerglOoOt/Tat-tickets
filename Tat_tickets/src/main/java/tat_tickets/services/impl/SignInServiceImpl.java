package tat_tickets.services.impl;

import tat_tickets.dao.UserRepository;
import tat_tickets.dto.SignInForm;
import tat_tickets.dto.UserDto;
import tat_tickets.models.User;
import tat_tickets.services.PasswordEncoder;
import tat_tickets.services.SignInService;
import tat_tickets.services.validation.ErrorEntity;
import tat_tickets.utils.exceptions.ValidationException;
import tat_tickets.utils.mappers.UserMapper;

public class SignInServiceImpl implements SignInService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    public SignInServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto signIn(SignInForm signInForm) throws ValidationException {
        User user = userRepository.findByEmail(signInForm.getEmail())
                .orElseThrow(() -> new ValidationException(ErrorEntity.NOT_FOUND));
        if (!passwordEncoder.matches(signInForm.getPassword(), user.getHashedPassword())) {
            throw new ValidationException(ErrorEntity.INCORRECT_PASSWORD);
        }
       return userMapper.toDto(user);
    }
}
