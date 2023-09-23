package ru.otus.coursework.staff.service.mapper;

import org.mapstruct.*;
import ru.otus.coursework.staff.domain.Role;
import ru.otus.coursework.staff.service.dto.RoleDto;

/**
 * Mapper for the entity {@link Role} and its DTO {@link RoleDto}.
 */
@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDto, Role> {}
