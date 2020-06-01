package com.projectDevOps.projectDevOps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Customer")
public class AppModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "published")
	private Boolean published;
	
	public AppModel() {

	}

	public AppModel(String title, String description, boolean published) {
		this.title = title;
		this.description = description;
		this.published = published;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean isPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}
	
	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
	}
	
}
