package ru.otus.coursework.staff.service.mapper;

import org.mapstruct.*;
import ru.otus.coursework.staff.domain.Department;
import ru.otus.coursework.staff.domain.Employee;
import ru.otus.coursework.staff.service.dto.DepartmentDto;
import ru.otus.coursework.staff.service.dto.EmployeeDto;

/**
 * Mapper for the entity {@link Employee} and its DTO {@link EmployeeDto}.
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper extends EntityMapper<EmployeeDto, Employee> {
    @Mapping(target = "manager", source = "manager", qualifiedByName = "employeeId")
    @Mapping(target = "department", source = "department", qualifiedByName = "departmentId")
    EmployeeDto toDto(Employee s);

    @Named("employeeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EmployeeDto toDtoEmployeeId(Employee employee);

    @Named("departmentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DepartmentDto toDtoDepartmentId(Department department);
}
