package ru.otus.coursework.staff.service.mapper;

import org.mapstruct.*;
import ru.otus.coursework.staff.domain.Employee;
import ru.otus.coursework.staff.domain.Job;
import ru.otus.coursework.staff.service.dto.EmployeeDto;
import ru.otus.coursework.staff.service.dto.JobDto;

/**
 * Mapper for the entity {@link Job} and its DTO {@link JobDto}.
 */
@Mapper(componentModel = "spring")
public interface JobMapper extends EntityMapper<JobDto, Job> {
    @Mapping(target = "employee", source = "employee", qualifiedByName = "employeeId")
    JobDto toDto(Job s);

    @Named("employeeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EmployeeDto toDtoEmployeeId(Employee employee);
}
