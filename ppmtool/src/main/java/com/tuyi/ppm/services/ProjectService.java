package com.tuyi.ppm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuyi.ppm.domain.Backlog;
import com.tuyi.ppm.domain.Project;
import com.tuyi.ppm.exception.ProjectIdException;
import com.tuyi.ppm.repositories.BacklogRepository;
import com.tuyi.ppm.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private BacklogRepository backlogRepository;

	public Project saveOrUpdate(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

			if (project.getId() == null) {
				Backlog backlog = new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			}

			if (project.getId() != null) {
				project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier()));
			}
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException(
					"Project ID:  " + project.getProjectIdentifier().toUpperCase() + " Already exist");
		}

	}

	@SuppressWarnings("null")
	public Project findProjectByIdentifier(String projectIdentifier) {
		Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
		if (project == null) {
			throw new ProjectIdException("Project ID:  " + projectIdentifier.toUpperCase() + " does not exist");
		}
		return project;
	}

	public Iterable<Project> findAllPtojects() {
		return projectRepository.findAll();
	}

	public void deleteProjectByIdentifier(String projectIdentifier) {
		Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
		if (project == null) {
			throw new ProjectIdException(
					"Project with Project ID:  " + projectIdentifier.toUpperCase() + " does not exist");
		}
		projectRepository.delete(project);
	}

}
