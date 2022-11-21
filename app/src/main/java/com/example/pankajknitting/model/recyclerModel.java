package com.example.pankajknitting.model;

public class recyclerModel {
    String img;
    String desc;
    String price;

    //Constructor
    public recyclerModel(String img, String desc, String price) {
        this.img = img;
        this.desc = desc;
        this.price = price;
    }

    //Getter and Setter


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
