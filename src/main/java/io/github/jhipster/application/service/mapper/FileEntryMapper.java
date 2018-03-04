package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.FileEntryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FileEntry and its DTO FileEntryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FileEntryMapper extends EntityMapper<FileEntryDTO, FileEntry> {


    @Mapping(target = "uploadVersions", ignore = true)
    @Mapping(target = "fileGroups", ignore = true)
    FileEntry toEntity(FileEntryDTO fileEntryDTO);

    default FileEntry fromId(Long id) {
        if (id == null) {
            return null;
        }
        FileEntry fileEntry = new FileEntry();
        fileEntry.setId(id);
        return fileEntry;
    }
}
