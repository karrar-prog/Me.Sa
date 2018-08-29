package com.example.domka.menhajAlsaleheen.app.Model.Part;

import android.content.Context;

import com.example.domka.menhajAlsaleheen.database.DatabaseAdapter;
import com.example.domka.menhajAlsaleheen.database.Table;

/**
 * Created by domka on 2017-04-23.
 */
public class Part
{
    private Integer ID;
    private Integer book_ID;
    private String name;
    private String intro;
    private String note;
    private String image;
    private String edition;
    private String image_path;

    public Part(Integer ID, Integer book_ID, String name, String intro, String note, String image, String edition, String image_path)
    {
        this.ID = ID;
        this.book_ID = book_ID;
        this.name = name;
        this.intro = intro;
        this.note = note;
        this.image = image;
        this.edition = edition;
        this.image_path = image_path;
    }

    public Part()
    {
    }

    public Table getAllparts(Context context, Integer book_ID)
    {
        Table table;
        String params[] = {String.valueOf(book_ID)};
        String sql = "SELECT * FROM part where book_ID = ?";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        table = db.select(sql, params);
        db.close();
        return table;
    }

    public Integer getID()
    {
        return ID;
    }

    public void setID(Integer ID)
    {
        this.ID = ID;
    }

    public Integer getBook_ID()
    {
        return book_ID;
    }

    public void setBook_ID(Integer book_ID)
    {
        this.book_ID = book_ID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getIntro()
    {
        return intro;
    }

    public void setIntro(String intro)
    {
        this.intro = intro;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getEdition()
    {
        return edition;
    }

    public void setEdition(String edition)
    {
        this.edition = edition;
    }

    public String getImage_path()
    {
        return image_path;
    }

    public void setImage_path(String image_path)
    {
        this.image_path = image_path;
    }
}
