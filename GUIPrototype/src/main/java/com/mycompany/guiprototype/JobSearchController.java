/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guiprototype;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author llama
 */
public class JobSearchController {
    @FXML
    private Button firstResultButton;
    @FXML
    private Button secondResultButton;
    @FXML
    private Button thirdResultButton;
    @FXML
    private Button updateButton;
    
    @FXML
    private void switchToCreateJob() throws IOException {
        App.setRoot("createJob");
    }
    
    @FXML
    private void switchToUpdateJob() throws IOException {
        App.setRoot("updateJob");
    }
    
    @FXML
    private void underlineFirst() throws IOException {
        this.firstResultButton.setUnderline(true);
        this.secondResultButton.setUnderline(false);
        this.thirdResultButton.setUnderline(false);
        updateButton.setDisable(false);
    }
    
    @FXML
    private void underlineSecond() throws IOException {
        this.firstResultButton.setUnderline(false);
        this.secondResultButton.setUnderline(true);
        this.thirdResultButton.setUnderline(false);
        updateButton.setDisable(false);
    }
    
    @FXML
    private void underlineThird() throws IOException {
        this.firstResultButton.setUnderline(false);
        this.secondResultButton.setUnderline(false);
        this.thirdResultButton.setUnderline(true);
        updateButton.setDisable(false);
    }
    
    @FXML
    private void goBack() throws IOException {
        App.setRoot("mainMenu");
    }
}
