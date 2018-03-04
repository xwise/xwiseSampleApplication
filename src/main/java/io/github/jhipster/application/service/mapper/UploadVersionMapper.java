package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.UploadVersionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UploadVersion and its DTO UploadVersionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UploadVersionMapper extends EntityMapper<UploadVersionDTO, UploadVersion> {


    @Mapping(target = "fileEntries", ignore = true)
    UploadVersion toEntity(UploadVersionDTO uploadVersionDTO);

    default UploadVersion fromId(Long id) {
        if (id == null) {
            return null;
        }
        UploadVersion uploadVersion = new UploadVersion();
        uploadVersion.setId(id);
        return uploadVersion;
    }
}
