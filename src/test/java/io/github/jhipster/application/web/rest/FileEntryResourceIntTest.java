package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.XwiseSampleApplicationApp;

import io.github.jhipster.application.domain.FileEntry;
import io.github.jhipster.application.domain.BaselineEntryVersion;
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

import javax.persistence.EntityManager;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FileEntryResource REST controller.
 *
 * @see FileEntryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XwiseSampleApplicationApp.class)
public class FileEntryResourceIntTest {

    private static final String DEFAULT_ENTRY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ENTRY_NAME = "BBBBBBBBBB";

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
            .entryName(DEFAULT_ENTRY_NAME);
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
        assertThat(testFileEntry.getEntryName()).isEqualTo(DEFAULT_ENTRY_NAME);
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
            .andExpect(jsonPath("$.[*].entryName").value(hasItem(DEFAULT_ENTRY_NAME.toString())));
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
            .andExpect(jsonPath("$.entryName").value(DEFAULT_ENTRY_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllFileEntriesByEntryNameIsEqualToSomething() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get all the fileEntryList where entryName equals to DEFAULT_ENTRY_NAME
        defaultFileEntryShouldBeFound("entryName.equals=" + DEFAULT_ENTRY_NAME);

        // Get all the fileEntryList where entryName equals to UPDATED_ENTRY_NAME
        defaultFileEntryShouldNotBeFound("entryName.equals=" + UPDATED_ENTRY_NAME);
    }

    @Test
    @Transactional
    public void getAllFileEntriesByEntryNameIsInShouldWork() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get all the fileEntryList where entryName in DEFAULT_ENTRY_NAME or UPDATED_ENTRY_NAME
        defaultFileEntryShouldBeFound("entryName.in=" + DEFAULT_ENTRY_NAME + "," + UPDATED_ENTRY_NAME);

        // Get all the fileEntryList where entryName equals to UPDATED_ENTRY_NAME
        defaultFileEntryShouldNotBeFound("entryName.in=" + UPDATED_ENTRY_NAME);
    }

    @Test
    @Transactional
    public void getAllFileEntriesByEntryNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        fileEntryRepository.saveAndFlush(fileEntry);

        // Get all the fileEntryList where entryName is not null
        defaultFileEntryShouldBeFound("entryName.specified=true");

        // Get all the fileEntryList where entryName is null
        defaultFileEntryShouldNotBeFound("entryName.specified=false");
    }

    @Test
    @Transactional
    public void getAllFileEntriesByBaselineEntryVersionIsEqualToSomething() throws Exception {
        // Initialize the database
        BaselineEntryVersion baselineEntryVersion = BaselineEntryVersionResourceIntTest.createEntity(em);
        em.persist(baselineEntryVersion);
        em.flush();
        fileEntry.addBaselineEntryVersion(baselineEntryVersion);
        fileEntryRepository.saveAndFlush(fileEntry);
        Long baselineEntryVersionId = baselineEntryVersion.getId();

        // Get all the fileEntryList where baselineEntryVersion equals to baselineEntryVersionId
        defaultFileEntryShouldBeFound("baselineEntryVersionId.equals=" + baselineEntryVersionId);

        // Get all the fileEntryList where baselineEntryVersion equals to baselineEntryVersionId + 1
        defaultFileEntryShouldNotBeFound("baselineEntryVersionId.equals=" + (baselineEntryVersionId + 1));
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
            .andExpect(jsonPath("$.[*].entryName").value(hasItem(DEFAULT_ENTRY_NAME.toString())));
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
            .entryName(UPDATED_ENTRY_NAME);
        FileEntryDTO fileEntryDTO = fileEntryMapper.toDto(updatedFileEntry);

        restFileEntryMockMvc.perform(put("/api/file-entries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileEntryDTO)))
            .andExpect(status().isOk());

        // Validate the FileEntry in the database
        List<FileEntry> fileEntryList = fileEntryRepository.findAll();
        assertThat(fileEntryList).hasSize(databaseSizeBeforeUpdate);
        FileEntry testFileEntry = fileEntryList.get(fileEntryList.size() - 1);
        assertThat(testFileEntry.getEntryName()).isEqualTo(UPDATED_ENTRY_NAME);
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
