package tat_tickets.utils.mappers;

import tat_tickets.dto.SectionDto;
import tat_tickets.models.Section;

public interface SectionMapper {
    SectionDto toDto(Section section);
    Section toSection(SectionDto dto);
}
