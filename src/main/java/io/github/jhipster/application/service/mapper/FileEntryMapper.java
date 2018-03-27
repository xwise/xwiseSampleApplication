package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.FileEntryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FileEntry and its DTO FileEntryDTO.
 */
@Mapper(componentModel = "spring", uses = {BaselineMapper.class})
public interface FileEntryMapper extends EntityMapper<FileEntryDTO, FileEntry> {

    @Mapping(source = "baseline.id", target = "baselineId")
    @Mapping(source = "baseline.baselineName", target = "baselineBaselineName")
    FileEntryDTO toDto(FileEntry fileEntry);

    @Mapping(target = "uploadVersions", ignore = true)
    @Mapping(source = "baselineId", target = "baseline")
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
