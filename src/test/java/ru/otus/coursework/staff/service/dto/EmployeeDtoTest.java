package ru.otus.coursework.staff.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ru.otus.coursework.staff.web.rest.TestUtil;

class EmployeeDtoTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeDto.class);
        EmployeeDto employeeDto1 = new EmployeeDto();
        employeeDto1.setId(1L);
        EmployeeDto employeeDto2 = new EmployeeDto();
        assertThat(employeeDto1).isNotEqualTo(employeeDto2);
        employeeDto2.setId(employeeDto1.getId());
        assertThat(employeeDto1).isEqualTo(employeeDto2);
        employeeDto2.setId(2L);
        assertThat(employeeDto1).isNotEqualTo(employeeDto2);
        employeeDto1.setId(null);
        assertThat(employeeDto1).isNotEqualTo(employeeDto2);
    }
}
