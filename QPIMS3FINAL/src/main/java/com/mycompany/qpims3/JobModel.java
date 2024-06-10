/*
The purpose of the JobModel class is primarily to retrieve and update job 
related data in the SQL database. It contains methods useful for controller
classes to search for job entries, create job entries, update job entries, and
get other job related data.
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

public class JobModel {

    private static final String URL = App.URL;

    private static final String USERNAME = App.rootUserName;
    private static final String PASSWORD = App.rootPassword;

    private Connection connection = null; // manages connection
    private PreparedStatement selectAllJobs = null;
    private PreparedStatement selectJobByJobId = null;
    private PreparedStatement selectJobByPropertyId = null;
    private PreparedStatement updateJob = null;
    private PreparedStatement CreateJob = null;
    private PreparedStatement getNumberOfJobsByType = null;
    private PreparedStatement getLastId = null; // Prepared Statement for fetching the last ID to display the customer ID.

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

            //
            updateJob = connection.prepareStatement(
                    "UPDATE repairjob "
                    + "SET JobDescription = ?, JobStartDate = ?, JobcompletionDate = ?, JobCost = ?, JobServiceMan = ?, JobType = ?, JobStatus = ?, property_PropertyID = ? "
                    + "WHERE JobID = ?"
            );
            
            // Counts the number of completed jobs of a certain type
            getNumberOfJobsByType = connection.prepareStatement(
                    "SELECT COUNT(*) AS jobCount "
                    + "FROM repairjob "
                    + "WHERE JobType = ? AND JobStatus = 'Completed' "
            );
            getLastId = connection.prepareStatement("SELECT * FROM repairjob ORDER BY JobID DESC LIMIT 1");

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

    // Returns a list of Job objects based on job id
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

    // Returns a list of Job objects based on property id
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

    // Returns all jobs as a list of Job objects
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

    // Returns the number of completed jobs by type
    public int getNumberOfJobsByType(String jobType) throws SQLException {
        getNumberOfJobsByType.setString(1, jobType);
        ResultSet resultSet = getNumberOfJobsByType.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("jobCount");
        } else {
            return 0;
        }
    }

    // Returns an array containing average, minimum, and maximum charges for jobs
    public double[] getAverageMinMax() {
        List<Job> allJobs = getAllJobs();
        double sum = 0;
        double average = 0;
        double min = 0;
        double max = 0;

        // Sort jobs by charge and assign minimum and maximum charge
        Collections.sort(allJobs, new Comparator<Job>() {
            @Override
            public int compare(Job j1, Job j2) {
                if (j1.getCharge() > j2.getCharge()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        min = allJobs.get(0).getCharge();
        max = allJobs.get(allJobs.size() - 1).getCharge();

        // Calculate average
        for (int i = 0; i < allJobs.size() - 1; i++) {
            sum += allJobs.get(i).getCharge();
        }
        average = sum / allJobs.size();

        // Return array of doubles
        double[] jobTypeStats = {average, min, max};
        return jobTypeStats;
    }
    public int getLastId() {
        List< Job> results = null;
        ResultSet resultSet = null;

        try {
            

            // executeQuery returns ResultSet containing matching entries
            resultSet = getLastId.executeQuery();

            results = new ArrayList< Job>();

            while (resultSet.next()) {
                results.add(new Job( // adding matching entries from ResultSet to result.
                 resultSet.getInt("JobID"),
                resultSet.getInt("property_PropertyID"),
                resultSet.getString("JobStartDate"),
                 resultSet.getString("JobcompletionDate"),
                resultSet.getDouble("JobCost"),
                resultSet.getString("JobServiceMan"),
                resultSet.getString("JobDescription"),
                resultSet.getString("JobType"),
                resultSet.getString("JobStatus")
                ));
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

        return results.get(0).getJobId();
    } // end method 

}
