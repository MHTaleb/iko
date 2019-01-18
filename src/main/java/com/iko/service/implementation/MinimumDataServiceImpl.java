/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iko.service.implementation;

import com.iko.iko.EntityManagerConfig;
import com.iko.iko.domain.User;
import com.iko.iko.repository.UserJpaController;
import com.iko.service.MinimumDataService;


/**
 *
 * @author win 10
 */
public class MinimumDataServiceImpl implements MinimumDataService {

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
        
    }


    
}
