package tat_tickets.utils.mappers.impl;

import tat_tickets.dto.SectionDto;
import tat_tickets.models.Section;
import tat_tickets.utils.mappers.SectionMapper;

public class SectionMapperImpl implements SectionMapper {
    @Override
    public SectionDto toDto(Section section) {
        return SectionDto.builder()
                .id(section.getId())
                .capacity(section.getCapacity())
                .build();
    }

    @Override
    public Section toSection(SectionDto dto) {
        return Section.builder()
                .id(dto.getId())
                .capacity(dto.getCapacity())
                .build();
    }
}
