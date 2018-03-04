package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.BaselineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Baseline and its DTO BaselineDTO.
 */
@Mapper(componentModel = "spring", uses = {BaselineEntryMapper.class})
public interface BaselineMapper extends EntityMapper<BaselineDTO, Baseline> {



    default Baseline fromId(Long id) {
        if (id == null) {
            return null;
        }
        Baseline baseline = new Baseline();
        baseline.setId(id);
        return baseline;
    }
}
