<<<<<<< HEAD
package tat_tickets.utils.mappers;

import tat_tickets.dto.SignUpForm;
import tat_tickets.dto.UserDto;
import tat_tickets.models.User;

public interface UserMapper {
    UserDto toDto(User user);
    User toUser(UserDto dto);
    User toUser(SignUpForm dto);
}

=======
package tat_tickets.utils.mappers;

import tat_tickets.dto.SignUpForm;
import tat_tickets.dto.UserDto;
import tat_tickets.models.User;

public interface UserMapper {
    UserDto toDto(User user);
    User toUser(UserDto dto);
    User toUser(SignUpForm dto);
}

>>>>>>> d23f224 (feat: add done project)
