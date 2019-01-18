/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iko.iko;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author win 10
 */
public class EntityManagerConfig {
    private static EntityManagerFactory emf;

    public static void setup() {
        EntityManagerConfig.emf = Persistence.createEntityManagerFactory("DEFAULT_PU");
    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }
    
    
}
