package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.BaselineEntryVersionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BaselineEntryVersion and its DTO BaselineEntryVersionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaselineEntryVersionMapper extends EntityMapper<BaselineEntryVersionDTO, BaselineEntryVersion> {


    @Mapping(target = "fileEntries", ignore = true)
    BaselineEntryVersion toEntity(BaselineEntryVersionDTO baselineEntryVersionDTO);

    default BaselineEntryVersion fromId(Long id) {
        if (id == null) {
            return null;
        }
        BaselineEntryVersion baselineEntryVersion = new BaselineEntryVersion();
        baselineEntryVersion.setId(id);
        return baselineEntryVersion;
    }
}
