package com.iko.iko;

import com.iko.iko.domain.Movie;
import com.iko.iko.domain.User;
import com.iko.service.MovieService;
import com.iko.service.implementation.ConnectionServiceImpl;
import com.iko.service.implementation.MovieServiceImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

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
    private JFXComboBox<Integer> movie1Note;
    @FXML
    private VBox movie2Panel;
    @FXML
    private Label movie2Title;
    @FXML
    private ImageView movie2Image;
    @FXML
    private JFXCheckBox movie2Favori;
    @FXML
    private JFXComboBox<Integer> movie2Note;
    @FXML
    private VBox movie3Panel;
    @FXML
    private Label movie3Title;
    @FXML
    private ImageView movie3Image;
    @FXML
    private JFXCheckBox movie3Favori;
    @FXML
    private JFXComboBox<Integer> movie3Note;
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

    }

    @FXML
    private void searchEvent(ActionEvent event) {
    }

    @FXML
    private void showPrevious(ActionEvent event) {
    }

    @FXML
    private void showNext(ActionEvent event) {
    }

    @FXML
    private void advancedSearch(ActionEvent event) {
    }



    private void fetchInitialData() {
        movies.addAll(movieService.getAllMovies());

        try {
            Movie movie = movies.get(0);
            movie1Image.setImage(new Image("c:/Films/"+ movie.getTitle() + ".jpg"));

            movie = movies.get(1);
            movie2Image.setImage(new Image("c:/Films/"+ movie.getTitle() + ".jpg"));

            movie = movies.get(2);
            movie3Image.setImage(new Image("c:/Films/"+ movie.getTitle() + ".jpg"));

        } catch (Exception e) {
            System.out.println(" images not found");
        }

    }

    private void initObservables() {
        movies = FXCollections.emptyObservableList();
    }


    private ObservableList<Movie> movies;

    private final MovieService movieService = new MovieServiceImpl();
    private User currentUser;
}
