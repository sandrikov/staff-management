package ru.otus.coursework.staff.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.coursework.staff.domain.Role;
import ru.otus.coursework.staff.repository.RoleRepository;
import ru.otus.coursework.staff.service.RoleService;
import ru.otus.coursework.staff.service.dto.RoleDto;
import ru.otus.coursework.staff.service.mapper.RoleMapper;

/**
 * Service Implementation for managing {@link ru.otus.coursework.staff.domain.Role}.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        log.debug("Request to save Role : {}", roleDto);
        Role role = roleMapper.toEntity(roleDto);
        role = roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    @Override
    public RoleDto update(RoleDto roleDto) {
        log.debug("Request to update Role : {}", roleDto);
        Role role = roleMapper.toEntity(roleDto);
        role = roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    @Override
    public Optional<RoleDto> partialUpdate(RoleDto roleDto) {
        log.debug("Request to partially update Role : {}", roleDto);

        return roleRepository
            .findById(roleDto.getId())
            .map(existingRole -> {
                roleMapper.partialUpdate(existingRole, roleDto);

                return existingRole;
            })
            .map(roleRepository::save)
            .map(roleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleDto> findAll() {
        log.debug("Request to get all Roles");
        return roleRepository.findAll().stream().map(roleMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the roles where ProjectMember is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RoleDto> findAllWhereProjectMemberIsNull() {
        log.debug("Request to get all roles where ProjectMember is null");
        return StreamSupport
            .stream(roleRepository.findAll().spliterator(), false)
            .filter(role -> role.getProjectMember() == null)
            .map(roleMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RoleDto> findOne(Long id) {
        log.debug("Request to get Role : {}", id);
        return roleRepository.findById(id).map(roleMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Role : {}", id);
        roleRepository.deleteById(id);
    }
}
