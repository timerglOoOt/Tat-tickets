package tat_tickets.services;

public interface PasswordEncoder {
    String hash (String password);
    boolean matches (String hash, String password);
}
