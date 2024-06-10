package com.mycompany.qpims3;
// Below are the imports help in fetching library and thier elements to be used in application. 

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene; // This code will create a private static Scene variable for developing the scene when the application is launched.

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("JobSearch"), 600, 700); // This code will set the scene to the "LoginFXML" page, hence, will be able to login. 
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml)); // FXML loader
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml")); // This code will help in creating a FXMLloader object which will help in getting the right resource from file.
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(); // This code will help in launching the application. 
    }

}
