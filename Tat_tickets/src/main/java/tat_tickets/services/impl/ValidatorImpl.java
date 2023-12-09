<<<<<<< HEAD
package tat_tickets.services.impl;

import tat_tickets.dao.UserRepository;
import tat_tickets.dto.SignInForm;
import tat_tickets.dto.SignUpForm;
import tat_tickets.services.validation.ErrorEntity;
import tat_tickets.services.validation.Validator;

import java.util.Optional;

public class ValidatorImpl implements Validator {
    private final UserRepository userRepository;
    public ValidatorImpl(UserRepository userRepository) { this.userRepository = userRepository; }
    @Override
    public Optional<ErrorEntity> validateSignUp(SignUpForm signUpForm) {
        if (signUpForm.getEmail() == null) {
            return Optional.of(ErrorEntity.INVALID_EMAIL);
        }
        if (userRepository.findByEmail(signUpForm.getEmail()).isPresent()) {
            return Optional.of(ErrorEntity.EMAIL_ALREADY_EXISTS);
        }
        if (signUpForm.getPassword() == null) {
            return Optional.of(ErrorEntity.INCORRECT_PASSWORD);
        }
        if (signUpForm.getPassword().length() < 8) {
            return Optional.of(ErrorEntity.PASSWORD_TOO_SIMPLE);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ErrorEntity> validateSignIn(SignInForm signInForm) {
        if (signInForm.getEmail() == null) {
            return Optional.of(ErrorEntity.INVALID_EMAIL);
        }
        if (signInForm.getPassword() == null) {
            return Optional.of(ErrorEntity.INCORRECT_PASSWORD);
        }
        if (userRepository.findByEmail(signInForm.getEmail()).isEmpty()) {
            return Optional.of(ErrorEntity.USER_NOT_FOUND);
        }
        if (!userRepository.findByEmail(signInForm.getEmail()).get().getHashedPassword().equals(signInForm.getPassword())) {
            return Optional.of(ErrorEntity.INCORRECT_PASSWORD);
        }
        return Optional.empty();
    }
}
=======
package tat_tickets.services.impl;

import tat_tickets.dao.UserRepository;
import tat_tickets.dto.SignInForm;
import tat_tickets.dto.SignUpForm;
import tat_tickets.services.validation.ErrorEntity;
import tat_tickets.services.validation.Validator;

import java.util.Optional;

public class ValidatorImpl implements Validator {
    private final UserRepository userRepository;
    public ValidatorImpl(UserRepository userRepository) { this.userRepository = userRepository; }
    @Override
    public Optional<ErrorEntity> validateSignUp(SignUpForm signUpForm) {
        if (signUpForm.getEmail() == null) {
            return Optional.of(ErrorEntity.INVALID_EMAIL);
        }
        if (userRepository.findByEmail(signUpForm.getEmail()).isPresent()) {
            return Optional.of(ErrorEntity.EMAIL_ALREADY_EXISTS);
        }
        if (signUpForm.getPassword() == null) {
            return Optional.of(ErrorEntity.INCORRECT_PASSWORD);
        }
        if (signUpForm.getPassword().length() < 8) {
            return Optional.of(ErrorEntity.PASSWORD_TOO_SIMPLE);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ErrorEntity> validateSignIn(SignInForm signInForm) {
        if (signInForm.getEmail() == null) {
            return Optional.of(ErrorEntity.INVALID_EMAIL);
        }
        if (signInForm.getPassword() == null) {
            return Optional.of(ErrorEntity.INCORRECT_PASSWORD);
        }
        if (userRepository.findByEmail(signInForm.getEmail()).isEmpty()) {
            return Optional.of(ErrorEntity.USER_NOT_FOUND);
        }
        if (!userRepository.findByEmail(signInForm.getEmail()).get().getHashedPassword().equals(signInForm.getPassword())) {
            return Optional.of(ErrorEntity.INCORRECT_PASSWORD);
        }
        return Optional.empty();
    }
}
>>>>>>> d23f224 (feat: add done project)
