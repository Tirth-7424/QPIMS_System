/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qpims3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author tirth
 */
public class JobModel {

    private static final String URL = "jdbc:mysql://localhost/qpims";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "bqqolyrm"; //your own password to Root account of MySQL

    private Connection connection = null; // manages connection
    private PreparedStatement selectAllJobs = null;
    private PreparedStatement selectJobByJobId = null;
    private PreparedStatement selectJobByPropertyId = null;
    private PreparedStatement updateJob = null;
    private PreparedStatement CreateJob = null;
    private PreparedStatement getNumberOfJobsByType = null;

    CustomerModel cm = new CustomerModel();
    PropertyModel pm = new PropertyModel();

    public JobModel() {
        try {
            connection
                    = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // create query that selects all entries in the table
            selectAllJobs
                    = connection.prepareStatement("SELECT * FROM repairjob");

            // create query that selects entries with a specific name
            selectJobByJobId = connection.prepareStatement(
                    "SELECT * FROM repairjob WHERE JobID = ?");
            // create query that selects entirs with a specific id
            selectJobByPropertyId = connection.prepareStatement(
                    "SELECT * FROM repairjob WHERE property_PropertyID = ?");
            // create insert that adds a new entry into the database

            CreateJob = connection.prepareStatement(
                    "INSERT INTO repairjob "
                    + "( JobDescription, JobStartDate, JobcompletionDate, JobCost, JobServiceMan, JobType, JobStatus, property_PropertyID) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )");

            updateJob = connection.prepareStatement(
                    "UPDATE repairjob "
                    + "SET JobDescription = ?, JobStartDate = ?, JobcompletionDate = ?, JobCost = ?, JobServiceMan = ?, JobType = ?, JobStatus = ?, property_PropertyID = ? "
                    + "WHERE JobID = ?"
            );
            
            getNumberOfJobsByType = connection.prepareStatement(
                    "SELECT COUNT(*) AS jobCount "
                    + "FROM repairjob "
                    + "WHERE JobType = ? AND JobStatus = 'Completed' "
            );
            

        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        } // end catch
    } // end Property constructor

