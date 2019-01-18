/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iko.service.implementation;

import com.iko.iko.EntityManagerConfig;
import com.iko.iko.domain.User;
import com.iko.iko.repository.UserJpaController;
import com.iko.service.ConnectionService;
import java.util.Optional;
import java.util.function.Predicate;

/**
 *
 * @author win 10
 */
public class ConnectionServiceImpl implements ConnectionService {

    private static User connectedUser;
    
    UserJpaController ujc = new UserJpaController(EntityManagerConfig.getEmf());

    @Override
    public User doConnect(String username, String password) {
        User user = null;

        Predicate<? super User> usernamePredicate = curUser -> {
            return curUser.getUsername().toLowerCase().equals(username.toLowerCase());
        };
        final Optional<User> optionalUser = ujc.findUserEntities().stream().filter(usernamePredicate).findFirst();

        if (optionalUser.isPresent()) {
            
            User userTemporal = optionalUser.get();    
            if (userTemporal.getPassword().equals(password)) {
                user = userTemporal;
                connectedUser = user;
            } 
        }
        return user;


    }

    @Override
    public User doRegister(String username, String password) {
        
        try {
            
        User user = new User();
        
        user.setPassword(password);
        user.setUsername(username);
        
        ujc.create(user);
        
        return user;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    public static User getConnectedUser() {
        return connectedUser;
    }
    
    

}
