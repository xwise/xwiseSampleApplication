package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.UploadVersionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UploadVersion and its DTO UploadVersionDTO.
 */
@Mapper(componentModel = "spring", uses = {FileEntryMapper.class})
public interface UploadVersionMapper extends EntityMapper<UploadVersionDTO, UploadVersion> {

    @Mapping(source = "fileEntry.id", target = "fileEntryId")
    @Mapping(source = "fileEntry.fileName", target = "fileEntryFileName")
    UploadVersionDTO toDto(UploadVersion uploadVersion);

    @Mapping(source = "fileEntryId", target = "fileEntry")
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
