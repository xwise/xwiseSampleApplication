package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.XwiseSampleApplicationApp;

import io.github.jhipster.application.domain.UploadVersion;
import io.github.jhipster.application.domain.FileEntry;
import io.github.jhipster.application.repository.UploadVersionRepository;
import io.github.jhipster.application.service.UploadVersionService;
import io.github.jhipster.application.service.dto.UploadVersionDTO;
import io.github.jhipster.application.service.mapper.UploadVersionMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.UploadVersionCriteria;
import io.github.jhipster.application.service.UploadVersionQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UploadVersionResource REST controller.
 *
 * @see UploadVersionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XwiseSampleApplicationApp.class)
public class UploadVersionResourceIntTest {

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FILE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FILE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_FILE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FILE_CONTENT_TYPE = "image/png";

    @Autowired
    private UploadVersionRepository uploadVersionRepository;

    @Autowired
    private UploadVersionMapper uploadVersionMapper;

    @Autowired
    private UploadVersionService uploadVersionService;

    @Autowired
    private UploadVersionQueryService uploadVersionQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUploadVersionMockMvc;

    private UploadVersion uploadVersion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UploadVersionResource uploadVersionResource = new UploadVersionResource(uploadVersionService, uploadVersionQueryService);
        this.restUploadVersionMockMvc = MockMvcBuilders.standaloneSetup(uploadVersionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UploadVersion createEntity(EntityManager em) {
        UploadVersion uploadVersion = new UploadVersion()
            .version(DEFAULT_VERSION)
            .file(DEFAULT_FILE)
            .fileContentType(DEFAULT_FILE_CONTENT_TYPE);
        return uploadVersion;
    }

    @Before
    public void initTest() {
        uploadVersion = createEntity(em);
    }

    @Test
    @Transactional
    public void createUploadVersion() throws Exception {
        int databaseSizeBeforeCreate = uploadVersionRepository.findAll().size();

        // Create the UploadVersion
        UploadVersionDTO uploadVersionDTO = uploadVersionMapper.toDto(uploadVersion);
        restUploadVersionMockMvc.perform(post("/api/upload-versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uploadVersionDTO)))
            .andExpect(status().isCreated());

        // Validate the UploadVersion in the database
        List<UploadVersion> uploadVersionList = uploadVersionRepository.findAll();
        assertThat(uploadVersionList).hasSize(databaseSizeBeforeCreate + 1);
        UploadVersion testUploadVersion = uploadVersionList.get(uploadVersionList.size() - 1);
        assertThat(testUploadVersion.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testUploadVersion.getFile()).isEqualTo(DEFAULT_FILE);
        assertThat(testUploadVersion.getFileContentType()).isEqualTo(DEFAULT_FILE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createUploadVersionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = uploadVersionRepository.findAll().size();

        // Create the UploadVersion with an existing ID
        uploadVersion.setId(1L);
        UploadVersionDTO uploadVersionDTO = uploadVersionMapper.toDto(uploadVersion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUploadVersionMockMvc.perform(post("/api/upload-versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uploadVersionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UploadVersion in the database
        List<UploadVersion> uploadVersionList = uploadVersionRepository.findAll();
        assertThat(uploadVersionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUploadVersions() throws Exception {
        // Initialize the database
        uploadVersionRepository.saveAndFlush(uploadVersion);

        // Get all the uploadVersionList
        restUploadVersionMockMvc.perform(get("/api/upload-versions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uploadVersion.getId().intValue())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION.toString())))
            .andExpect(jsonPath("$.[*].fileContentType").value(hasItem(DEFAULT_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].file").value(hasItem(Base64Utils.encodeToString(DEFAULT_FILE))));
    }

    @Test
    @Transactional
    public void getUploadVersion() throws Exception {
        // Initialize the database
        uploadVersionRepository.saveAndFlush(uploadVersion);

        // Get the uploadVersion
        restUploadVersionMockMvc.perform(get("/api/upload-versions/{id}", uploadVersion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(uploadVersion.getId().intValue()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION.toString()))
            .andExpect(jsonPath("$.fileContentType").value(DEFAULT_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.file").value(Base64Utils.encodeToString(DEFAULT_FILE)));
    }

    @Test
    @Transactional
    public void getAllUploadVersionsByVersionIsEqualToSomething() throws Exception {
        // Initialize the database
        uploadVersionRepository.saveAndFlush(uploadVersion);

        // Get all the uploadVersionList where version equals to DEFAULT_VERSION
        defaultUploadVersionShouldBeFound("version.equals=" + DEFAULT_VERSION);

        // Get all the uploadVersionList where version equals to UPDATED_VERSION
        defaultUploadVersionShouldNotBeFound("version.equals=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void getAllUploadVersionsByVersionIsInShouldWork() throws Exception {
        // Initialize the database
        uploadVersionRepository.saveAndFlush(uploadVersion);

        // Get all the uploadVersionList where version in DEFAULT_VERSION or UPDATED_VERSION
        defaultUploadVersionShouldBeFound("version.in=" + DEFAULT_VERSION + "," + UPDATED_VERSION);

        // Get all the uploadVersionList where version equals to UPDATED_VERSION
        defaultUploadVersionShouldNotBeFound("version.in=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void getAllUploadVersionsByVersionIsNullOrNotNull() throws Exception {
        // Initialize the database
        uploadVersionRepository.saveAndFlush(uploadVersion);

        // Get all the uploadVersionList where version is not null
        defaultUploadVersionShouldBeFound("version.specified=true");

        // Get all the uploadVersionList where version is null
        defaultUploadVersionShouldNotBeFound("version.specified=false");
    }

    @Test
    @Transactional
    public void getAllUploadVersionsByFileEntryIsEqualToSomething() throws Exception {
        // Initialize the database
        FileEntry fileEntry = FileEntryResourceIntTest.createEntity(em);
        em.persist(fileEntry);
        em.flush();
        uploadVersion.setFileEntry(fileEntry);
        uploadVersionRepository.saveAndFlush(uploadVersion);
        Long fileEntryId = fileEntry.getId();

        // Get all the uploadVersionList where fileEntry equals to fileEntryId
        defaultUploadVersionShouldBeFound("fileEntryId.equals=" + fileEntryId);

        // Get all the uploadVersionList where fileEntry equals to fileEntryId + 1
        defaultUploadVersionShouldNotBeFound("fileEntryId.equals=" + (fileEntryId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultUploadVersionShouldBeFound(String filter) throws Exception {
        restUploadVersionMockMvc.perform(get("/api/upload-versions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uploadVersion.getId().intValue())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION.toString())))
            .andExpect(jsonPath("$.[*].fileContentType").value(hasItem(DEFAULT_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].file").value(hasItem(Base64Utils.encodeToString(DEFAULT_FILE))));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultUploadVersionShouldNotBeFound(String filter) throws Exception {
        restUploadVersionMockMvc.perform(get("/api/upload-versions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingUploadVersion() throws Exception {
        // Get the uploadVersion
        restUploadVersionMockMvc.perform(get("/api/upload-versions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUploadVersion() throws Exception {
        // Initialize the database
        uploadVersionRepository.saveAndFlush(uploadVersion);
        int databaseSizeBeforeUpdate = uploadVersionRepository.findAll().size();

        // Update the uploadVersion
        UploadVersion updatedUploadVersion = uploadVersionRepository.findOne(uploadVersion.getId());
        // Disconnect from session so that the updates on updatedUploadVersion are not directly saved in db
        em.detach(updatedUploadVersion);
        updatedUploadVersion
            .version(UPDATED_VERSION)
            .file(UPDATED_FILE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE);
        UploadVersionDTO uploadVersionDTO = uploadVersionMapper.toDto(updatedUploadVersion);

        restUploadVersionMockMvc.perform(put("/api/upload-versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uploadVersionDTO)))
            .andExpect(status().isOk());

        // Validate the UploadVersion in the database
        List<UploadVersion> uploadVersionList = uploadVersionRepository.findAll();
        assertThat(uploadVersionList).hasSize(databaseSizeBeforeUpdate);
        UploadVersion testUploadVersion = uploadVersionList.get(uploadVersionList.size() - 1);
        assertThat(testUploadVersion.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testUploadVersion.getFile()).isEqualTo(UPDATED_FILE);
        assertThat(testUploadVersion.getFileContentType()).isEqualTo(UPDATED_FILE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingUploadVersion() throws Exception {
        int databaseSizeBeforeUpdate = uploadVersionRepository.findAll().size();

        // Create the UploadVersion
        UploadVersionDTO uploadVersionDTO = uploadVersionMapper.toDto(uploadVersion);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUploadVersionMockMvc.perform(put("/api/upload-versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uploadVersionDTO)))
            .andExpect(status().isCreated());

        // Validate the UploadVersion in the database
        List<UploadVersion> uploadVersionList = uploadVersionRepository.findAll();
        assertThat(uploadVersionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUploadVersion() throws Exception {
        // Initialize the database
        uploadVersionRepository.saveAndFlush(uploadVersion);
        int databaseSizeBeforeDelete = uploadVersionRepository.findAll().size();

        // Get the uploadVersion
        restUploadVersionMockMvc.perform(delete("/api/upload-versions/{id}", uploadVersion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UploadVersion> uploadVersionList = uploadVersionRepository.findAll();
        assertThat(uploadVersionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UploadVersion.class);
        UploadVersion uploadVersion1 = new UploadVersion();
        uploadVersion1.setId(1L);
        UploadVersion uploadVersion2 = new UploadVersion();
        uploadVersion2.setId(uploadVersion1.getId());
        assertThat(uploadVersion1).isEqualTo(uploadVersion2);
        uploadVersion2.setId(2L);
        assertThat(uploadVersion1).isNotEqualTo(uploadVersion2);
        uploadVersion1.setId(null);
        assertThat(uploadVersion1).isNotEqualTo(uploadVersion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UploadVersionDTO.class);
        UploadVersionDTO uploadVersionDTO1 = new UploadVersionDTO();
        uploadVersionDTO1.setId(1L);
        UploadVersionDTO uploadVersionDTO2 = new UploadVersionDTO();
        assertThat(uploadVersionDTO1).isNotEqualTo(uploadVersionDTO2);
        uploadVersionDTO2.setId(uploadVersionDTO1.getId());
        assertThat(uploadVersionDTO1).isEqualTo(uploadVersionDTO2);
        uploadVersionDTO2.setId(2L);
        assertThat(uploadVersionDTO1).isNotEqualTo(uploadVersionDTO2);
        uploadVersionDTO1.setId(null);
        assertThat(uploadVersionDTO1).isNotEqualTo(uploadVersionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(uploadVersionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(uploadVersionMapper.fromId(null)).isNull();
    }
}
