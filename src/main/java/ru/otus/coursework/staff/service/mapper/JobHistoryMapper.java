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
    @Mapping(target = "job", source = "job", qualifiedByName = "jobId")
    @Mapping(target = "department", source = "department", qualifiedByName = "departmentId")
    @Mapping(target = "employee", source = "employee", qualifiedByName = "employeeId")
    JobHistoryDto toDto(JobHistory s);

    @Named("jobId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    JobDto toDtoJobId(Job job);

    @Named("departmentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DepartmentDto toDtoDepartmentId(Department department);

    @Named("employeeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EmployeeDto toDtoEmployeeId(Employee employee);
}
