package ru.otus.coursework.staff.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.coursework.staff.repository.ProjectMemberRepository;
import ru.otus.coursework.staff.service.ProjectMemberService;
import ru.otus.coursework.staff.service.dto.ProjectMemberDto;
import ru.otus.coursework.staff.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ru.otus.coursework.staff.domain.ProjectMember}.
 */
@RestController
@RequestMapping("/api")
public class ProjectMemberResource {

    private final Logger log = LoggerFactory.getLogger(ProjectMemberResource.class);

    private static final String ENTITY_NAME = "projectMember";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectMemberService projectMemberService;

    private final ProjectMemberRepository projectMemberRepository;

    public ProjectMemberResource(ProjectMemberService projectMemberService, ProjectMemberRepository projectMemberRepository) {
        this.projectMemberService = projectMemberService;
        this.projectMemberRepository = projectMemberRepository;
    }

    /**
     * {@code POST  /project-members} : Create a new projectMember.
     *
     * @param projectMemberDto the projectMemberDto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectMemberDto, or with status {@code 400 (Bad Request)} if the projectMember has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-members")
    public ResponseEntity<ProjectMemberDto> createProjectMember(@Valid @RequestBody ProjectMemberDto projectMemberDto)
        throws URISyntaxException {
        log.debug("REST request to save ProjectMember : {}", projectMemberDto);
        if (projectMemberDto.getId() != null) {
            throw new BadRequestAlertException("A new projectMember cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectMemberDto result = projectMemberService.save(projectMemberDto);
        return ResponseEntity
            .created(new URI("/api/project-members/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-members/:id} : Updates an existing projectMember.
     *
     * @param id the id of the projectMemberDto to save.
     * @param projectMemberDto the projectMemberDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectMemberDto,
     * or with status {@code 400 (Bad Request)} if the projectMemberDto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectMemberDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-members/{id}")
    public ResponseEntity<ProjectMemberDto> updateProjectMember(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ProjectMemberDto projectMemberDto
    ) throws URISyntaxException {
        log.debug("REST request to update ProjectMember : {}, {}", id, projectMemberDto);
        if (projectMemberDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectMemberDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectMemberRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProjectMemberDto result = projectMemberService.update(projectMemberDto);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, projectMemberDto.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /project-members/:id} : Partial updates given fields of an existing projectMember, field will ignore if it is null
     *
     * @param id the id of the projectMemberDto to save.
     * @param projectMemberDto the projectMemberDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectMemberDto,
     * or with status {@code 400 (Bad Request)} if the projectMemberDto is not valid,
     * or with status {@code 404 (Not Found)} if the projectMemberDto is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectMemberDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/project-members/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProjectMemberDto> partialUpdateProjectMember(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ProjectMemberDto projectMemberDto
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProjectMember partially : {}, {}", id, projectMemberDto);
        if (projectMemberDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectMemberDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectMemberRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProjectMemberDto> result = projectMemberService.partialUpdate(projectMemberDto);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, projectMemberDto.getId().toString())
        );
    }

    /**
     * {@code GET  /project-members} : get all the projectMembers.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectMembers in body.
     */
    @GetMapping("/project-members")
    public List<ProjectMemberDto> getAllProjectMembers(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all ProjectMembers");
        return projectMemberService.findAll();
    }

    /**
     * {@code GET  /project-members/:id} : get the "id" projectMember.
     *
     * @param id the id of the projectMemberDto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectMemberDto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-members/{id}")
    public ResponseEntity<ProjectMemberDto> getProjectMember(@PathVariable Long id) {
        log.debug("REST request to get ProjectMember : {}", id);
        Optional<ProjectMemberDto> projectMemberDto = projectMemberService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projectMemberDto);
    }

    /**
     * {@code DELETE  /project-members/:id} : delete the "id" projectMember.
     *
     * @param id the id of the projectMemberDto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-members/{id}")
    public ResponseEntity<Void> deleteProjectMember(@PathVariable Long id) {
        log.debug("REST request to delete ProjectMember : {}", id);
        projectMemberService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
