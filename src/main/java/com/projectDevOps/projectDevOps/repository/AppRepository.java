package com.projectDevOps.projectDevOps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projectDevOps.projectDevOps.model.AppModel;

public interface AppRepository extends JpaRepository<AppModel, Long> {
	
	List<AppModel> findByPublished(boolean published);
	List<AppModel> findByTitleContaining(String title);

}
