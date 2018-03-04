package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.BaselineEntryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BaselineEntry and its DTO BaselineEntryDTO.
 */
@Mapper(componentModel = "spring", uses = {BaselineEntryVersionMapper.class})
public interface BaselineEntryMapper extends EntityMapper<BaselineEntryDTO, BaselineEntry> {


    @Mapping(target = "baselineEntries", ignore = true)
    BaselineEntry toEntity(BaselineEntryDTO baselineEntryDTO);

    default BaselineEntry fromId(Long id) {
        if (id == null) {
            return null;
        }
        BaselineEntry baselineEntry = new BaselineEntry();
        baselineEntry.setId(id);
        return baselineEntry;
    }
}
