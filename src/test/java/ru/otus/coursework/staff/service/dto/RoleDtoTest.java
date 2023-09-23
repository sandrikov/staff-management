package ru.otus.coursework.staff.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ru.otus.coursework.staff.web.rest.TestUtil;

class RoleDtoTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RoleDto.class);
        RoleDto roleDto1 = new RoleDto();
        roleDto1.setId(1L);
        RoleDto roleDto2 = new RoleDto();
        assertThat(roleDto1).isNotEqualTo(roleDto2);
        roleDto2.setId(roleDto1.getId());
        assertThat(roleDto1).isEqualTo(roleDto2);
        roleDto2.setId(2L);
        assertThat(roleDto1).isNotEqualTo(roleDto2);
        roleDto1.setId(null);
        assertThat(roleDto1).isNotEqualTo(roleDto2);
    }
}
