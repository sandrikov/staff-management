package ru.otus.coursework.staff.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.coursework.staff.domain.Project;
import ru.otus.coursework.staff.repository.ProjectRepository;
import ru.otus.coursework.staff.service.ProjectService;
import ru.otus.coursework.staff.service.dto.ProjectDto;
import ru.otus.coursework.staff.service.mapper.ProjectMapper;

/**
 * Service Implementation for managing {@link ru.otus.coursework.staff.domain.Project}.
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public ProjectDto save(ProjectDto projectDto) {
        log.debug("Request to save Project : {}", projectDto);
        Project project = projectMapper.toEntity(projectDto);
        project = projectRepository.save(project);
        return projectMapper.toDto(project);
    }

    @Override
    public ProjectDto update(ProjectDto projectDto) {
        log.debug("Request to update Project : {}", projectDto);
        Project project = projectMapper.toEntity(projectDto);
        project = projectRepository.save(project);
        return projectMapper.toDto(project);
    }

    @Override
    public Optional<ProjectDto> partialUpdate(ProjectDto projectDto) {
        log.debug("Request to partially update Project : {}", projectDto);

        return projectRepository
            .findById(projectDto.getId())
            .map(existingProject -> {
                projectMapper.partialUpdate(existingProject, projectDto);

                return existingProject;
            })
            .map(projectRepository::save)
            .map(projectMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDto> findAll() {
        log.debug("Request to get all Projects");
        return projectRepository.findAll().stream().map(projectMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the projects where ProjectMember is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProjectDto> findAllWhereProjectMemberIsNull() {
        log.debug("Request to get all projects where ProjectMember is null");
        return StreamSupport
            .stream(projectRepository.findAll().spliterator(), false)
            .filter(project -> project.getProjectMember() == null)
            .map(projectMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectDto> findOne(Long id) {
        log.debug("Request to get Project : {}", id);
        return projectRepository.findById(id).map(projectMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Project : {}", id);
        projectRepository.deleteById(id);
    }
}
