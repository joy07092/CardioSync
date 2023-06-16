package com.example.cardiosync;

/**
 * This is class for getting and setting data in the list
 */
public class ModelClass {
    /**
     * These are the variables that will hold the record
     */
    String date = "";
    String time = "";
    String systolic = "";
    String diastolic = "";
    String bloodPressure = "";
    String comment = "";

    /**
     * This is the Parameterized constructor of the class for assigning the variables with values
     *
     * @param date, time, systolic, diastolic, bloodPressure, comment
     *              these are all the variables that hold the record
     */
    public ModelClass(String date, String time, String systolic, String diastolic, String bloodPressure, String comment) {
        //assigning values
        this.date = date;
        this.time = time;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.bloodPressure = bloodPressure;
        this.comment = comment;
    }

    /**
     * These are the getter methods
     */
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}