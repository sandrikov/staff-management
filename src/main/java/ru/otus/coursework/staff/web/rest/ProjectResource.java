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
import ru.otus.coursework.staff.repository.ProjectRepository;
import ru.otus.coursework.staff.service.ProjectService;
import ru.otus.coursework.staff.service.dto.ProjectDto;
import ru.otus.coursework.staff.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ru.otus.coursework.staff.domain.Project}.
 */
@RestController
@RequestMapping("/api")
public class ProjectResource {

    private final Logger log = LoggerFactory.getLogger(ProjectResource.class);

    private static final String ENTITY_NAME = "project";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectService projectService;

    private final ProjectRepository projectRepository;

    public ProjectResource(ProjectService projectService, ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
    }

    /**
     * {@code POST  /projects} : Create a new project.
     *
     * @param projectDto the projectDto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectDto, or with status {@code 400 (Bad Request)} if the project has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/projects")
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto projectDto) throws URISyntaxException {
        log.debug("REST request to save Project : {}", projectDto);
        if (projectDto.getId() != null) {
            throw new BadRequestAlertException("A new project cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectDto result = projectService.save(projectDto);
        return ResponseEntity
            .created(new URI("/api/projects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /projects/:id} : Updates an existing project.
     *
     * @param id the id of the projectDto to save.
     * @param projectDto the projectDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectDto,
     * or with status {@code 400 (Bad Request)} if the projectDto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/projects/{id}")
    public ResponseEntity<ProjectDto> updateProject(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ProjectDto projectDto
    ) throws URISyntaxException {
        log.debug("REST request to update Project : {}, {}", id, projectDto);
        if (projectDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProjectDto result = projectService.update(projectDto);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, projectDto.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /projects/:id} : Partial updates given fields of an existing project, field will ignore if it is null
     *
     * @param id the id of the projectDto to save.
     * @param projectDto the projectDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectDto,
     * or with status {@code 400 (Bad Request)} if the projectDto is not valid,
     * or with status {@code 404 (Not Found)} if the projectDto is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/projects/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProjectDto> partialUpdateProject(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ProjectDto projectDto
    ) throws URISyntaxException {
        log.debug("REST request to partial update Project partially : {}, {}", id, projectDto);
        if (projectDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProjectDto> result = projectService.partialUpdate(projectDto);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, projectDto.getId().toString())
        );
    }

    /**
     * {@code GET  /projects} : get all the projects.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projects in body.
     */
    @GetMapping("/projects")
    public List<ProjectDto> getAllProjects(@RequestParam(required = false) String filter) {
        if ("projectmember-is-null".equals(filter)) {
            log.debug("REST request to get all Projects where projectMember is null");
            return projectService.findAllWhereProjectMemberIsNull();
        }
        log.debug("REST request to get all Projects");
        return projectService.findAll();
    }

    /**
     * {@code GET  /projects/:id} : get the "id" project.
     *
     * @param id the id of the projectDto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectDto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable Long id) {
        log.debug("REST request to get Project : {}", id);
        Optional<ProjectDto> projectDto = projectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projectDto);
    }

    /**
     * {@code DELETE  /projects/:id} : delete the "id" project.
     *
     * @param id the id of the projectDto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        log.debug("REST request to delete Project : {}", id);
        projectService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
