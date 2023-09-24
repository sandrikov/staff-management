package ru.otus.coursework.staff.service.mapper;

import org.mapstruct.*;
import ru.otus.coursework.staff.domain.Department;
import ru.otus.coursework.staff.domain.Employee;
import ru.otus.coursework.staff.domain.Job;
import ru.otus.coursework.staff.domain.JobHistory;
import ru.otus.coursework.staff.service.dto.DepartmentDto;
import ru.otus.coursework.staff.service.dto.EmployeeDto;
import ru.otus.coursework.staff.service.dto.JobDto;
import ru.otus.coursework.staff.service.dto.JobHistoryDto;

/**
 * Mapper for the entity {@link JobHistory} and its DTO {@link JobHistoryDto}.
 */
@Mapper(componentModel = "spring")
public interface JobHistoryMapper extends EntityMapper<JobHistoryDto, JobHistory> {
    @Mapping(target = "job", source = "job", qualifiedByName = "jobJobTitle")
    @Mapping(target = "department", source = "department", qualifiedByName = "departmentDepartmentName")
    @Mapping(target = "employee", source = "employee", qualifiedByName = "employeeLastName")
    JobHistoryDto toDto(JobHistory s);

    @Named("jobJobTitle")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "jobTitle", source = "jobTitle")
    JobDto toDtoJobJobTitle(Job job);

    @Named("departmentDepartmentName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "departmentName", source = "departmentName")
    DepartmentDto toDtoDepartmentDepartmentName(Department department);

    @Named("employeeLastName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "lastName", source = "lastName")
    EmployeeDto toDtoEmployeeLastName(Employee employee);
}
