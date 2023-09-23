package ru.otus.coursework.staff.service;

import java.util.List;
import java.util.Optional;
import ru.otus.coursework.staff.service.dto.ProjectDto;

/**
 * Service Interface for managing {@link ru.otus.coursework.staff.domain.Project}.
 */
public interface ProjectService {
    /**
     * Save a project.
     *
     * @param projectDto the entity to save.
     * @return the persisted entity.
     */
    ProjectDto save(ProjectDto projectDto);

    /**
     * Updates a project.
     *
     * @param projectDto the entity to update.
     * @return the persisted entity.
     */
    ProjectDto update(ProjectDto projectDto);

    /**
     * Partially updates a project.
     *
     * @param projectDto the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProjectDto> partialUpdate(ProjectDto projectDto);

    /**
     * Get all the projects.
     *
     * @return the list of entities.
     */
    List<ProjectDto> findAll();

    /**
     * Get all the ProjectDto where ProjectMember is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<ProjectDto> findAllWhereProjectMemberIsNull();

    /**
     * Get the "id" project.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProjectDto> findOne(Long id);

    /**
     * Delete the "id" project.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
