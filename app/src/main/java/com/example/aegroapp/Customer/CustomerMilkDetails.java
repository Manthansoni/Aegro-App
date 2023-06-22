package com.example.aegroapp.Customer;


public class CustomerMilkDetails {
    private String date;
    private String litre;
    private String type;
    private String time;
    private int fat;
    private int container;
    private boolean isExpanded;

    public CustomerMilkDetails(){

    }

    public CustomerMilkDetails(String date, String litre, String type, String time, int fat) {
        this.date = date;
        this.isExpanded = false;
        this.litre = litre;
        this.type = type;
        this.time = time;
        this.fat = fat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLitre() {
        return litre;
    }

    public void setLitre(String litre) {
        this.litre = litre;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getContainer() {
        return container;
    }

    public void setContainer(int container) {
        this.container = container;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
