<<<<<<< HEAD
package tat_tickets.dao;

import tat_tickets.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
=======
package tat_tickets.dao;

import tat_tickets.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByFirstName(String firstName);

}
>>>>>>> d23f224 (feat: add done project)
