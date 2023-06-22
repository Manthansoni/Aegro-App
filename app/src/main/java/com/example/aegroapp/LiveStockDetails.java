package com.example.aegroapp;

public class LiveStockDetails {

    public LiveStockDetails() {

    }

    public LiveStockDetails(String name, String date, String number, String type, String capacity, String liveStockUrl, String details) {
        this.name = name;
        this.date = date;
        this.number = number;
        this.type = type;
        this.capacity = capacity;
        this.details = details;
//        this.avatarUrl = avatarUrl;
//        this.liveStockUrl = liveStockUrl;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

//    public String getLiveStockUrl() {
//        return liveStockUrl;
//    }
//
//    public void setLiveStockUrl(String liveStockUrl) {
//        this.liveStockUrl = liveStockUrl;
//    }

    String name;
    String date;
    String number;
    String type;
    String capacity;
    String details;
//    String avatarUrl;
//    String liveStockUrl;

}
