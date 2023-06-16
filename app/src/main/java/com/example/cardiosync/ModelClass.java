package com.example.cardiosync;

//what will be a record look like
public class ModelClass {
    //variable declaration
    String date = "";
    String time = "";
    String systolic = "";
    String diastolic = "";
    String bloodPressure = "";
    String comment = "";

    public ModelClass(String date, String time, String systolic, String diastolic, String bloodPressure, String comment) {
        //assigning values
        this.date = date;
        this.time = time;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.bloodPressure = bloodPressure;
        this.comment = comment;
    }

    //setters and getters
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