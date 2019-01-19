/**
 * This file was generated by the Jeddict
 */
package com.iko.iko.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author win 10
 */
@Entity
@Table(name = "iko_favory")
public class Favory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    private User user;

    @ManyToMany(targetEntity = Movie.class ,fetch = FetchType.EAGER)
    private List<Movie> movies;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Movie> getMovies() {
        return this.movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

}