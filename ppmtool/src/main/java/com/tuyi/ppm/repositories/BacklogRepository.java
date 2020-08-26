package com.tuyi.ppm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tuyi.ppm.domain.Backlog;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {

}
