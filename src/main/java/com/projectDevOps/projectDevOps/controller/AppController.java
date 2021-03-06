package com.projectDevOps.projectDevOps.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectDevOps.projectDevOps.model.AppModel;
import com.projectDevOps.projectDevOps.repository.AppRepository;

@CrossOrigin(origins = "${client.url}")
@RestController
@RequestMapping("/api")
public class AppController {
	
	@Autowired
	AppRepository tutorialRepository;
	
	@GetMapping("/tutorials")
	public ResponseEntity<List<AppModel>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<AppModel> tutorials = new ArrayList();
			
			if(title == null) {
				tutorialRepository.findAll().forEach(tutorials::add);
			} else {
				tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
			}
			 if (tutorials.isEmpty()) {
			        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			      }

			      return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	 @GetMapping("/tutorials/{id}")
	  public ResponseEntity<AppModel> getTutorialById(@PathVariable("id") long id) {
	    Optional<AppModel> tutorialData = tutorialRepository.findById(id);

	    if (tutorialData.isPresent()) {
	      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	  @PostMapping("/tutorials")
	  public ResponseEntity<AppModel> createTutorial(@RequestBody AppModel tutorial) {
	    try {
	    	AppModel _tutorial = tutorialRepository
	          .save(new AppModel(tutorial.getTitle(), tutorial.getDescription(), false));
	      return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	  
	  @PutMapping("/tutorials/{id}")
	  public ResponseEntity<AppModel> updateTutorial(@PathVariable("id") long id, @RequestBody AppModel tutorial) {
	    Optional<AppModel> tutorialData = tutorialRepository.findById(id);

	    if (tutorialData.isPresent()) {
	    	AppModel _tutorial = tutorialData.get();
	      _tutorial.setTitle(tutorial.getTitle());
	      _tutorial.setDescription(tutorial.getDescription());
	      _tutorial.setPublished(tutorial.isPublished());
	      return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  @DeleteMapping("/tutorials/{id}")
	  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
	    try {
	      tutorialRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	  
	  @DeleteMapping("/tutorials")
	  public ResponseEntity<HttpStatus> deleteAllTutorials() {
	    try {
	      tutorialRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }

	  }
	  
	  @GetMapping("/tutorials/published")
	  public ResponseEntity<List<AppModel>> findByPublished() {
	    try {
	      List<AppModel> tutorials = tutorialRepository.findByPublished(true);

	      if (tutorials.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(tutorials, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	  


}
