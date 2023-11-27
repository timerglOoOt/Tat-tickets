package tat_tickets.services;

import tat_tickets.dto.SignInForm;
import tat_tickets.dto.UserDto;
import tat_tickets.utils.exceptions.ValidationException;

public interface SignInService {
    UserDto signIn(SignInForm signInForm) throws ValidationException;
}
