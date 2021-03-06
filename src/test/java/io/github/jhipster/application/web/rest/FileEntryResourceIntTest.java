package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.XwiseSampleApplicationApp;

import io.github.jhipster.application.domain.FileEntry;
import io.github.jhipster.application.domain.Baseline;
import io.github.jhipster.application.repository.FileEntryRepository;
import io.github.jhipster.application.service.FileEntryService;
import io.github.jhipster.application.service.dto.FileEntryDTO;
import io.github.jhipster.application.service.mapper.FileEntryMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.FileEntryCriteria;
import io.github.jhipster.application.service.FileEntryQueryService;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.domain.enumeration.UploadStatus;
/**
 * Test class for the FileEntryResource REST controller.
 *
 * @see FileEntryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XwiseSampleApplicationApp.class)
public class FileEntryResourceIntTest {

    private static final Instant DEFAULT_CREATION_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_FILE_NAME_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FILE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FILE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_FILE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FILE_CONTENT_TYPE = "image/png";

    private static final UploadStatus DEFAULT_UPLOAD_STATUS = UploadStatus.NO;
    private static final UploadStatus UPDATED_UPLOAD_STATUS = UploadStatus.OK;

    @Autowired
    private FileEntryRepository fileEntryRepository;

    @Autowired
    private FileEntryMapper fileEntryMapper;

    @Autowired
    private FileEntryService fileEntryService;

    @Autowired
    private FileEntryQueryService fileEntryQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFileEntryMockMvc;

    private FileEntry fileEntry;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FileEntryResource fileEntryResource = new FileEntryResource(fileEntryService, fileEntryQueryService);
        this.restFileEntryMockMvc = MockMvcBuilders.standaloneSetup(fileEntryResource)
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
    public static FileEntry createEntity(EntityManager em) {
        FileEntry fileEntry = new FileEntry()
            .creationTime(DEFAULT_CREATION_TIME)
            .fileNameVersion(DEFAULT_FILE_NAME_VERSION)
            .fileName(DEFAULT_FILE_NAME)
            .version(DEFAULT_VERSION)
            .file(DEFAULT_FILE)
            .fileContentType(DEFAULT_FILE_CONTENT_TYPE)
            .uploadStatus(DEFAULT_UPLOAD_STATUS);
        return fileEntry;
    }

    @Before
    public void initTest() {
        fileEntry = createEntity(em);
    }

    @Test
    @Transactional
    public void createFileEntry() throws Exception {
        int databaseSizeBeforeCreate = fileEntryRepository.findAll().size();

        // Create the FileEntry
        FileEntryDTO fileEntryDTO = fileEntryMapper.toDto(fileEntry);
        restFileEntryMockMvc.perform(post("/api/file-entries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileEntryDTO)))
            .andExpect(status().isCreated());

        // Validate the FileEntry in the database
        List<FileEntry> fileEntryList = fileEntryRepository.findAll();
        assertThat(fileEntryList).hasSize(databaseSizeBeforeCreate + 1);
        FileEntry testFileEntry = fileEntryList.get(fileEntryList.size() - 1);
        assertThat(testFileEntry.getCreationTime()).isEqualTo(DEFAULT_CREATION_TIME);
        assertThat(testFileEntry.getFileNameVersion()).isEqualTo(DEFAULT_FILE_NAME_VERSION);
        assertThat(testFileEntry.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testFileEntry.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testFileEntry.getFile()).isEqualTo(DEFAULT_FILE);
        assertThat(testFileEntry.getFileContentType()).isEqualTo(DEFAULT_FILE_CONTENT_TYPE);
        assertThat(testFileEntry.getUploadStatus()).isEqualTo(DEFAULT_UPLOAD_STATUS);
    }

    @Test
    @Transactional
    public void createFileEntryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fileEntryRepository.findAll().size();

        // Create the FileEntry with an existing ID
        fileEntry.setId(1L);
        FileEntryDTO fileEntryDTO = fileEntryMapper.toDto(fileEntry);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFileEntryMockMvc.perform(post("/api/file-entries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileEntryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FileEntry in the database
        List<FileEntry> fileEntryList = fileEntryRepository.findAll();
        assertThat(fileEntryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFileEntries() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get all the fileEntryList
        restFileEntryMockMvc.perform(get("/api/file-entries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fileEntry.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationTime").value(hasItem(DEFAULT_CREATION_TIME.toString())))
            .andExpect(jsonPath("$.[*].fileNameVersion").value(hasItem(DEFAULT_FILE_NAME_VERSION.toString())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION.toString())))
            .andExpect(jsonPath("$.[*].fileContentType").value(hasItem(DEFAULT_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].file").value(hasItem(Base64Utils.encodeToString(DEFAULT_FILE))))
            .andExpect(jsonPath("$.[*].uploadStatus").value(hasItem(DEFAULT_UPLOAD_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getFileEntry() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get the fileEntry
        restFileEntryMockMvc.perform(get("/api/file-entries/{id}", fileEntry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fileEntry.getId().intValue()))
            .andExpect(jsonPath("$.creationTime").value(DEFAULT_CREATION_TIME.toString()))
            .andExpect(jsonPath("$.fileNameVersion").value(DEFAULT_FILE_NAME_VERSION.toString()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME.toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION.toString()))
            .andExpect(jsonPath("$.fileContentType").value(DEFAULT_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.file").value(Base64Utils.encodeToString(DEFAULT_FILE)))
            .andExpect(jsonPath("$.uploadStatus").value(DEFAULT_UPLOAD_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getAllFileEntriesByCreationTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get all the fileEntryList where creationTime equals to DEFAULT_CREATION_TIME
        defaultFileEntryShouldBeFound("creationTime.equals=" + DEFAULT_CREATION_TIME);

        // Get all the fileEntryList where creationTime equals to UPDATED_CREATION_TIME
        defaultFileEntryShouldNotBeFound("creationTime.equals=" + UPDATED_CREATION_TIME);
    }

    @Test
    @Transactional
    public void getAllFileEntriesByCreationTimeIsInShouldWork() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get all the fileEntryList where creationTime in DEFAULT_CREATION_TIME or UPDATED_CREATION_TIME
        defaultFileEntryShouldBeFound("creationTime.in=" + DEFAULT_CREATION_TIME + "," + UPDATED_CREATION_TIME);

        // Get all the fileEntryList where creationTime equals to UPDATED_CREATION_TIME
        defaultFileEntryShouldNotBeFound("creationTime.in=" + UPDATED_CREATION_TIME);
    }

    @Test
    @Transactional
    public void getAllFileEntriesByCreationTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get all the fileEntryList where creationTime is not null
        defaultFileEntryShouldBeFound("creationTime.specified=true");

        // Get all the fileEntryList where creationTime is null
        defaultFileEntryShouldNotBeFound("creationTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllFileEntriesByFileNameVersionIsEqualToSomething() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get all the fileEntryList where fileNameVersion equals to DEFAULT_FILE_NAME_VERSION
        defaultFileEntryShouldBeFound("fileNameVersion.equals=" + DEFAULT_FILE_NAME_VERSION);

        // Get all the fileEntryList where fileNameVersion equals to UPDATED_FILE_NAME_VERSION
        defaultFileEntryShouldNotBeFound("fileNameVersion.equals=" + UPDATED_FILE_NAME_VERSION);
    }

    @Test
    @Transactional
    public void getAllFileEntriesByFileNameVersionIsInShouldWork() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get all the fileEntryList where fileNameVersion in DEFAULT_FILE_NAME_VERSION or UPDATED_FILE_NAME_VERSION
        defaultFileEntryShouldBeFound("fileNameVersion.in=" + DEFAULT_FILE_NAME_VERSION + "," + UPDATED_FILE_NAME_VERSION);

        // Get all the fileEntryList where fileNameVersion equals to UPDATED_FILE_NAME_VERSION
        defaultFileEntryShouldNotBeFound("fileNameVersion.in=" + UPDATED_FILE_NAME_VERSION);
    }

    @Test
    @Transactional
    public void getAllFileEntriesByFileNameVersionIsNullOrNotNull() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get all the fileEntryList where fileNameVersion is not null
        defaultFileEntryShouldBeFound("fileNameVersion.specified=true");

        // Get all the fileEntryList where fileNameVersion is null
        defaultFileEntryShouldNotBeFound("fileNameVersion.specified=false");
    }

    @Test
    @Transactional
    public void getAllFileEntriesByFileNameIsEqualToSomething() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get all the fileEntryList where fileName equals to DEFAULT_FILE_NAME
        defaultFileEntryShouldBeFound("fileName.equals=" + DEFAULT_FILE_NAME);

        // Get all the fileEntryList where fileName equals to UPDATED_FILE_NAME
        defaultFileEntryShouldNotBeFound("fileName.equals=" + UPDATED_FILE_NAME);
    }

    @Test
    @Transactional
    public void getAllFileEntriesByFileNameIsInShouldWork() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get all the fileEntryList where fileName in DEFAULT_FILE_NAME or UPDATED_FILE_NAME
        defaultFileEntryShouldBeFound("fileName.in=" + DEFAULT_FILE_NAME + "," + UPDATED_FILE_NAME);

        // Get all the fileEntryList where fileName equals to UPDATED_FILE_NAME
        defaultFileEntryShouldNotBeFound("fileName.in=" + UPDATED_FILE_NAME);
    }

    @Test
    @Transactional
    public void getAllFileEntriesByFileNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get all the fileEntryList where fileName is not null
        defaultFileEntryShouldBeFound("fileName.specified=true");

        // Get all the fileEntryList where fileName is null
        defaultFileEntryShouldNotBeFound("fileName.specified=false");
    }

    @Test
    @Transactional
    public void getAllFileEntriesByVersionIsEqualToSomething() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get all the fileEntryList where version equals to DEFAULT_VERSION
        defaultFileEntryShouldBeFound("version.equals=" + DEFAULT_VERSION);

        // Get all the fileEntryList where version equals to UPDATED_VERSION
        defaultFileEntryShouldNotBeFound("version.equals=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void getAllFileEntriesByVersionIsInShouldWork() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get all the fileEntryList where version in DEFAULT_VERSION or UPDATED_VERSION
        defaultFileEntryShouldBeFound("version.in=" + DEFAULT_VERSION + "," + UPDATED_VERSION);

        // Get all the fileEntryList where version equals to UPDATED_VERSION
        defaultFileEntryShouldNotBeFound("version.in=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void getAllFileEntriesByVersionIsNullOrNotNull() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get all the fileEntryList where version is not null
        defaultFileEntryShouldBeFound("version.specified=true");

        // Get all the fileEntryList where version is null
        defaultFileEntryShouldNotBeFound("version.specified=false");
    }

    @Test
    @Transactional
    public void getAllFileEntriesByUploadStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get all the fileEntryList where uploadStatus equals to DEFAULT_UPLOAD_STATUS
        defaultFileEntryShouldBeFound("uploadStatus.equals=" + DEFAULT_UPLOAD_STATUS);

        // Get all the fileEntryList where uploadStatus equals to UPDATED_UPLOAD_STATUS
        defaultFileEntryShouldNotBeFound("uploadStatus.equals=" + UPDATED_UPLOAD_STATUS);
    }

    @Test
    @Transactional
    public void getAllFileEntriesByUploadStatusIsInShouldWork() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get all the fileEntryList where uploadStatus in DEFAULT_UPLOAD_STATUS or UPDATED_UPLOAD_STATUS
        defaultFileEntryShouldBeFound("uploadStatus.in=" + DEFAULT_UPLOAD_STATUS + "," + UPDATED_UPLOAD_STATUS);

        // Get all the fileEntryList where uploadStatus equals to UPDATED_UPLOAD_STATUS
        defaultFileEntryShouldNotBeFound("uploadStatus.in=" + UPDATED_UPLOAD_STATUS);
    }

    @Test
    @Transactional
    public void getAllFileEntriesByUploadStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get all the fileEntryList where uploadStatus is not null
        defaultFileEntryShouldBeFound("uploadStatus.specified=true");

        // Get all the fileEntryList where uploadStatus is null
        defaultFileEntryShouldNotBeFound("uploadStatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllFileEntriesByBaselineIsEqualToSomething() throws Exception {
        // Initialize the database
        Baseline baseline = BaselineResourceIntTest.createEntity(em);
        em.persist(baseline);
        em.flush();
        fileEntry.addBaseline(baseline);
        fileEntryRepository.saveAndFlush(fileEntry);
        Long baselineId = baseline.getId();

        // Get all the fileEntryList where baseline equals to baselineId
        defaultFileEntryShouldBeFound("baselineId.equals=" + baselineId);

        // Get all the fileEntryList where baseline equals to baselineId + 1
        defaultFileEntryShouldNotBeFound("baselineId.equals=" + (baselineId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultFileEntryShouldBeFound(String filter) throws Exception {
        restFileEntryMockMvc.perform(get("/api/file-entries?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fileEntry.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationTime").value(hasItem(DEFAULT_CREATION_TIME.toString())))
            .andExpect(jsonPath("$.[*].fileNameVersion").value(hasItem(DEFAULT_FILE_NAME_VERSION.toString())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION.toString())))
            .andExpect(jsonPath("$.[*].fileContentType").value(hasItem(DEFAULT_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].file").value(hasItem(Base64Utils.encodeToString(DEFAULT_FILE))))
            .andExpect(jsonPath("$.[*].uploadStatus").value(hasItem(DEFAULT_UPLOAD_STATUS.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultFileEntryShouldNotBeFound(String filter) throws Exception {
        restFileEntryMockMvc.perform(get("/api/file-entries?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingFileEntry() throws Exception {
        // Get the fileEntry
        restFileEntryMockMvc.perform(get("/api/file-entries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFileEntry() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);
        int databaseSizeBeforeUpdate = fileEntryRepository.findAll().size();

        // Update the fileEntry
        FileEntry updatedFileEntry = fileEntryRepository.findOne(fileEntry.getId());
        // Disconnect from session so that the updates on updatedFileEntry are not directly saved in db
        em.detach(updatedFileEntry);
        updatedFileEntry
            .creationTime(UPDATED_CREATION_TIME)
            .fileNameVersion(UPDATED_FILE_NAME_VERSION)
            .fileName(UPDATED_FILE_NAME)
            .version(UPDATED_VERSION)
            .file(UPDATED_FILE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE)
            .uploadStatus(UPDATED_UPLOAD_STATUS);
        FileEntryDTO fileEntryDTO = fileEntryMapper.toDto(updatedFileEntry);

        restFileEntryMockMvc.perform(put("/api/file-entries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileEntryDTO)))
            .andExpect(status().isOk());

        // Validate the FileEntry in the database
        List<FileEntry> fileEntryList = fileEntryRepository.findAll();
        assertThat(fileEntryList).hasSize(databaseSizeBeforeUpdate);
        FileEntry testFileEntry = fileEntryList.get(fileEntryList.size() - 1);
        assertThat(testFileEntry.getCreationTime()).isEqualTo(UPDATED_CREATION_TIME);
        assertThat(testFileEntry.getFileNameVersion()).isEqualTo(UPDATED_FILE_NAME_VERSION);
        assertThat(testFileEntry.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testFileEntry.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testFileEntry.getFile()).isEqualTo(UPDATED_FILE);
        assertThat(testFileEntry.getFileContentType()).isEqualTo(UPDATED_FILE_CONTENT_TYPE);
        assertThat(testFileEntry.getUploadStatus()).isEqualTo(UPDATED_UPLOAD_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingFileEntry() throws Exception {
        int databaseSizeBeforeUpdate = fileEntryRepository.findAll().size();

        // Create the FileEntry
        FileEntryDTO fileEntryDTO = fileEntryMapper.toDto(fileEntry);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFileEntryMockMvc.perform(put("/api/file-entries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileEntryDTO)))
            .andExpect(status().isCreated());

        // Validate the FileEntry in the database
        List<FileEntry> fileEntryList = fileEntryRepository.findAll();
        assertThat(fileEntryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFileEntry() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);
        int databaseSizeBeforeDelete = fileEntryRepository.findAll().size();

        // Get the fileEntry
        restFileEntryMockMvc.perform(delete("/api/file-entries/{id}", fileEntry.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FileEntry> fileEntryList = fileEntryRepository.findAll();
        assertThat(fileEntryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FileEntry.class);
        FileEntry fileEntry1 = new FileEntry();
        fileEntry1.setId(1L);
        FileEntry fileEntry2 = new FileEntry();
        fileEntry2.setId(fileEntry1.getId());
        assertThat(fileEntry1).isEqualTo(fileEntry2);
        fileEntry2.setId(2L);
        assertThat(fileEntry1).isNotEqualTo(fileEntry2);
        fileEntry1.setId(null);
        assertThat(fileEntry1).isNotEqualTo(fileEntry2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FileEntryDTO.class);
        FileEntryDTO fileEntryDTO1 = new FileEntryDTO();
        fileEntryDTO1.setId(1L);
        FileEntryDTO fileEntryDTO2 = new FileEntryDTO();
        assertThat(fileEntryDTO1).isNotEqualTo(fileEntryDTO2);
        fileEntryDTO2.setId(fileEntryDTO1.getId());
        assertThat(fileEntryDTO1).isEqualTo(fileEntryDTO2);
        fileEntryDTO2.setId(2L);
        assertThat(fileEntryDTO1).isNotEqualTo(fileEntryDTO2);
        fileEntryDTO1.setId(null);
        assertThat(fileEntryDTO1).isNotEqualTo(fileEntryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fileEntryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fileEntryMapper.fromId(null)).isNull();
    }
}
