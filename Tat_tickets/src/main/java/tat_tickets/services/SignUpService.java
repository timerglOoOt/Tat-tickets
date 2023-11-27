package tat_tickets.services;

import tat_tickets.dto.SignUpForm;
import tat_tickets.dto.UserDto;
import tat_tickets.utils.exceptions.ValidationException;

public interface SignUpService {
    UserDto signUp(SignUpForm signUpForm) throws ValidationException;
}
