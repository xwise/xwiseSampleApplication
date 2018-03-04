package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.FileGroupService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.FileGroupDTO;
import io.github.jhipster.application.service.dto.FileGroupCriteria;
import io.github.jhipster.application.service.FileGroupQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing FileGroup.
 */
@RestController
@RequestMapping("/api")
public class FileGroupResource {

    private final Logger log = LoggerFactory.getLogger(FileGroupResource.class);

    private static final String ENTITY_NAME = "fileGroup";

    private final FileGroupService fileGroupService;

    private final FileGroupQueryService fileGroupQueryService;

    public FileGroupResource(FileGroupService fileGroupService, FileGroupQueryService fileGroupQueryService) {
        this.fileGroupService = fileGroupService;
        this.fileGroupQueryService = fileGroupQueryService;
    }

    /**
     * POST  /file-groups : Create a new fileGroup.
     *
     * @param fileGroupDTO the fileGroupDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fileGroupDTO, or with status 400 (Bad Request) if the fileGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/file-groups")
    @Timed
    public ResponseEntity<FileGroupDTO> createFileGroup(@RequestBody FileGroupDTO fileGroupDTO) throws URISyntaxException {
        log.debug("REST request to save FileGroup : {}", fileGroupDTO);
        if (fileGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new fileGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileGroupDTO result = fileGroupService.save(fileGroupDTO);
        return ResponseEntity.created(new URI("/api/file-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /file-groups : Updates an existing fileGroup.
     *
     * @param fileGroupDTO the fileGroupDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fileGroupDTO,
     * or with status 400 (Bad Request) if the fileGroupDTO is not valid,
     * or with status 500 (Internal Server Error) if the fileGroupDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/file-groups")
    @Timed
    public ResponseEntity<FileGroupDTO> updateFileGroup(@RequestBody FileGroupDTO fileGroupDTO) throws URISyntaxException {
        log.debug("REST request to update FileGroup : {}", fileGroupDTO);
        if (fileGroupDTO.getId() == null) {
            return createFileGroup(fileGroupDTO);
        }
        FileGroupDTO result = fileGroupService.save(fileGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fileGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /file-groups : get all the fileGroups.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of fileGroups in body
     */
    @GetMapping("/file-groups")
    @Timed
    public ResponseEntity<List<FileGroupDTO>> getAllFileGroups(FileGroupCriteria criteria) {
        log.debug("REST request to get FileGroups by criteria: {}", criteria);
        List<FileGroupDTO> entityList = fileGroupQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * GET  /file-groups/:id : get the "id" fileGroup.
     *
     * @param id the id of the fileGroupDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fileGroupDTO, or with status 404 (Not Found)
     */
    @GetMapping("/file-groups/{id}")
    @Timed
    public ResponseEntity<FileGroupDTO> getFileGroup(@PathVariable Long id) {
        log.debug("REST request to get FileGroup : {}", id);
        FileGroupDTO fileGroupDTO = fileGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fileGroupDTO));
    }

    /**
     * DELETE  /file-groups/:id : delete the "id" fileGroup.
     *
     * @param id the id of the fileGroupDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/file-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteFileGroup(@PathVariable Long id) {
        log.debug("REST request to delete FileGroup : {}", id);
        fileGroupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
