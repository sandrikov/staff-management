package ru.otus.coursework.staff.service.mapper;

import org.junit.jupiter.api.BeforeEach;

class ProjectMemberMapperTest {

    private ProjectMemberMapper projectMemberMapper;

    @BeforeEach
    public void setUp() {
        projectMemberMapper = new ProjectMemberMapperImpl();
    }
}
