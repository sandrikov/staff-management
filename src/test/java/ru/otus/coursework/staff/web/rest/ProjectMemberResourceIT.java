package ru.otus.coursework.staff.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.coursework.staff.IntegrationTest;
import ru.otus.coursework.staff.domain.Project;
import ru.otus.coursework.staff.domain.ProjectMember;
import ru.otus.coursework.staff.repository.ProjectMemberRepository;
import ru.otus.coursework.staff.service.ProjectMemberService;
import ru.otus.coursework.staff.service.dto.ProjectMemberDto;
import ru.otus.coursework.staff.service.mapper.ProjectMemberMapper;

/**
 * Integration tests for the {@link ProjectMemberResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class ProjectMemberResourceIT {

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/project-members";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Mock
    private ProjectMemberRepository projectMemberRepositoryMock;

    @Autowired
    private ProjectMemberMapper projectMemberMapper;

    @Mock
    private ProjectMemberService projectMemberServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectMemberMockMvc;

    private ProjectMember projectMember;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectMember createEntity(EntityManager em) {
        ProjectMember projectMember = new ProjectMember().startDate(DEFAULT_START_DATE).endDate(DEFAULT_END_DATE);
        // Add required entity
        Project project;
        if (TestUtil.findAll(em, Project.class).isEmpty()) {
            project = ProjectResourceIT.createEntity(em);
            em.persist(project);
            em.flush();
        } else {
            project = TestUtil.findAll(em, Project.class).get(0);
        }
        projectMember.setProject(project);
        return projectMember;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectMember createUpdatedEntity(EntityManager em) {
        ProjectMember projectMember = new ProjectMember().startDate(UPDATED_START_DATE).endDate(UPDATED_END_DATE);
        // Add required entity
        Project project;
        if (TestUtil.findAll(em, Project.class).isEmpty()) {
            project = ProjectResourceIT.createUpdatedEntity(em);
            em.persist(project);
            em.flush();
        } else {
            project = TestUtil.findAll(em, Project.class).get(0);
        }
        projectMember.setProject(project);
        return projectMember;
    }

    @BeforeEach
    public void initTest() {
        projectMember = createEntity(em);
    }

    @Test
    @Transactional
    void createProjectMember() throws Exception {
        int databaseSizeBeforeCreate = projectMemberRepository.findAll().size();
        // Create the ProjectMember
        ProjectMemberDto projectMemberDto = projectMemberMapper.toDto(projectMember);
        restProjectMemberMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(projectMemberDto))
            )
            .andExpect(status().isCreated());

        // Validate the ProjectMember in the database
        List<ProjectMember> projectMemberList = projectMemberRepository.findAll();
        assertThat(projectMemberList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectMember testProjectMember = projectMemberList.get(projectMemberList.size() - 1);
        assertThat(testProjectMember.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testProjectMember.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

    @Test
    @Transactional
    void createProjectMemberWithExistingId() throws Exception {
        // Create the ProjectMember with an existing ID
        projectMember.setId(1L);
        ProjectMemberDto projectMemberDto = projectMemberMapper.toDto(projectMember);

        int databaseSizeBeforeCreate = projectMemberRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectMemberMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(projectMemberDto))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectMember in the database
        List<ProjectMember> projectMemberList = projectMemberRepository.findAll();
        assertThat(projectMemberList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectMemberRepository.findAll().size();
        // set the field null
        projectMember.setStartDate(null);

        // Create the ProjectMember, which fails.
        ProjectMemberDto projectMemberDto = projectMemberMapper.toDto(projectMember);

        restProjectMemberMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(projectMemberDto))
            )
            .andExpect(status().isBadRequest());

        List<ProjectMember> projectMemberList = projectMemberRepository.findAll();
        assertThat(projectMemberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllProjectMembers() throws Exception {
        // Initialize the database
        projectMemberRepository.saveAndFlush(projectMember);

        // Get all the projectMemberList
        restProjectMemberMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectMember.getId().intValue())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllProjectMembersWithEagerRelationshipsIsEnabled() throws Exception {
        when(projectMemberServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restProjectMemberMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(projectMemberServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllProjectMembersWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(projectMemberServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restProjectMemberMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(projectMemberRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getProjectMember() throws Exception {
        // Initialize the database
        projectMemberRepository.saveAndFlush(projectMember);

        // Get the projectMember
        restProjectMemberMockMvc
            .perform(get(ENTITY_API_URL_ID, projectMember.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectMember.getId().intValue()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingProjectMember() throws Exception {
        // Get the projectMember
        restProjectMemberMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProjectMember() throws Exception {
        // Initialize the database
        projectMemberRepository.saveAndFlush(projectMember);

        int databaseSizeBeforeUpdate = projectMemberRepository.findAll().size();

        // Update the projectMember
        ProjectMember updatedProjectMember = projectMemberRepository.findById(projectMember.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProjectMember are not directly saved in db
        em.detach(updatedProjectMember);
        updatedProjectMember.startDate(UPDATED_START_DATE).endDate(UPDATED_END_DATE);
        ProjectMemberDto projectMemberDto = projectMemberMapper.toDto(updatedProjectMember);

        restProjectMemberMockMvc
            .perform(
                put(ENTITY_API_URL_ID, projectMemberDto.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(projectMemberDto))
            )
            .andExpect(status().isOk());

        // Validate the ProjectMember in the database
        List<ProjectMember> projectMemberList = projectMemberRepository.findAll();
        assertThat(projectMemberList).hasSize(databaseSizeBeforeUpdate);
        ProjectMember testProjectMember = projectMemberList.get(projectMemberList.size() - 1);
        assertThat(testProjectMember.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testProjectMember.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    void putNonExistingProjectMember() throws Exception {
        int databaseSizeBeforeUpdate = projectMemberRepository.findAll().size();
        projectMember.setId(count.incrementAndGet());

        // Create the ProjectMember
        ProjectMemberDto projectMemberDto = projectMemberMapper.toDto(projectMember);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectMemberMockMvc
            .perform(
                put(ENTITY_API_URL_ID, projectMemberDto.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(projectMemberDto))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectMember in the database
        List<ProjectMember> projectMemberList = projectMemberRepository.findAll();
        assertThat(projectMemberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProjectMember() throws Exception {
        int databaseSizeBeforeUpdate = projectMemberRepository.findAll().size();
        projectMember.setId(count.incrementAndGet());

        // Create the ProjectMember
        ProjectMemberDto projectMemberDto = projectMemberMapper.toDto(projectMember);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectMemberMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(projectMemberDto))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectMember in the database
        List<ProjectMember> projectMemberList = projectMemberRepository.findAll();
        assertThat(projectMemberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProjectMember() throws Exception {
        int databaseSizeBeforeUpdate = projectMemberRepository.findAll().size();
        projectMember.setId(count.incrementAndGet());

        // Create the ProjectMember
        ProjectMemberDto projectMemberDto = projectMemberMapper.toDto(projectMember);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectMemberMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(projectMemberDto))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProjectMember in the database
        List<ProjectMember> projectMemberList = projectMemberRepository.findAll();
        assertThat(projectMemberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProjectMemberWithPatch() throws Exception {
        // Initialize the database
        projectMemberRepository.saveAndFlush(projectMember);

        int databaseSizeBeforeUpdate = projectMemberRepository.findAll().size();

        // Update the projectMember using partial update
        ProjectMember partialUpdatedProjectMember = new ProjectMember();
        partialUpdatedProjectMember.setId(projectMember.getId());

        restProjectMemberMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectMember.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProjectMember))
            )
            .andExpect(status().isOk());

        // Validate the ProjectMember in the database
        List<ProjectMember> projectMemberList = projectMemberRepository.findAll();
        assertThat(projectMemberList).hasSize(databaseSizeBeforeUpdate);
        ProjectMember testProjectMember = projectMemberList.get(projectMemberList.size() - 1);
        assertThat(testProjectMember.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testProjectMember.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

    @Test
    @Transactional
    void fullUpdateProjectMemberWithPatch() throws Exception {
        // Initialize the database
        projectMemberRepository.saveAndFlush(projectMember);

        int databaseSizeBeforeUpdate = projectMemberRepository.findAll().size();

        // Update the projectMember using partial update
        ProjectMember partialUpdatedProjectMember = new ProjectMember();
        partialUpdatedProjectMember.setId(projectMember.getId());

        partialUpdatedProjectMember.startDate(UPDATED_START_DATE).endDate(UPDATED_END_DATE);

        restProjectMemberMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectMember.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProjectMember))
            )
            .andExpect(status().isOk());

        // Validate the ProjectMember in the database
        List<ProjectMember> projectMemberList = projectMemberRepository.findAll();
        assertThat(projectMemberList).hasSize(databaseSizeBeforeUpdate);
        ProjectMember testProjectMember = projectMemberList.get(projectMemberList.size() - 1);
        assertThat(testProjectMember.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testProjectMember.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingProjectMember() throws Exception {
        int databaseSizeBeforeUpdate = projectMemberRepository.findAll().size();
        projectMember.setId(count.incrementAndGet());

        // Create the ProjectMember
        ProjectMemberDto projectMemberDto = projectMemberMapper.toDto(projectMember);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectMemberMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, projectMemberDto.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(projectMemberDto))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectMember in the database
        List<ProjectMember> projectMemberList = projectMemberRepository.findAll();
        assertThat(projectMemberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProjectMember() throws Exception {
        int databaseSizeBeforeUpdate = projectMemberRepository.findAll().size();
        projectMember.setId(count.incrementAndGet());

        // Create the ProjectMember
        ProjectMemberDto projectMemberDto = projectMemberMapper.toDto(projectMember);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectMemberMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(projectMemberDto))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectMember in the database
        List<ProjectMember> projectMemberList = projectMemberRepository.findAll();
        assertThat(projectMemberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProjectMember() throws Exception {
        int databaseSizeBeforeUpdate = projectMemberRepository.findAll().size();
        projectMember.setId(count.incrementAndGet());

        // Create the ProjectMember
        ProjectMemberDto projectMemberDto = projectMemberMapper.toDto(projectMember);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectMemberMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(projectMemberDto))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProjectMember in the database
        List<ProjectMember> projectMemberList = projectMemberRepository.findAll();
        assertThat(projectMemberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProjectMember() throws Exception {
        // Initialize the database
        projectMemberRepository.saveAndFlush(projectMember);

        int databaseSizeBeforeDelete = projectMemberRepository.findAll().size();

        // Delete the projectMember
        restProjectMemberMockMvc
            .perform(delete(ENTITY_API_URL_ID, projectMember.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProjectMember> projectMemberList = projectMemberRepository.findAll();
        assertThat(projectMemberList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
