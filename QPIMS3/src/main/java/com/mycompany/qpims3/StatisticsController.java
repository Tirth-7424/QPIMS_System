/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qpims3;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;



/**
 * FXML Controller class
 *
 * @author tirth
 */
public class StatisticsController implements Initializable {
    
    @FXML
    private Text maxChargeText;
    @FXML
    private Text minChargeText;
    @FXML
    private Text averageChargeText;
    
    @FXML
    private BarChart barChart;
    
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    XYChart.Series<String, Number> series1 = new XYChart.Series<>(); 
    JobModel model=new JobModel();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        xAxis.setLabel("Country");       
        yAxis.setLabel("Value");
        series1.setName("Job Type"); 
        
        for(int i = 0; i < Job.JobType.values().length; i++){
            try {
                String jobType = ""+Job.JobType.values()[i];
                int numberOfJobs = model.getNumberOfJobsByType(jobType);
                System.out.println(jobType.toString()+" "+numberOfJobs);
                series1.getData().add(new XYChart.Data<>(jobType, numberOfJobs));
            } catch (SQLException ex) {
                Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        barChart.getData().addAll(series1);
        
        double[] jobTypeStats = model.getAverageMinMax();
        
        maxChargeText.setText("Maximum charge: "+ jobTypeStats[2]);
        minChargeText.setText("Minimum charge: "+ jobTypeStats[1]);
        averageChargeText.setText("Average charge: "+ jobTypeStats[0]);
        System.out.println("Average: "+jobTypeStats[0]+"\nMinimum charge: "+jobTypeStats[1]+"\nMaximum charge: "+jobTypeStats[2]);
    }



    @FXML
    private void goBack() throws IOException{
        App.setRoot("MainMenuFXML");
    }

}
