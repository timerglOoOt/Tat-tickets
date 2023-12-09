package tat_tickets.dao;

import tat_tickets.models.Section;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends CrudRepository<Section, Integer> {
    Optional<Section> findById(Integer id);
    List<Section> findAll();
    Section save(Section item);
    void update(Section item);
    void delete(Integer id);
}
