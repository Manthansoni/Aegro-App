package com.example.aegroapp;


public class MilkDetails {
    private String date;
    private String litre;
    private String type;
//    private String time;
    private String fat;
    private String container;
    private String quality;
    private String priceOfLitre;
    private String receivedBy;
    private String  amount;
    private boolean isExpanded;

    public MilkDetails(){

    }

    public MilkDetails(String date, String litre, String type, String time, String fat, String container, String quality, String priceOfLitre, String receivedBy, String amount) {
        this.date = date;
        this.isExpanded = true;
        this.litre = litre;
        this.type = type;
        this.fat = fat;
        this.container = container;
        this.quality = quality;
        this.priceOfLitre = priceOfLitre;
        this.receivedBy = receivedBy;
        this.amount = amount;
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

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getPriceOfLitre() {
        return priceOfLitre;
    }

    public void setPriceOfLitre(String priceOfLitre) {
        this.priceOfLitre = priceOfLitre;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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
