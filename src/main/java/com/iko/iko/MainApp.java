package com.iko.iko;

import com.iko.service.implementation.MinimumDataServiceImpl;
import com.iko.service.MinimumDataService;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    private static void persistData() {
        MinimumDataService mds = new MinimumDataServiceImpl(); 
        mds.setupUser();
        mds.setupMovies();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // installer la configuration jpa
        EntityManagerConfig.setup();
        // preciser les données de depart
        persistData();
        //lancer l applcation javaFx
        launch(args);
    }

}
