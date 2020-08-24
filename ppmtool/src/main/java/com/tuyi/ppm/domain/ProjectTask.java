package com.tuyi.ppm.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

@Entity
public class ProjectTask {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(updatable = false)
	private String projectSequence;
	@NotBlank(message="Please include a project summary")
	private String summary;
	private String acceptanceCriteria;
	private String status;
	private Integer priority;
	private Date dueDate;
	//ManyToOne with Backlog
	private String projectIdentifier;
	
	
	
	
	
	
	private Date created_At;
	private Date updated_At;
	
	
	
	
	
	@PrePersist
	protected void onCreate() {
		this.created_At = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updated_At = new Date();
	}

	
	

}