    public void close() {
        try {
            connection.close();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
    } // end method close

    public int addJob(String JobDescription, String JobStartDate, String JobcompletionDate, double JobCost, String JobServiceMan, String JobType, String JobStatus, int property_PropertyID) {
        int result = 0;

        // set parameters, then execute insertNewPatient
        try {
            CreateJob.setString(1, JobDescription);
            CreateJob.setString(2, JobStartDate);
            CreateJob.setString(3, JobcompletionDate);
            CreateJob.setDouble(4, JobCost);
            CreateJob.setString(5, JobServiceMan);
            CreateJob.setString(6, JobType);
            CreateJob.setString(7, JobStatus);
            CreateJob.setInt(8, property_PropertyID);

            // insert the new entry; returns # of rows updated
            result = CreateJob.executeUpdate();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch

        return result;
    } // end method

    public int updateJob(String JobDescription, String JobStartDate, String JobcompletionDate, double JobCost, String JobServiceMan, String JobType, String JobStatus, int property_PropertyID, int JobId) {
        int result = 0;

        // set parameters, then execute insertNewPatient
        try {
            updateJob.setString(1, JobDescription);
            updateJob.setString(2, JobStartDate);
            updateJob.setString(3, JobcompletionDate);
            updateJob.setDouble(4, JobCost);
            updateJob.setString(5, JobServiceMan);
            updateJob.setString(6, JobType);
            updateJob.setString(7, JobStatus);
            updateJob.setInt(8, property_PropertyID);
            updateJob.setInt(9, JobId);
            // insert the new entry; returns # of rows updated
            result = updateJob.executeUpdate();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch

        return result;
    } // end method

    public List< Job> getJobsByJobID(int id) {
        List< Job> results = null;
        ResultSet resultSet = null;

        try {
            selectJobByJobId.setInt(1, id); // specify id

            // executeQuery returns ResultSet containing matching entries
            resultSet = selectJobByJobId.executeQuery();

            results = new ArrayList<Job>();

            while (resultSet.next()) {
                int jobId = resultSet.getInt("JobID");
                int propertyId = resultSet.getInt("property_PropertyID");
                String jobStartDate = resultSet.getString("JobStartDate");
                String jobCompletionDate = resultSet.getString("JobcompletionDate");
                double jobCost = resultSet.getDouble("JobCost");
                String jobServiceMan = resultSet.getString("JobServiceMan");
                String jobDescription = resultSet.getString("JobDescription");
                String jobType = resultSet.getString("JobType");
                String jobStatus = resultSet.getString("JobStatus");

                if (jobCompletionDate == null) {
                    System.out.println("Hi");
                    results.add(new Job(jobId, propertyId, jobStartDate, jobCost, jobServiceMan, jobDescription, jobType, jobStatus));
                } else {
                    results.add(new Job(jobId, propertyId, jobStartDate, jobCompletionDate, jobCost, jobServiceMan, jobDescription, jobType, jobStatus));
                }
            } // end while
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
        finally {
            try {
                resultSet.close();
            } // end try
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            } // end catch
        } // end finally

        return results;
    } // end method getByName
    // add an entry

    public List< Job> getJobsByPropertyID(int id) {
        List< Job> results = null;
        ResultSet resultSet = null;

        try {
            selectJobByPropertyId.setInt(1, id); // specify id

            // executeQuery returns ResultSet containing matching entries
            resultSet = selectJobByPropertyId.executeQuery();

            results = new ArrayList<Job>();

            while (resultSet.next()) {
                int jobId = resultSet.getInt("JobID");
                int propertyId = resultSet.getInt("property_PropertyID");
                String jobStartDate = resultSet.getString("JobStartDate");
                String jobCompletionDate = resultSet.getString("JobcompletionDate");
                double jobCost = resultSet.getDouble("JobCost");
                String jobServiceMan = resultSet.getString("JobServiceMan");
                String jobDescription = resultSet.getString("JobDescription");
                String jobType = resultSet.getString("JobType");
                String jobStatus = resultSet.getString("JobStatus");

                if (jobCompletionDate == null) {
                    System.out.println("Hi");
                    results.add(new Job(jobId, propertyId, jobStartDate, jobCost, jobServiceMan, jobDescription, jobType, jobStatus));
                } else {
                    results.add(new Job(jobId, propertyId, jobStartDate, jobCompletionDate, jobCost, jobServiceMan, jobDescription, jobType, jobStatus));
                }
            } // end while
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
        finally {
            try {
                resultSet.close();
            } // end try
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            } // end catch
        } // end finally

        return results;
    }

    public List< Job> getAllJobs() {
        List< Job> results = null;
        ResultSet resultSet = null;

        try {

            // executeQuery returns ResultSet containing matching entries
            resultSet = selectAllJobs.executeQuery();

            results = new ArrayList<Job>();

            while (resultSet.next()) {
                int jobId = resultSet.getInt("JobID");
                int propertyId = resultSet.getInt("property_PropertyID");
                String jobStartDate = resultSet.getString("JobStartDate");
                String jobCompletionDate = resultSet.getString("JobcompletionDate");
                double jobCost = resultSet.getDouble("JobCost");
                String jobServiceMan = resultSet.getString("JobServiceMan");
                String jobDescription = resultSet.getString("JobDescription");
                String jobType = resultSet.getString("JobType");
                String jobStatus = resultSet.getString("JobStatus");

                if (jobCompletionDate == null) {
                    System.out.println("Hi");
                    results.add(new Job(jobId, propertyId, jobStartDate, jobCost, jobServiceMan, jobDescription, jobType, jobStatus));
                } else {
                    results.add(new Job(jobId, propertyId, jobStartDate, jobCompletionDate, jobCost, jobServiceMan, jobDescription, jobType, jobStatus));
                }
            } // end while
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
        finally {
            try {
                resultSet.close();
            } // end try
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            } // end catch
        } // end finally

        return results;
    }

    public boolean findProperty(int PID) {
        List< Property> results = null;
        boolean propertyfound = false;
        results = pm.getAllProperties();

        for (int i = 0; i < results.size(); i++) {
            if (PID == results.get(i).getId()) {
                propertyfound = true;
            }

        }
        return propertyfound;
    }
    
    public int getNumberOfJobsByType(String jobType) throws SQLException {
        getNumberOfJobsByType.setString(1, jobType);
        ResultSet resultSet = getNumberOfJobsByType.executeQuery();

        if (resultSet.next()) { // This moves the cursor to the first row of the result set
            return resultSet.getInt("jobCount");
        } else {
            return 0;
        }
    }
    
    public double[] getAverageMinMax(){
        List<Job> allJobs = getAllJobs();
        double sum = 0;
        double average = 0;
        double min = 0;
        double max = 0;
        
        // Sort jobs by charge
        Collections.sort(allJobs, new Comparator<Job>() {    
            @Override
            public int compare(Job j1, Job j2) {
                if (j1.getCharge() > j2.getCharge()) {
                    return 1;
                } else{
                    return -1;
                }
            }
        });
        
        // Calculate average
        for(int i = 0; i < allJobs.size()-1;i++){
            sum += allJobs.get(i).getCharge();
        }
        
        // Get min, man, and average
        average = sum/allJobs.size();
        min = allJobs.get(0).getCharge();
        max = allJobs.get(allJobs.size()-1).getCharge();
        double[] jobTypeStats = {average, min, max};
        
        return jobTypeStats;
    }

}
