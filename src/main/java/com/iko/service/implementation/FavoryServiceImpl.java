/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iko.service.implementation;

import com.iko.iko.EntityManagerConfig;
import com.iko.iko.domain.Favory;
import com.iko.iko.domain.Movie;
import com.iko.iko.domain.User;
import com.iko.iko.repository.FavoryJpaController;
import com.iko.iko.repository.MovieJpaController;
import com.iko.service.FavoryService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FavoryServiceImpl implements FavoryService {

    FavoryJpaController favoryJpaController = new  FavoryJpaController(EntityManagerConfig.getEmf());
    MovieJpaController movieJpaController = new MovieJpaController(EntityManagerConfig.getEmf());
    
    @Override
    public void setFavory(Long movieId) {
        User connectedUser = ConnectionServiceImpl.getConnectedUser();
        Predicate<? super Favory> userFavoryFilter = favory-> {
            return favory.getUser().getId().longValue() == connectedUser.getId().longValue();
        };
        Optional<Favory> findFavory = favoryJpaController.findFavoryEntities().stream().filter(userFavoryFilter).findFirst();
        Favory favory;
        Movie movie = movieJpaController.findMovie(movieId);
        if(findFavory.isPresent()){
            favory = findFavory.get();
            favory.getMovies().add(movie);
            try {
                favoryJpaController.edit(favory);
            } catch (Exception ex) {
                Logger.getLogger(FavoryServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            favory = new Favory();
            favory.setMovies(new ArrayList<>());
            favory.getMovies().add(movie);
            favory.setUser(connectedUser);
            favoryJpaController.create(favory);
        }
    }

    @Override
    public void removeFavory(Long movieId) {
        User connectedUser = ConnectionServiceImpl.getConnectedUser();
        Predicate<? super Favory> userFavoryFilter = favory-> {
            return favory.getUser().getId().longValue() == connectedUser.getId().longValue();
        };
        Optional<Favory> findFavory = favoryJpaController.findFavoryEntities().stream().filter(userFavoryFilter).findFirst();
        Favory favory;
        Movie movie = movieJpaController.findMovie(movieId);
        if(findFavory.isPresent()){
            favory = findFavory.get();
            favory.getMovies().remove(movie);
            try {
                favoryJpaController.edit(favory);
            } catch (Exception ex) {
                Logger.getLogger(FavoryServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Movie> gteAllFavory() {
        User connectedUser = ConnectionServiceImpl.getConnectedUser();
        Predicate<? super Favory> userFavoryFilter = favory-> {
            return favory.getUser().getId().longValue() == connectedUser.getId().longValue();
        };
        Optional<Favory> findFavory = favoryJpaController.findFavoryEntities().stream().filter(userFavoryFilter).findFirst();
        Favory favory;
        if(findFavory.isPresent()){
            favory = findFavory.get();
            return favory.getMovies();
        }
        return new ArrayList<>();
    }
    
}
