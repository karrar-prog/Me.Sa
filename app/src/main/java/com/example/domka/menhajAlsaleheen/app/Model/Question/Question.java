package com.example.domka.menhajAlsaleheen.app.Model.Question;

/**
 * Created by domka on 2017-04-23.
 */

public class Question {

    private Integer ID;
    private Integer folow_ID;
    private Integer question_no;
    private String question_content;
    private String note;
    private String page_no;
    private String favorite;
    private String part_ID;


    public Question(String part_ID) {
        this.part_ID = part_ID;
    }

    public Question() {
    }

















    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getFolow_ID() {
        return folow_ID;
    }

    public void setFolow_ID(Integer folow_ID) {
        this.folow_ID = folow_ID;
    }

    public Integer getQuestion_no() {
        return question_no;
    }

    public void setQuestion_no(Integer question_no) {
        this.question_no = question_no;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPage_no() {
        return page_no;
    }

    public void setPage_no(String page_no) {
        this.page_no = page_no;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getPart_ID() {
        return part_ID;
    }

    public void setPart_ID(String part_ID) {
        this.part_ID = part_ID;
    }
}
