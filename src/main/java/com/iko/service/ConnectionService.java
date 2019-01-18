/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iko.service;

import com.iko.iko.domain.User;

/**
 *
 * @author win 10
 */
public interface ConnectionService {
    public User doConnect(String username,String password);
    public User doRegister(String username,String password);
}
