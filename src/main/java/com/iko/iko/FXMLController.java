package com.iko.iko;

import com.iko.iko.domain.Movie;
import com.iko.iko.domain.Note;
import com.iko.iko.domain.User;
import com.iko.service.FavoryService;
import com.iko.service.MovieNoteService;
import com.iko.service.MovieService;
import com.iko.service.implementation.ConnectionServiceImpl;
import com.iko.service.implementation.FavoryServiceImpl;
import com.iko.service.implementation.MovieNoteServiceImpl;
import com.iko.service.implementation.MovieServiceImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;

public class FXMLController implements Initializable {

    private Label label;
    @FXML
    private JFXTextField searchField;
    @FXML
    private VBox movie1Panel;
    @FXML
    private Label movie1Title;
    @FXML
    private ImageView movie1Image;
    @FXML
    private JFXCheckBox movie1Favori;
    @FXML
    private Rating movie1Note;
    @FXML
    private VBox movie2Panel;
    @FXML
    private Label movie2Title;
    @FXML
    private ImageView movie2Image;
    @FXML
    private JFXCheckBox movie2Favori;
    @FXML
    private Rating movie2Note;
    @FXML
    private VBox movie3Panel;
    @FXML
    private Label movie3Title;
    @FXML
    private ImageView movie3Image;
    @FXML
    private JFXCheckBox movie3Favori;
    @FXML
    private Rating movie3Note;
    @FXML
    private VBox menu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        currentUser = ConnectionServiceImpl.getConnectedUser();
        initObservables();
        fetchInitialData();
    }

    @FXML
    private void menuEvent(ActionEvent event) {
        JFXToggleButton menuButton = (JFXToggleButton) event.getSource();
        menu.setVisible(menuButton.isSelected());
        menu.setPrefWidth(-1 * menu.getPrefWidth());
        menu.setMinWidth(-1 * menu.getPrefWidth());
        menu.setMaxWidth(-1 * menu.getPrefWidth());
    }

    @FXML
    private void movieFilterEvent(ActionEvent event) {
        JFXButton filterButton = (JFXButton) event.getSource();
        String filter = filterButton.getText();
        if (!filter.isEmpty()) {
            List<Movie> allMoviesByTitle = movieService.getAllMoviesByType(filter);
            movies.clear();
            movies.addAll(allMoviesByTitle);
            Movie1Index = 0;
            Movie2Index = 1;
            Movie3Index = 2;
            showMovies(Movie1Index, Movie2Index, Movie3Index);
        }

    }

    @FXML
    private void searchEvent(ActionEvent event) {
        String searchValue = searchField.getText();
        if (!searchValue.isEmpty()) {
            List<Movie> allMoviesByTitle = movieService.getAllMoviesByTitle(searchValue);
            movies.clear();
            movies.addAll(allMoviesByTitle);
            Movie1Index = 0;
            Movie2Index = 1;
            Movie3Index = 2;
            showMovies(Movie1Index, Movie2Index, Movie3Index);
        }

    }

    @FXML
    private void showPrevious(ActionEvent event) {
        if (Movie1Index > 0) {
            Movie1Index -= 1;
            Movie2Index -= 1;
            Movie3Index -= 1;
            showMovies(Movie1Index, Movie2Index, Movie3Index);
        }
    }

    @FXML
    private void showNext(ActionEvent event) {
        if (Movie3Index < movies.size() - 1) {
            Movie3Index += 1;
            Movie2Index += 1;
            Movie1Index += 1;
            showMovies(Movie1Index, Movie2Index, Movie3Index);
        }
    }

    @FXML
    private void advancedSearch(ActionEvent event) {
    }

    @FXML
    private void updateMovie1Note(MouseEvent event) {
        try {
            System.out.println("movie panel 1 index is " + Movie1Index);
            final double rating = movie1Note.getRating();
            double updateNote = movieNoteService.updateNote(movies.get(Movie1Index).getId(), rating);
            movie1Note.setRating(updateNote);
            getData();
            System.out.println("event end with note " + updateNote + " current input ratting " + rating);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateMovie2Note(MouseEvent event) {
        try {
            System.out.println("movie panel 2 index is " + Movie2Index);
            final double rating = movie2Note.getRating();
            double updateNote = movieNoteService.updateNote(movies.get(Movie2Index).getId(), rating);
            movie2Note.setRating(updateNote);
            System.out.println("event end");
            getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateMovie3Note(MouseEvent event) {
        try {
            System.out.println("movie panel 3 index is " + Movie3Index);
            final double rating = movie3Note.getRating();
            double updateNote = movieNoteService.updateNote(movies.get(Movie3Index).getId(), rating);
            movie3Note.setRating(updateNote);
            getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void movie1favoryEvent(ActionEvent event) {
        if (movie1Favori.isSelected()) {
            favoryService.setFavory(movies.get(Movie1Index).getId());
        }else{
            favoryService.removeFavory(movies.get(Movie1Index).getId());
        }
    }

    @FXML
    private void movie2favoryEvent(ActionEvent event) {
        if (movie2Favori.isSelected()) {
            favoryService.setFavory(movies.get(Movie2Index).getId());
        }else{
            favoryService.removeFavory(movies.get(Movie2Index).getId());
        }
    }

    @FXML
    private void movie3favoryEvent(ActionEvent event) {
        if (movie3Favori.isSelected()) {
            favoryService.setFavory(movies.get(Movie3Index).getId());
        }else{
            favoryService.removeFavory(movies.get(Movie3Index).getId());
        }
    }

    private void fetchInitialData() {

        getData();

        showMovies(Movie1Index, Movie2Index, Movie3Index);

    }

    private void getData() {
        List<Movie> allMovies = movieService.getAllMovies();
        movies.clear();
        for (Movie movie : allMovies) {
            System.out.println(movie);
            movies.add(movie);
        }
    }

    private void showMovies(int Movie1IndexParam, int Movie2IndexParam, int Movie3IndexParam) {
        try {
            double somme;
            int size;
            Movie movie;

            try {
                movie1Panel.setVisible(true);
                somme = 0;
                size = 0;
                movie = movies.get(Movie1IndexParam);
                movie1Title.setText(movie.getTitle());
                movie1Image.setImage(new Image("file:///C:/Films/" + movie.getTitle() + ".jpg"));
                if (!movie.getNotes().isEmpty()) {
                    for (Note note : movie.getNotes()) {
                        somme += note.getValeur();
                    }
                    size = movie.getNotes().size();
                    movie1Note.setRating(somme / size);
                } else {
                    movie1Note.setRating(0);

                }
                
                movie1Favori.setSelected(favoryService.gteAllFavory().contains(movie));

            } catch (IndexOutOfBoundsException e) {
                movie1Panel.setVisible(false);
            }

            try {

                movie2Panel.setVisible(true);
                somme = 0;
                size = 0;
                movie = movies.get(Movie2IndexParam);
                movie2Title.setText(movie.getTitle());
                movie2Image.setImage(new Image("file:///c:/Films/" + movie.getTitle() + ".jpg"));
                if (!movie.getNotes().isEmpty()) {
                    for (Note note : movie.getNotes()) {
                        somme += note.getValeur();
                    }
                    size = movie.getNotes().size();
                    movie2Note.setRating(somme / size);
                } else {
                    movie2Note.setRating(0);

                }

                movie2Favori.setSelected(favoryService.gteAllFavory().contains(movie));
            } catch (IndexOutOfBoundsException e) {
                movie2Panel.setVisible(false);
            }

            try {
                movie3Panel.setVisible(true);
                somme = 0;
                size = 0;
                movie = movies.get(Movie3IndexParam);
                movie3Title.setText(movie.getTitle());
                movie3Image.setImage(new Image("file:///c:/Films/" + movie.getTitle() + ".jpg"));
                if (!movie.getNotes().isEmpty()) {
                    for (Note note : movie.getNotes()) {
                        somme += note.getValeur();
                    }
                    size = movie.getNotes().size();
                    movie3Note.setRating(somme / size);
                } else {
                    movie3Note.setRating(0);

                }

                movie3Favori.setSelected(favoryService.gteAllFavory().contains(movie));
            } catch (IndexOutOfBoundsException e) {
                movie3Panel.setVisible(false);
            }

        } catch (Exception e) {
            System.out.println(" images not found");
            e.printStackTrace();
        }
    }

    private void initObservables() {
        movies = FXCollections.observableArrayList();
    }

    private ObservableList<Movie> movies;

    private final MovieService movieService = new MovieServiceImpl();
    private final MovieNoteService movieNoteService = new MovieNoteServiceImpl();
    private final FavoryService favoryService = new FavoryServiceImpl();
    private User currentUser;

    int Movie1Index = 0;
    int Movie2Index = 1;
    int Movie3Index = 2;
}
