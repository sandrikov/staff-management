package ru.otus.coursework.staff.service.mapper;

import org.mapstruct.*;
import ru.otus.coursework.staff.domain.Project;
import ru.otus.coursework.staff.service.dto.ProjectDto;

/**
 * Mapper for the entity {@link Project} and its DTO {@link ProjectDto}.
 */
@Mapper(componentModel = "spring")
public interface ProjectMapper extends EntityMapper<ProjectDto, Project> {}
