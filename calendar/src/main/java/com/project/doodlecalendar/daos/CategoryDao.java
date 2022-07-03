package com.project.doodlecalendar.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doodlecalendar.entities.Category;




@Repository
public interface CategoryDao {
	
	public List<Category> getAllCategory() throws DaoException;
}
