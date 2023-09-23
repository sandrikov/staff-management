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
import ru.otus.coursework.staff.repository.RoleRepository;
import ru.otus.coursework.staff.service.RoleService;
import ru.otus.coursework.staff.service.dto.RoleDto;
import ru.otus.coursework.staff.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ru.otus.coursework.staff.domain.Role}.
 */
@RestController
@RequestMapping("/api")
public class RoleResource {

    private final Logger log = LoggerFactory.getLogger(RoleResource.class);

    private static final String ENTITY_NAME = "role";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RoleService roleService;

    private final RoleRepository roleRepository;

    public RoleResource(RoleService roleService, RoleRepository roleRepository) {
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }

    /**
     * {@code POST  /roles} : Create a new role.
     *
     * @param roleDto the roleDto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new roleDto, or with status {@code 400 (Bad Request)} if the role has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/roles")
    public ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleDto roleDto) throws URISyntaxException {
        log.debug("REST request to save Role : {}", roleDto);
        if (roleDto.getId() != null) {
            throw new BadRequestAlertException("A new role cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RoleDto result = roleService.save(roleDto);
        return ResponseEntity
            .created(new URI("/api/roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /roles/:id} : Updates an existing role.
     *
     * @param id the id of the roleDto to save.
     * @param roleDto the roleDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roleDto,
     * or with status {@code 400 (Bad Request)} if the roleDto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the roleDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/roles/{id}")
    public ResponseEntity<RoleDto> updateRole(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody RoleDto roleDto
    ) throws URISyntaxException {
        log.debug("REST request to update Role : {}, {}", id, roleDto);
        if (roleDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, roleDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!roleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RoleDto result = roleService.update(roleDto);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, roleDto.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /roles/:id} : Partial updates given fields of an existing role, field will ignore if it is null
     *
     * @param id the id of the roleDto to save.
     * @param roleDto the roleDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roleDto,
     * or with status {@code 400 (Bad Request)} if the roleDto is not valid,
     * or with status {@code 404 (Not Found)} if the roleDto is not found,
     * or with status {@code 500 (Internal Server Error)} if the roleDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/roles/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RoleDto> partialUpdateRole(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody RoleDto roleDto
    ) throws URISyntaxException {
        log.debug("REST request to partial update Role partially : {}, {}", id, roleDto);
        if (roleDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, roleDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!roleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RoleDto> result = roleService.partialUpdate(roleDto);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, roleDto.getId().toString())
        );
    }

    /**
     * {@code GET  /roles} : get all the roles.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of roles in body.
     */
    @GetMapping("/roles")
    public List<RoleDto> getAllRoles(@RequestParam(required = false) String filter) {
        if ("projectmember-is-null".equals(filter)) {
            log.debug("REST request to get all Roles where projectMember is null");
            return roleService.findAllWhereProjectMemberIsNull();
        }
        log.debug("REST request to get all Roles");
        return roleService.findAll();
    }

    /**
     * {@code GET  /roles/:id} : get the "id" role.
     *
     * @param id the id of the roleDto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the roleDto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/roles/{id}")
    public ResponseEntity<RoleDto> getRole(@PathVariable Long id) {
        log.debug("REST request to get Role : {}", id);
        Optional<RoleDto> roleDto = roleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(roleDto);
    }

    /**
     * {@code DELETE  /roles/:id} : delete the "id" role.
     *
     * @param id the id of the roleDto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        log.debug("REST request to delete Role : {}", id);
        roleService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
