package com.tuyi.ppm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tuyi.ppm.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
Project findByProjectIdentifier(String projectIdentifier);
}
