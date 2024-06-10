/*
The StatisticsController class is the controller for statistics.fxml. It
populats a bar chart that demonstrates the distribution of completed jobs 
by type. It also displays the minimum, maximum, and average charges for
jobs.
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

public class StatisticsController implements Initializable {

    @FXML
    private Text maxChargeText;
    @FXML
    private Text minChargeText;
    @FXML
    private Text averageChargeText;

    @FXML
    private BarChart barChart;

    //Initializes the controller class. Fills out barchart and displays 
    // minimum, maximum, and average job charges
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Fill bar chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        JobModel model = new JobModel();
        series1.setName("Job Type");

        // 
        for (int i = 0; i < Job.JobType.values().length; i++) {
            try {
                String jobType = "" + Job.JobType.values()[i];
                int numberOfJobs = model.getNumberOfJobsByType(jobType);
                series1.getData().add(new XYChart.Data<>(jobType, numberOfJobs));
            } catch (SQLException ex) {
                Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        barChart.getData().addAll(series1);

        // Display the minimum, maximum, and average charge
        double[] jobTypeStats = model.getAverageMinMax();
        maxChargeText.setText("Maximum charge: " + jobTypeStats[2]);
        minChargeText.setText("Minimum charge: " + jobTypeStats[1]);
        averageChargeText.setText("Average charge: " + jobTypeStats[0]);
    }

    @FXML
    private void goBack() throws IOException {
        App.setRoot("MainMenuFXML");
    }

}
