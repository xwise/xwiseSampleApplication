package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.FileEntryService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.FileEntryDTO;
import io.github.jhipster.application.service.dto.FileEntryCriteria;
import io.github.jhipster.application.service.FileEntryQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing FileEntry.
 */
@RestController
@RequestMapping("/api")
public class FileEntryResource {

    private final Logger log = LoggerFactory.getLogger(FileEntryResource.class);

    private static final String ENTITY_NAME = "fileEntry";

    private final FileEntryService fileEntryService;

    private final FileEntryQueryService fileEntryQueryService;

    public FileEntryResource(FileEntryService fileEntryService, FileEntryQueryService fileEntryQueryService) {
        this.fileEntryService = fileEntryService;
        this.fileEntryQueryService = fileEntryQueryService;
    }

    /**
     * POST  /file-entries : Create a new fileEntry.
     *
     * @param fileEntryDTO the fileEntryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fileEntryDTO, or with status 400 (Bad Request) if the fileEntry has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/file-entries")
    @Timed
    public ResponseEntity<FileEntryDTO> createFileEntry(@RequestBody FileEntryDTO fileEntryDTO) throws URISyntaxException {
        log.debug("REST request to save FileEntry : {}", fileEntryDTO);
        if (fileEntryDTO.getId() != null) {
            throw new BadRequestAlertException("A new fileEntry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileEntryDTO result = fileEntryService.save(fileEntryDTO);
        return ResponseEntity.created(new URI("/api/file-entries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /file-entries : Updates an existing fileEntry.
     *
     * @param fileEntryDTO the fileEntryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fileEntryDTO,
     * or with status 400 (Bad Request) if the fileEntryDTO is not valid,
     * or with status 500 (Internal Server Error) if the fileEntryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/file-entries")
    @Timed
    public ResponseEntity<FileEntryDTO> updateFileEntry(@RequestBody FileEntryDTO fileEntryDTO) throws URISyntaxException {
        log.debug("REST request to update FileEntry : {}", fileEntryDTO);
        if (fileEntryDTO.getId() == null) {
            return createFileEntry(fileEntryDTO);
        }
        FileEntryDTO result = fileEntryService.save(fileEntryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fileEntryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /file-entries : get all the fileEntries.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of fileEntries in body
     */
    @GetMapping("/file-entries")
    @Timed
    public ResponseEntity<List<FileEntryDTO>> getAllFileEntries(FileEntryCriteria criteria, Pageable pageable) {
        log.debug("REST request to get FileEntries by criteria: {}", criteria);
        Page<FileEntryDTO> page = fileEntryQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/file-entries");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /file-entries/:id : get the "id" fileEntry.
     *
     * @param id the id of the fileEntryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fileEntryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/file-entries/{id}")
    @Timed
    public ResponseEntity<FileEntryDTO> getFileEntry(@PathVariable Long id) {
        log.debug("REST request to get FileEntry : {}", id);
        FileEntryDTO fileEntryDTO = fileEntryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fileEntryDTO));
    }

    /**
     * DELETE  /file-entries/:id : delete the "id" fileEntry.
     *
     * @param id the id of the fileEntryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/file-entries/{id}")
    @Timed
    public ResponseEntity<Void> deleteFileEntry(@PathVariable Long id) {
        log.debug("REST request to delete FileEntry : {}", id);
        fileEntryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
