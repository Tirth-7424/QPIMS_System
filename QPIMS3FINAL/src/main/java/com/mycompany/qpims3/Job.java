/*
 The Job class is used to represent service/maintenance jobs. It contains two
constructors. A regular constructor, and a constructor without a completion 
date. We decided that the completion date should be optional because
the completion date may not necessarily be known when the job entry is
created.
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
    // Enums are public so they can be used to populate choiceboxes in GUI
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
    
    // Regular constructor
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
    
    // Constructor without completion date
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
