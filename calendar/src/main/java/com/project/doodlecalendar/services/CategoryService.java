package com.project.doodlecalendar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.doodlecalendar.daos.CategoryDao;
import com.project.doodlecalendar.daos.DaoException;
import com.project.doodlecalendar.entities.Category;

@Service
public class CategoryService {

	@Autowired
	@Qualifier("htCatDao")
	CategoryDao catDao;

	public List<Category> getAllCategory() throws DaoException{
		return catDao.getAllCategory();
	}
}
