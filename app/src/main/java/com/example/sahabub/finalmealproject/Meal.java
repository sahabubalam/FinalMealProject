package com.example.sahabub.finalmealproject;

public class Meal {
    private String requestedBy;
    private String roll;
    private String datetime;
    private String lunch;
    private String dinner;
    public Meal()
    {

    }

    public Meal(String requestedBy, String roll, String datetime, String lunch, String dinner) {
        this.requestedBy = requestedBy;
        this.roll = roll;
        this.datetime = datetime;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }
}
