/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iko.service;

import com.iko.iko.domain.Movie;
import java.util.List;

/**
 *
 * @author win 10
 */
public interface FavoryService {
    public void setFavory(Long movieId);
    public void removeFavory(Long movieId);
    public List<Movie> gteAllFavory();
}
