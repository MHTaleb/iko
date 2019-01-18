/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iko.service.implementation;

import com.iko.iko.EntityManagerConfig;
import com.iko.iko.domain.Movie;
import com.iko.iko.domain.MovieType;
import com.iko.iko.repository.MovieJpaController;
import com.iko.service.MovieService;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MovieServiceImpl implements MovieService {

    MovieJpaController mjc = new MovieJpaController(EntityManagerConfig.getEmf());

    @Override
    public List<Movie> getAllMovies() {

        List<Movie> movies = mjc.findMovieEntities();
        return movies;
    }

    @Override
    public List<Movie> getAllMoviesByType(String typeName) {

        Predicate<? super Movie> typeNamePredicate = movie -> {
            return movie.getType().name().toLowerCase().equals(typeName.toLowerCase());
        };

        List<Movie> movies = new ArrayList<>();

        mjc.findMovieEntities().stream().filter(typeNamePredicate).forEach(movie -> {
            movies.add(movie);
        });

        return movies;
    }

    @Override
    public List<Movie> getAllMoviesByType(MovieType typeName) {

        Predicate<? super Movie> typeNamePredicate = movie -> {
            return movie.getType().equals(typeName);
        };

        List<Movie> movies = new ArrayList<>();

        mjc.findMovieEntities().stream().filter(typeNamePredicate).forEach(movie -> {
            movies.add(movie);
        });

        return movies;
    }

    @Override
    public List<Movie> getAllMoviesByTitle(String title) {

        Predicate<? super Movie> typeNamePredicate = movie -> {
            return movie.getTitle().toLowerCase().equals(title);
        };

        List<Movie> movies = new ArrayList<>();

        mjc.findMovieEntities().stream().filter(typeNamePredicate).forEach(movie -> {
            movies.add(movie);
        });

        return movies;
    }

    @Override
    public List<Movie> getAllMoviesByAdvancedSearch(String title, String typeName, List keyWords) {
        List<Movie> movies = new ArrayList();
        Predicate<? super Movie> advancedSearchPredicate = movie -> {
            return movie.getTitle().toLowerCase().contains(title.toLowerCase()) &&
                    movie.getType().name().toLowerCase().equals(typeName) && 
                    checkKeyWords(movie, keyWords);
        };
        mjc.findMovieEntities().stream().filter(advancedSearchPredicate).forEach(movies::add);
        return movies;
    }

    @Override
    public List<Movie> getAllMoviesByAdvancedSearch(String title, MovieType typeName, List keyWords) {
        List<Movie> movies = new ArrayList();
        Predicate<? super Movie> advancedSearchPredicate = movie -> {
            return movie.getTitle().toLowerCase().contains(title.toLowerCase()) &&
                    movie.getType().equals(typeName) &&
                    checkKeyWords(movie, keyWords);
        };
        mjc.findMovieEntities().stream().filter(advancedSearchPredicate).forEach(movies::add);
        return movies;
    }

    private boolean checkKeyWords(Movie movie, List<String> keyWords) {
        if (keyWords.stream().anyMatch((keyWord) -> (movie.getDetail().toLowerCase().contains(keyWord)))) {
            return true;
        }
        return false;
    }

}
