package tat_tickets.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
