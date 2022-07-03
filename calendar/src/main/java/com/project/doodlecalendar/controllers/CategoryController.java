package com.project.doodlecalendar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doodlecalendar.daos.DaoException;
import com.project.doodlecalendar.entities.Category;
import com.project.doodlecalendar.services.CategoryService;



@RestController
@RequestMapping("categories")
public class CategoryController {

	@Autowired
	private CategoryService catService;
	
	@GetMapping
	public ResponseEntity<?> getCategories(){
		try {
			List<Category> catList = catService.getAllCategory();
			return ResponseEntity.ok(catList);
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e);
		}
	}
	
}
