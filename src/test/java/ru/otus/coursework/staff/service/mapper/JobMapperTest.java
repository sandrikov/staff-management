package ru.otus.coursework.staff.service.mapper;

import org.junit.jupiter.api.BeforeEach;

class JobMapperTest {

    private JobMapper jobMapper;

    @BeforeEach
    public void setUp() {
        jobMapper = new JobMapperImpl();
    }
}
