package ru.otus.coursework.staff.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ru.otus.coursework.staff.web.rest.TestUtil;

class DepartmentDtoTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepartmentDto.class);
        DepartmentDto departmentDto1 = new DepartmentDto();
        departmentDto1.setId(1L);
        DepartmentDto departmentDto2 = new DepartmentDto();
        assertThat(departmentDto1).isNotEqualTo(departmentDto2);
        departmentDto2.setId(departmentDto1.getId());
        assertThat(departmentDto1).isEqualTo(departmentDto2);
        departmentDto2.setId(2L);
        assertThat(departmentDto1).isNotEqualTo(departmentDto2);
        departmentDto1.setId(null);
        assertThat(departmentDto1).isNotEqualTo(departmentDto2);
    }
}
