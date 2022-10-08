package com.example.pankajknitting.model;

public class recyclerModel {
    int img;
    String desc;
    double price;

    //Constructor
    public recyclerModel(int img, String desc, double price) {
        this.img = img;
        this.desc = desc;
        this.price = price;
    }

    //Getter and Setter


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
