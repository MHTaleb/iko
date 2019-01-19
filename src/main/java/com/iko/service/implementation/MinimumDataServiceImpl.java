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
        try {
            
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
        } catch (Exception e) {
            System.out.println("account already made");
        }
    }

    @Override
    public void setupMovies() {
        Movie movie;

        //premier film  le titre doit etre comme l image
        try {

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
            movie.setTitle("THE DARK KNIGHT");
            movie.setType(MovieType.ACTION);
            movie.setDetail("The Dark Knight Rises, ou L'Ascension du Chevalier Noir au Québec et au Nouveau-Brunswick,\n"
                    + "est un film de super-héros américano-britannique réalisé par Christopher Nolan, sorti en 2012. \n"
                    + "Fondé sur le personnage de fiction de DC Comics, Batman,\n"
                    + " c'est le dernier volet de la trilogie comprenant Batman Begins \n(2005) et The Dark Knight : Le Chevalier noir (2008).");
            mjc.create(movie);
            //3em film
            movie = new Movie();
            movie.setNotes(new ArrayList<>());
            movie.setTitle("VENOM");
            movie.setType(MovieType.ACTION);
            movie.setDetail("Venom est un film de super-héros américain réalisé par Ruben Fleischer, sorti en 2018.\n"
                    + " Il s'agit de l’adaptation cinématographique du personnage Venom\n, ennemi de Spider-Man, publié par Marvel Comics.");
            mjc.create(movie);

            //aventure
            movie = new Movie();
            movie.setNotes(new ArrayList<>());
            movie.setTitle("Le retour du roi");
            movie.setType(MovieType.ADVENTUR);
            movie.setDetail("Date de sortie 17 décembre 2003 (3h 21min)\n"
                    + "De Peter Jackson Avec Sean Astin, Elijah Wood, Viggo Mortensen\n "
                    + "Genres Fantastique, Aventure\n"
                    + "Nationalités Américain, Néo-Zélandais");
            mjc.create(movie);

            movie = new Movie();
            movie.setNotes(new ArrayList<>());
            movie.setTitle("LE ROI LION");
            movie.setType(MovieType.ADVENTUR);
            movie.setDetail("Date de reprise 11 avril 2012 - 3D (1h 29min)\n"
                    + "Date de sortie 23 novembre 1994 (1h 29min)\n"
                    + "De Roger Allers, Rob Minkoff Avec Emmanuel Curtil, Dimitri Rougeul, Jean Reno |\n"
                    + "Genres Animation, Aventure \n"
                    + "Nationalité Américain");
            mjc.create(movie);

            //comedie
            movie = new Movie();
            movie.setNotes(new ArrayList<>());
            movie.setTitle("GREEN BOOK");
            movie.setType(MovieType.COMEDY);
            movie.setDetail("Green Book : Sur les routes du sud  est un film américain\n"
                    + " réalisé par Peter Farrelly, sorti en 2018. \n" 
                    + "Il s'agit d'un film biographique sur le chanteur Don Shirley et Tony Lip.\n"
                    + "Titre québécois	Le livre de Green\n"
                    + "Réalisation	Peter Farrelly \n"
                    + "Scénario	Nick Vallelonga, Brian Hayes Currie ,  Peter Farrelly\n"
                    + "Sociétés de production	Amblin Partners\n"
                    + "Pays d’origine	 Etats-Unis\n"
                    + "Genre	comédie dramatique et biographique\n"
                    + "Durée	130 minutes\n"
                    + "Sortie	2018");
            mjc.create(movie);

            movie = new Movie();
            movie.setNotes(new ArrayList<>());
            movie.setTitle("Home Alone");
            movie.setType(MovieType.COMEDY);
            movie.setDetail("Maman, j'ai raté l'avion (Home Alone) est une comédie\n familiale américaine réalisée par Chris Columbus, sortie en 1990.");
            mjc.create(movie);

            movie = new Movie();
            movie.setNotes(new ArrayList<>());
            movie.setTitle("PITCH PERFECT");
            movie.setType(MovieType.COMEDY);
            movie.setDetail("Beca, une étudiante de première année à la Barden University\n,"
                    + "est amenée à rejoindre The Bellas, le groupe de chant composé uniquement de filles de son école. \n"
                    + "En injectant une énergie indispensable dans leur répertoire, \n"
                    + "les Bellas affrontent leurs rivaux masculins dans une compétition sur le campus.\n"
                    + "Directeur: Jason Moore\n"
                    + "Ecrivains: Kay Cannon (scénario), Mickey Rapkin (d'après le livre)\n"
                    + "Actrices: Anna Kendrick, Brittany Snow, Rebel Wilson");
            mjc.create(movie);

            movie = new Movie();
            movie.setNotes(new ArrayList<>());
            movie.setTitle("Shaun of the Dead");
            movie.setType(MovieType.COMEDY);
            movie.setDetail(" sortie en 2004.\n Sous-titré en France « Une comédie romantique avec des zombies »,\n"
                    + "Shaun of the Dead est une comédie horrifique britannique et française réalisée par Edgar Wright,\n"
                    + "il est à la fois un hommage et une parodie de films de série B.\n");
            mjc.create(movie);

            // drama
            movie = new Movie();
            movie.setNotes(new ArrayList<>());
            movie.setTitle("La Mule");
            movie.setType(MovieType.ROMANCE);
            movie.setDetail("Réalisation	Clint Eastwood\n"
                    + "Scénario	Nick Schenk\n"
                    + "Acteurs principaux	:" + " Clint Eastwood ," + "Bradley Cooper ," + "Laurence Fishburne ," + "Alison Eastwood , " + "Michael Pe?a\n"
                    + "Sociétés de production	Malpaso Productions Warner Bros.\n"
                    + "Pays d’origine	 Etats-Unis\n"
                    + "Genre	Drame biographique\n"
                    + "Durée	116 minutes\n"
                    + "Sortie	2018");
            mjc.create(movie);

            movie = new Movie();
            movie.setNotes(new ArrayList<>());
            movie.setTitle("ROOM");
            movie.setType(MovieType.ROMANCE);
            movie.setDetail("Room est un film dramatique canado-irlandais-britannique-américain réalisé par Lenny Abrahamson, sorti en 2015.\n"
                    + "Les deux acteurs principaux sont Brie Larson, jouant la mère du petit garçon de 5 ans,\n"
                    + " Jack, qui est lui joué par Jacob Tremblay.");
            mjc.create(movie);

            //Horreur  
            movie = new Movie();
            movie.setNotes(new ArrayList<>());
            movie.setTitle("La Nonne");
            movie.setType(MovieType.HOROR);// ajoute le type HOROR a la place de type THRILLER
            movie.setDetail("Titre québécois	La Religieuse\n"
                    + "Titre original	The Nun\n"
                    + "Réalisation	Corin Hardy\n"
                    + "Scénario	Gary Dauberman\n"
                    + "Acteurs principaux :\n" + "Taissa Farmiga ," + "Demi?n Bichir ," + "Jonas Bloquet ," + "Charlotte Hope ," + "Ingrid Bisu ," + "Bonnie Aarons\n"
                    + "Pays d’origine	Etats-Unis\n"
                    + "Genre	horreur\n"
                    + "Durée	96 minutes\n"
                    + "Sortie	2018");
            mjc.create(movie);

            movie = new Movie();
            movie.setNotes(new ArrayList<>());
            movie.setTitle("Saw");
            movie.setType(MovieType.HOROR);
            movie.setDetail("Saw ou Décadence au Québec est un film d'horreur (thriller horrifique)\n"
                    + " américano-australien réalisé par James Wan, sorti en 2004.\n" +
                    "C'est le premier volet de la série de films Saw.\n"
                    + "Il est écrit par Leigh Whannell et James Wan, \n"
                    + "qui se sont inspirés de deux autres thrillers : Cube et Seven.");
            mjc.create(movie);

            movie = new Movie();
            movie.setNotes(new ArrayList<>());
            movie.setTitle("Scream");
            movie.setType(MovieType.HOROR);
            movie.setDetail("Scream (en français : « Hurlement ») ou Frissons au Québec,\n"
                    + " est un film d'horreur américain réalisé par Wes Craven, sorti en 1996.\n" 
                    + "Il est le premier film de la tétralogie qui est constituée de Scream 2, Scream 3 et Scream 4.\n "
                    + "La franchise connaît ensuite une série télévisée, dont la saison 1 est diffusée dès juin 2015 sur MTV. \n"
                    + "Il est le 14e film réalisé par Wes Craven et le premier écrit par Kevin Williamson.\n"
                    + "Basé en partie sur l'affaire du tueur de Gainesville1 et fortement inspiré du film Halloween,\n"
                    + "Scream mélange les genres de l'horreur, de la comédie et du whodunit. \n");
            mjc.create(movie);

            movie = new Movie();
            movie.setNotes(new ArrayList<>());
            movie.setTitle("Sabrina");
            movie.setType(MovieType.HOROR);
            movie.setDetail("Date de sortie 20 novembre 2018 en VOD (1h 53min)\n"
                    + "De Rocky Soraya Avec Luna Maya, Jeremy Thomas\n"
                    + "Genre Epouvante-horreur\n"
                    + "Nationalités Indonésien, Singapourien, Malaisien\n");
            mjc.create(movie);
        } catch (Exception e) {
            System.out.println("movies created");
        }

    }

}
