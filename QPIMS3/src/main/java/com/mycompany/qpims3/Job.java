/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qpims3;
import java.time.LocalDate;
/**
 *
 * @author tirth
 */
public class Job {
    int jobId;
    int propertyId;
    LocalDate bookingDate;
    LocalDate completionDate;
    double charge;
    String serviceStaffName;
    String description;
    public enum JobType {
        Cleaning,
        Extermination,
        Electrical,
        Plumbing,
        Structural,
        Gardening
    }
    
    public enum JobStatus {
        Planned,
        Ongoing,
        Completed,
        Cancelled,
    }
    
    private JobType jobType = JobType.Cleaning;
    private JobStatus jobStatus = JobStatus.Planned;
    
    public Job(int jobId, int propertyId, String bookingDate, String completionDate, double charge, String serviceStaffName, String description, String jobType, String jobStatus){
        this.jobId = jobId;
        this.propertyId = propertyId;
        this.bookingDate = LocalDate.parse(bookingDate);
        this.completionDate = LocalDate.parse(completionDate);
        this.charge = charge;
        this.serviceStaffName = serviceStaffName;
        this.description = description;
        this.jobType = JobType.valueOf(jobType);
        this.jobStatus = JobStatus.valueOf(jobStatus);
    }
    
    public Job(int jobId, int propertyId, String bookingDate, double charge, String serviceStaffName, String description, String jobType, String jobStatus){
        this.jobId = jobId;
        this.propertyId = propertyId;
        this.bookingDate = LocalDate.parse(bookingDate);
        this.completionDate = null;
        this.charge = charge;
        this.serviceStaffName = serviceStaffName;
        this.description = description;
        this.jobType = JobType.valueOf(jobType);
        this.jobStatus = JobStatus.valueOf(jobStatus);
    }

    public int getJobId() {
        return jobId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public double getCharge() {
        return charge;
    }

    public String getServiceStaffName() {
        return serviceStaffName;
    }

    public String getDescription() {
        return description;
    }

    public String getJobType() {
        return jobType.toString();
    }

    public String getJobStatus() {
        return jobStatus.toString();
    }
}
