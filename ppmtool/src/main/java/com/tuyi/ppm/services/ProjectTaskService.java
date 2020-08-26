package com.tuyi.ppm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuyi.ppm.domain.Backlog;
import com.tuyi.ppm.domain.ProjectTask;
import com.tuyi.ppm.repositories.BacklogRepository;
import com.tuyi.ppm.repositories.ProjectRepository;
import com.tuyi.ppm.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;
	@Autowired
	private ProjectTaskRepository projectTaskRepository;

	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

		// Exception: Project not found

		// pTs to be added to a specific project, project != null, BL exists
		Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
		// set the bl to pt
		projectTask.setBacklog(backlog);
		// we wont our project sequence to be like this: IDPRO-1 IDPRO-2 ....... 100 101
		Integer backlogSequence = backlog.getPTSequence();
		// Update the BL SEQUENCE
		backlogSequence++;
		backlog.setPTSequence(backlogSequence);
		// Add sequence to the project task
		projectTask.setProjectSequence(backlog.getProjectIdentifier() + "-" + backlogSequence);
		projectTask.setProjectIdentifier(projectIdentifier);
		// INITIAL priority when priority null
		if (projectTask.getPriority() == null) {
			projectTask.setPriority(3);
		}
		// INITIAL status when status is null
		if(projectTask.getStatus()=="" || projectTask.getStatus()==null) {
			projectTask.setStatus("TO_DO");
		}
		
		return projectTaskRepository.save(projectTask);
	}

	public Iterable<ProjectTask> findBacklogById(String backlog_id) {
		
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
	}

}
