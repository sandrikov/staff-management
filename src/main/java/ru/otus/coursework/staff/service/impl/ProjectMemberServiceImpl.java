package ru.otus.coursework.staff.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.coursework.staff.domain.ProjectMember;
import ru.otus.coursework.staff.repository.ProjectMemberRepository;
import ru.otus.coursework.staff.service.ProjectMemberService;
import ru.otus.coursework.staff.service.dto.ProjectMemberDto;
import ru.otus.coursework.staff.service.mapper.ProjectMemberMapper;

/**
 * Service Implementation for managing {@link ru.otus.coursework.staff.domain.ProjectMember}.
 */
@Service
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    private final Logger log = LoggerFactory.getLogger(ProjectMemberServiceImpl.class);

    private final ProjectMemberRepository projectMemberRepository;

    private final ProjectMemberMapper projectMemberMapper;

    public ProjectMemberServiceImpl(ProjectMemberRepository projectMemberRepository, ProjectMemberMapper projectMemberMapper) {
        this.projectMemberRepository = projectMemberRepository;
        this.projectMemberMapper = projectMemberMapper;
    }

    @Override
    public ProjectMemberDto save(ProjectMemberDto projectMemberDto) {
        log.debug("Request to save ProjectMember : {}", projectMemberDto);
        ProjectMember projectMember = projectMemberMapper.toEntity(projectMemberDto);
        projectMember = projectMemberRepository.save(projectMember);
        return projectMemberMapper.toDto(projectMember);
    }

    @Override
    public ProjectMemberDto update(ProjectMemberDto projectMemberDto) {
        log.debug("Request to update ProjectMember : {}", projectMemberDto);
        ProjectMember projectMember = projectMemberMapper.toEntity(projectMemberDto);
        projectMember = projectMemberRepository.save(projectMember);
        return projectMemberMapper.toDto(projectMember);
    }

    @Override
    public Optional<ProjectMemberDto> partialUpdate(ProjectMemberDto projectMemberDto) {
        log.debug("Request to partially update ProjectMember : {}", projectMemberDto);

        return projectMemberRepository
            .findById(projectMemberDto.getId())
            .map(existingProjectMember -> {
                projectMemberMapper.partialUpdate(existingProjectMember, projectMemberDto);

                return existingProjectMember;
            })
            .map(projectMemberRepository::save)
            .map(projectMemberMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectMemberDto> findAll() {
        log.debug("Request to get all ProjectMembers");
        return projectMemberRepository.findAll().stream().map(projectMemberMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    public Page<ProjectMemberDto> findAllWithEagerRelationships(Pageable pageable) {
        return projectMemberRepository.findAllWithEagerRelationships(pageable).map(projectMemberMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectMemberDto> findOne(Long id) {
        log.debug("Request to get ProjectMember : {}", id);
        return projectMemberRepository.findOneWithEagerRelationships(id).map(projectMemberMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProjectMember : {}", id);
        projectMemberRepository.deleteById(id);
    }
}
