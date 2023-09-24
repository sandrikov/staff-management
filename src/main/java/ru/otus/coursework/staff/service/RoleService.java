package ru.otus.coursework.staff.service;

import java.util.List;
import java.util.Optional;
import ru.otus.coursework.staff.service.dto.RoleDto;

/**
 * Service Interface for managing {@link ru.otus.coursework.staff.domain.Role}.
 */
public interface RoleService {
    /**
     * Save a role.
     *
     * @param roleDto the entity to save.
     * @return the persisted entity.
     */
    RoleDto save(RoleDto roleDto);

    /**
     * Updates a role.
     *
     * @param roleDto the entity to update.
     * @return the persisted entity.
     */
    RoleDto update(RoleDto roleDto);

    /**
     * Partially updates a role.
     *
     * @param roleDto the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RoleDto> partialUpdate(RoleDto roleDto);

    /**
     * Get all the roles.
     *
     * @return the list of entities.
     */
    List<RoleDto> findAll();

    /**
     * Get all the RoleDto where ProjectMember is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<RoleDto> findAllWhereProjectMemberIsNull();

    /**
     * Get the "id" role.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RoleDto> findOne(Long id);

    /**
     * Delete the "id" role.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
