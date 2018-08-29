package com.example.domka.menhajAlsaleheen.app.Model.Book;

import android.content.Context;

import com.example.domka.menhajAlsaleheen.database.DatabaseAdapter;
import com.example.domka.menhajAlsaleheen.database.Table;

/**
 * Created by domka on 2017-04-23.
 */

public class Book {
    private Integer ID ;
    private  String name;
    private  String intro;
    private  String publisher;
    private  String image;
    private  String edition;
    private  String auther;
    private  String image_path;

    public Book(Integer ID, String name, String intro, String publisher, String image, String edition, String auther, String image_path) {
        this.ID = ID;
        this.name = name;
        this.intro = intro;
        this.publisher = publisher;
        this.image = image;
        this.edition = edition;
        this.auther = auther;
        this.image_path = image_path;
    }

    public Book() {
    }

    public Table  getAllBook(Context context){

        Table table;
        String sql ="SELECT * FROM book";
        DatabaseAdapter db = new DatabaseAdapter(context) ;
        db.open();
        table = db.select(sql);
        db.close();
        return table;
    }















    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }



}
