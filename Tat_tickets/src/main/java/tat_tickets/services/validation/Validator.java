<<<<<<< HEAD
package tat_tickets.services.validation;

import tat_tickets.dto.SignInForm;
import tat_tickets.dto.SignUpForm;

import java.util.Optional;

public interface Validator {
    Optional<ErrorEntity> validateSignUp(SignUpForm signUpForm);
    Optional<ErrorEntity> validateSignIn(SignInForm signInForm);
}
=======
package tat_tickets.services.validation;

import tat_tickets.dto.SignInForm;
import tat_tickets.dto.SignUpForm;

import java.util.Optional;

public interface Validator {
    Optional<ErrorEntity> validateSignUp(SignUpForm signUpForm);
    Optional<ErrorEntity> validateSignIn(SignInForm signInForm);
}
>>>>>>> d23f224 (feat: add done project)
