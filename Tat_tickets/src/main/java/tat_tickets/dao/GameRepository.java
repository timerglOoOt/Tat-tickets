package tat_tickets.dao;

import tat_tickets.models.Game;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends CrudRepository<Game, Integer> {
    Optional<Game> findByOpposingTeamName(String opposingTeamName);
    Optional<Game> findById(Integer id);
    List<Game> findAll();
}
