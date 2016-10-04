/*
 * Source Material (c) 2016 GCD
 * All rights reserved
 */
package co.grandcircus.movies.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.grandcircus.movies.dao.MovieDao;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private MovieDao movieDao;

	/**
	 * List all categories
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listMovies(Model model) {
		model.addAttribute("categories", movieDao.getAllCategories());

		System.out.println("/categories -> category-list.jsp");
		return "category-list";
	}

	/**
	 * Display one category, listing movies in that category
	 */
	@RequestMapping(value = "/{cat}", method = RequestMethod.GET)
	public String displayMovie(@PathVariable String cat, Model model) {
		model.addAttribute("category", cat);
		model.addAttribute("movies", movieDao.getMoviesByCategory(cat));

		System.out.println("/categories/" + cat + " -> category.jsp");
		return "category";
	}

}
