package ru.otus.coursework.staff.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.otus.coursework.staff.service.dto.ProjectMemberDto;

/**
 * Service Interface for managing {@link ru.otus.coursework.staff.domain.ProjectMember}.
 */
public interface ProjectMemberService {
    /**
     * Save a projectMember.
     *
     * @param projectMemberDto the entity to save.
     * @return the persisted entity.
     */
    ProjectMemberDto save(ProjectMemberDto projectMemberDto);

    /**
     * Updates a projectMember.
     *
     * @param projectMemberDto the entity to update.
     * @return the persisted entity.
     */
    ProjectMemberDto update(ProjectMemberDto projectMemberDto);

    /**
     * Partially updates a projectMember.
     *
     * @param projectMemberDto the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProjectMemberDto> partialUpdate(ProjectMemberDto projectMemberDto);

    /**
     * Get all the projectMembers.
     *
     * @return the list of entities.
     */
    List<ProjectMemberDto> findAll();

    /**
     * Get all the projectMembers with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProjectMemberDto> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" projectMember.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProjectMemberDto> findOne(Long id);

    /**
     * Delete the "id" projectMember.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
