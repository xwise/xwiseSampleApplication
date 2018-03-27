package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.UploadVersionService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.UploadVersionDTO;
import io.github.jhipster.application.service.dto.UploadVersionCriteria;
import io.github.jhipster.application.service.UploadVersionQueryService;
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
 * REST controller for managing UploadVersion.
 */
@RestController
@RequestMapping("/api")
public class UploadVersionResource {

    private final Logger log = LoggerFactory.getLogger(UploadVersionResource.class);

    private static final String ENTITY_NAME = "uploadVersion";

    private final UploadVersionService uploadVersionService;

    private final UploadVersionQueryService uploadVersionQueryService;

    public UploadVersionResource(UploadVersionService uploadVersionService, UploadVersionQueryService uploadVersionQueryService) {
        this.uploadVersionService = uploadVersionService;
        this.uploadVersionQueryService = uploadVersionQueryService;
    }

    /**
     * POST  /upload-versions : Create a new uploadVersion.
     *
     * @param uploadVersionDTO the uploadVersionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new uploadVersionDTO, or with status 400 (Bad Request) if the uploadVersion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/upload-versions")
    @Timed
    public ResponseEntity<UploadVersionDTO> createUploadVersion(@RequestBody UploadVersionDTO uploadVersionDTO) throws URISyntaxException {
        log.debug("REST request to save UploadVersion : {}", uploadVersionDTO);
        if (uploadVersionDTO.getId() != null) {
            throw new BadRequestAlertException("A new uploadVersion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UploadVersionDTO result = uploadVersionService.save(uploadVersionDTO);
        return ResponseEntity.created(new URI("/api/upload-versions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /upload-versions : Updates an existing uploadVersion.
     *
     * @param uploadVersionDTO the uploadVersionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated uploadVersionDTO,
     * or with status 400 (Bad Request) if the uploadVersionDTO is not valid,
     * or with status 500 (Internal Server Error) if the uploadVersionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/upload-versions")
    @Timed
    public ResponseEntity<UploadVersionDTO> updateUploadVersion(@RequestBody UploadVersionDTO uploadVersionDTO) throws URISyntaxException {
        log.debug("REST request to update UploadVersion : {}", uploadVersionDTO);
        if (uploadVersionDTO.getId() == null) {
            return createUploadVersion(uploadVersionDTO);
        }
        UploadVersionDTO result = uploadVersionService.save(uploadVersionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, uploadVersionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /upload-versions : get all the uploadVersions.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of uploadVersions in body
     */
    @GetMapping("/upload-versions")
    @Timed
    public ResponseEntity<List<UploadVersionDTO>> getAllUploadVersions(UploadVersionCriteria criteria) {
        log.debug("REST request to get UploadVersions by criteria: {}", criteria);
        List<UploadVersionDTO> entityList = uploadVersionQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * GET  /upload-versions/:id : get the "id" uploadVersion.
     *
     * @param id the id of the uploadVersionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the uploadVersionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/upload-versions/{id}")
    @Timed
    public ResponseEntity<UploadVersionDTO> getUploadVersion(@PathVariable Long id) {
        log.debug("REST request to get UploadVersion : {}", id);
        UploadVersionDTO uploadVersionDTO = uploadVersionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(uploadVersionDTO));
    }

    /**
     * DELETE  /upload-versions/:id : delete the "id" uploadVersion.
     *
     * @param id the id of the uploadVersionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/upload-versions/{id}")
    @Timed
    public ResponseEntity<Void> deleteUploadVersion(@PathVariable Long id) {
        log.debug("REST request to delete UploadVersion : {}", id);
        uploadVersionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
