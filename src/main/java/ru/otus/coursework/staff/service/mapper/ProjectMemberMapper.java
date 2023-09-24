package ru.otus.coursework.staff.service.mapper;

import org.mapstruct.*;
import ru.otus.coursework.staff.domain.Employee;
import ru.otus.coursework.staff.domain.Project;
import ru.otus.coursework.staff.domain.ProjectMember;
import ru.otus.coursework.staff.domain.Role;
import ru.otus.coursework.staff.service.dto.EmployeeDto;
import ru.otus.coursework.staff.service.dto.ProjectDto;
import ru.otus.coursework.staff.service.dto.ProjectMemberDto;
import ru.otus.coursework.staff.service.dto.RoleDto;

/**
 * Mapper for the entity {@link ProjectMember} and its DTO {@link ProjectMemberDto}.
 */
@Mapper(componentModel = "spring")
public interface ProjectMemberMapper extends EntityMapper<ProjectMemberDto, ProjectMember> {
    @Mapping(target = "project", source = "project", qualifiedByName = "projectProjectName")
    @Mapping(target = "role", source = "role", qualifiedByName = "roleRoleName")
    @Mapping(target = "employee", source = "employee", qualifiedByName = "employeeLastName")
    ProjectMemberDto toDto(ProjectMember s);

    @Named("projectProjectName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "projectName", source = "projectName")
    ProjectDto toDtoProjectProjectName(Project project);

    @Named("roleRoleName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "roleName", source = "roleName")
    RoleDto toDtoRoleRoleName(Role role);

    @Named("employeeLastName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "lastName", source = "lastName")
    EmployeeDto toDtoEmployeeLastName(Employee employee);
}
