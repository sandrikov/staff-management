package ru.otus.coursework.staff.service.mapper;

import org.mapstruct.*;
import ru.otus.coursework.staff.domain.Department;
import ru.otus.coursework.staff.service.dto.DepartmentDto;

/**
 * Mapper for the entity {@link Department} and its DTO {@link DepartmentDto}.
 */
@Mapper(componentModel = "spring")
public interface DepartmentMapper extends EntityMapper<DepartmentDto, Department> {}
