package com.example.domka.menhajAlsaleheen.app.Model.List;

import android.content.Context;

import com.example.domka.menhajAlsaleheen.database.DatabaseAdapter;
import com.example.domka.menhajAlsaleheen.database.Table;

/**
 * Created by domka on 2017-04-23.
 */

public class List {
    private Integer ID;
    private Integer part_ID;
    private Integer page_no;
    private String title;
    private String note;

    public List(Integer ID, Integer part_ID, Integer page_no, String title, String note) {
        this.ID = ID;
        this.part_ID = part_ID;
        this.page_no = page_no;
        this.title = title;
        this.note = note;
    }

    public List() {
    }

    public Table getAllList(Context context, Integer part_ID){
        Table table;
        String params[] ={String.valueOf(part_ID)};
        String sql ="SELECT * FROM List where part_ID = ?";
        DatabaseAdapter db = new DatabaseAdapter(context) ;
        db.open();
        table = db.select(sql,params);
        db.close();

        return table;

    }















    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getPart_ID() {
        return part_ID;
    }

    public void setPart_ID(Integer part_ID) {
        this.part_ID = part_ID;
    }

    public Integer getPage_no() {
        return page_no;
    }

    public void setPage_no(Integer page_no) {
        this.page_no = page_no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
