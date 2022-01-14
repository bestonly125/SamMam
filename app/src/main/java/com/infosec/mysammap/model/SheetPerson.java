package com.infosec.mysammap.model;

public class SheetPerson {

    String title;
    String description;
    int imgID;


    public SheetPerson(String title, String description, int imgID) {
        this.title = title;
        this.description = description;
        this.imgID = imgID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }
}
