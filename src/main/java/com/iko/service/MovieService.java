/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iko.service;

import com.iko.iko.domain.Movie;
import com.iko.iko.domain.MovieType;
import java.util.List;

/**
 *
 * @author win 10
 */
public interface MovieService {
    public List<Movie> getAllMovies();
    public List<Movie> getAllMoviesByType(String typeName);
    public List<Movie> getAllMoviesByType(MovieType typeName);
    public List<Movie> getAllMoviesByTitle(String title);
    
    
    public List<Movie> getAllMoviesByAdvancedSearch(String title,String typeName,List keyWords);
    public List<Movie> getAllMoviesByAdvancedSearch(String title,MovieType typeName,List keyWords);
 }
