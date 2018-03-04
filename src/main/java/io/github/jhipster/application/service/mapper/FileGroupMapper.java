package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.FileGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FileGroup and its DTO FileGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FileGroupMapper extends EntityMapper<FileGroupDTO, FileGroup> {


    @Mapping(target = "baselines", ignore = true)
    @Mapping(target = "fileEntries", ignore = true)
    FileGroup toEntity(FileGroupDTO fileGroupDTO);

    default FileGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        FileGroup fileGroup = new FileGroup();
        fileGroup.setId(id);
        return fileGroup;
    }
}
