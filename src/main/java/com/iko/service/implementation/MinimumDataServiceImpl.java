/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iko.service.implementation;

import com.iko.iko.EntityManagerConfig;
import com.iko.iko.domain.Movie;
import com.iko.iko.domain.MovieType;
import com.iko.iko.domain.User;
import com.iko.iko.repository.MovieJpaController;
import com.iko.iko.repository.UserJpaController;
import com.iko.service.MinimumDataService;
import java.util.ArrayList;

/**
 *
 * @author win 10
 */
public class MinimumDataServiceImpl implements MinimumDataService {
    
    MovieJpaController mjc = new MovieJpaController(EntityManagerConfig.getEmf());
    UserJpaController ujc = new UserJpaController(EntityManagerConfig.getEmf());
    
    @Override
    public void setupUser() {
        final User admin = new User();
        admin.setPassword("admin");
        admin.setUsername("admin");
        ujc.create(admin);
        
        final User user = new User();
        user.setPassword("user");
        user.setUsername("user");
        ujc.create(user);
        
        final User client = new User();
        client.setPassword("client");
        client.setUsername("client");
        ujc.create(client);
    }
    
    @Override
    public void setupMovies() {
        Movie movie;

        //premier film  le titre doit etre comme l image
        movie = new Movie();
        movie.setNotes(new ArrayList<>());
        movie.setTitle("AQUAMAN");
        movie.setType(MovieType.ACTION);
        movie.setDetail("Aquaman est un film de super-héros américain réalisé par James Wan, \n"
                + "sorti en 2018. Il s'agit de la première aventure solo du super-héros Aquaman,\n"
                + " déjà apparu dans Batman v Superman : L'Aube de la justice et Justice League de Zack Snyder.\n"
                + " Ce film est le sixième de l'univers cinématographique DC.");
        mjc.create(movie);
        
        //deuxieme film
        movie = new Movie();
        movie.setNotes(new ArrayList<>());
        movie.setTitle("");
        movie.setType(MovieType.ACTION);
        movie.setDetail("");
        mjc.create(movie);
        
        
        
    }
    
}
