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
    @Mapping(target = "manager", source = "manager", qualifiedByName = "employeeLastName")
    @Mapping(target = "department", source = "department", qualifiedByName = "departmentDepartmentName")
    EmployeeDto toDto(Employee s);

    @Named("employeeLastName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "lastName", source = "lastName")
    EmployeeDto toDtoEmployeeLastName(Employee employee);

    @Named("departmentDepartmentName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "departmentName", source = "departmentName")
    DepartmentDto toDtoDepartmentDepartmentName(Department department);
}
