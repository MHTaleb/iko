/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iko.iko;

import com.iko.iko.domain.User;
import com.iko.service.ConnectionService;
import com.iko.service.implementation.ConnectionServiceImpl;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author win 10
 */
public class LoginController implements Initializable {

    private ConnectionService connectionService;

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        connectionService = new ConnectionServiceImpl();
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void connect(ActionEvent event) throws IOException {
        if (username.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur champ manquant");
            alert.setContentText("Veuillez saisir le nom utilisateur ");
            alert.show();
            username.requestFocus();
            return;
        }
        if (password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur champ manquant");
            alert.setContentText("Veuillez saisir le mot de passe ");
            alert.show();
            password.requestFocus();
            return;
        }

        User doConnect = connectionService.doConnect(username.getText(), password.getText());
        if (doConnect != null) {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur Connexion");
            alert.setContentText("nom utilisateur ou mot de passe incorrect");
            alert.show();
            username.setText("");
            password.setText("");
            username.requestFocus();
        }
    }

}
